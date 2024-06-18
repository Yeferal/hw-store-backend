package com.microservice.admin_microservice.web.controller;

import com.microservice.admin_microservice.domain.dto.AccountDTO;
import com.microservice.admin_microservice.domain.dto.AccountRegisterBodyDTO;
import com.microservice.admin_microservice.domain.dto.PaginateAndSortDTO;
import com.microservice.admin_microservice.domain.service.AccountService;
import com.microservice.admin_microservice.source.exception.FailedRegisterException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Get list of accounts
    @GetMapping
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "username") String sortField,
            @RequestParam(required = false) String searchValue
    ) {
        PaginateAndSortDTO paginateAndSortDTO = new PaginateAndSortDTO(page, size, sortOrder, sortField, searchValue);
        Page<AccountDTO> accounts = accountService.getAllAccounts(paginateAndSortDTO);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Create account
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(
            @Valid @RequestBody AccountRegisterBodyDTO accountRegisterBodyDTO
    ) {
        try {
            AccountDTO accountDTO = accountService.registerAccount(accountRegisterBodyDTO);
            return ResponseEntity.ok(accountDTO);
        }catch (FailedRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/test")
    public ResponseEntity<String> saludo() {
        return ResponseEntity.ok("Hello World");
    }
}
