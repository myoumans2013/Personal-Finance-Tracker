package com.youmanscode.personalfinancetrackerapi.controller;

import com.youmanscode.personalfinancetrackerapi.dto.AccountDetails;
import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("getCurrentBalance/{id}")
    public BigDecimal getCurrentBalance(@PathVariable Long id) {
        return accountService.getCurrentAccountBalance(id);
    }

        @GetMapping("getAccounts")
    public List<AccountDetails> getAllAccounts() {
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
