package com.microservice.admin_microservice.domain.serviceimpl;

import com.microservice.admin_microservice.domain.dto.AccountDTO;
import com.microservice.admin_microservice.domain.dto.AccountRegisterBodyDTO;
import com.microservice.admin_microservice.domain.dto.PaginateAndSortDTO;
import com.microservice.admin_microservice.domain.map.AccountMapper;
import com.microservice.admin_microservice.domain.repository.AccountRepository;
import com.microservice.admin_microservice.domain.repository.UserProfileRepository;
import com.microservice.admin_microservice.domain.service.AccountService;
import com.microservice.admin_microservice.domain.spec.AccountSpecifications;
import com.microservice.admin_microservice.persistence.model.Account;
import com.microservice.admin_microservice.persistence.model.AssignmentRole;
import com.microservice.admin_microservice.persistence.model.UserProfile;
import com.microservice.admin_microservice.source.exception.FailedRegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(rollbackFor = {FailedRegisterException.class, Exception.class})
    public AccountDTO registerAccount(AccountRegisterBodyDTO accountRegister) {
        try {
            if (!accountRepository.isExistUsername(accountRegister.getUsername())){

                //Creation Account
                Account account = Account.builder()
                        .username(accountRegister.getUsername())
                        .creationDate(LocalDateTime.now())
                        .password(passwordEncoder.encode(accountRegister.getPassword()))
                        .verified(false)
                        .active(true)
                        .build();
                account = accountRepository.createAccount(account);

                if (account == null) {
                    throw new FailedRegisterException("No se pude registrar el usuario: ocurrió algún error en el registro account");
                }

                // Assignment Role Account
                AssignmentRole assignmentRole = AssignmentRole.builder()
                        .accountId(account.getId())
                        .roleId(accountRegister.getRoleId())
                        .build();
                assignmentRole = accountRepository.createAssignmentRole(assignmentRole);

                if (assignmentRole == null) {
                    throw new FailedRegisterException("No se pude registrar el usuario: ocurrió algún error en el registro assignment");
                }

                account.setAssignmentRoleList(List.of(assignmentRole));

                // Creation Profile
                UserProfile userProfile = UserProfile.builder()
                        .firstName(accountRegister.getFirstName())
                        .lastName(accountRegister.getLastName())
                        .email(accountRegister.getEmail())
                        .phoneNumber(accountRegister.getPhoneNumber())
                        .account(account)
                        .build();
                userProfileRepository.createUserProfile(userProfile);

                if (userProfile == null) {
                    throw new FailedRegisterException("No se pude registrar el usuario: ocurrió algún error en el registro profile");
                }

                account.setUserProfile(userProfile);

                AccountDTO accountDTO = accountMapper.toDto(account);
                System.out.println(accountDTO.toString());
                return accountDTO;
            } else {
                throw new FailedRegisterException("No se pude registrar el usuario: ya existe un usuario con el mismo Username");
            }
        } catch (Exception ex) {
            // La anotación @Transactional se encargará de realizar el rollback en caso de excepción.
            throw ex;
        }
    }

    @Override
    public Page<AccountDTO> getAllAccounts(PaginateAndSortDTO paginateAndSortDTO) {
        Sort sort = Sort.by(Sort.Direction.ASC, paginateAndSortDTO.getSortField());

        if (paginateAndSortDTO.getSortOrder().equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC, paginateAndSortDTO.getSortField());
        }

        Pageable pageable = PageRequest.of(paginateAndSortDTO.getPage(), paginateAndSortDTO.getSize(), sort);
        Specification<Account> specs = Specification.where(AccountSpecifications.isActive(true));

        if (paginateAndSortDTO.getSearchValue() != null){
            specs = specs.and(AccountSpecifications.usernameContains(paginateAndSortDTO.getSearchValue()))
                    .or(AccountSpecifications.userProfileFirstNameContains(paginateAndSortDTO.getSearchValue()))
                    .or(AccountSpecifications.userProfileLastNameContains(paginateAndSortDTO.getSearchValue()))
                    .or(AccountSpecifications.roleNameContains(paginateAndSortDTO.getSearchValue()));
        }

        Page<Account> pageAccounts = accountRepository.getAllAccountsPaginateAndSort(specs, pageable);
        List<AccountDTO> accountDTOs = pageAccounts.getContent().stream()
                .map(accountMapper::toDto)
                .toList();

        return new PageImpl<>(accountDTOs, pageable, pageAccounts.getTotalElements());
    }

}
