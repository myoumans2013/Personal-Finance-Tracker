package com.youmanscode.personalfinancetrackerapi.controller;

import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("getAccounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("getAccounts/{id}")
    public Account getAccountsById(@PathVariable Long id) {
        return accountService.getAccountsById(id);
    }

    @PostMapping("createAccount")
    public Account createAccount(@Valid @RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @DeleteMapping("deleteAccount/{id}")
    public void deleteAccountById(@PathVariable Long id) throws Exception {
        accountService.deleteAccountById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllAccountsAndTransactions() {
        accountService.deleteAllAccountsAndTransactions();
    }
}
