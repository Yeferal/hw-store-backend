package com.microservice.auth_microservice.web.config;

import com.microservice.auth_microservice.domain.repository.AccountRepository;
import com.microservice.auth_microservice.persistence.model.Account;
import com.microservice.auth_microservice.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountAuthRepository;

    //Método para traernos una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roleList){
        return roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    //Método para traernos un usuario con todos sus datos por medio de sus username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountAuthRepository.getAccountByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(account.getAssignmentRoleList().toString())
        );

        return new User(account.getUsername(), account.getPassword(), authorities);
//        return new User(account.getUsername(), account.getPassword(), mapToAuthorities(roleList));
    }
}
