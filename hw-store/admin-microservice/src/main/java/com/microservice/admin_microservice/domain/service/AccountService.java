package com.microservice.admin_microservice.domain.service;

import com.microservice.admin_microservice.domain.dto.AccountDTO;
import com.microservice.admin_microservice.domain.dto.AccountRegisterBodyDTO;
import com.microservice.admin_microservice.domain.dto.PaginateAndSortDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {

    AccountDTO registerAccount(AccountRegisterBodyDTO accountRegister);
    Page<AccountDTO> getAllAccounts(PaginateAndSortDTO paginateAndSortDTO);
}
