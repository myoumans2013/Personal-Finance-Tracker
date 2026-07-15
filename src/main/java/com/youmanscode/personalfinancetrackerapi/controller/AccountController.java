package com.youmanscode.personalfinancetrackerapi.controller;

import com.youmanscode.personalfinancetrackerapi.dto.AccountDetails;
import com.youmanscode.personalfinancetrackerapi.dto.AccountRequest;
import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.service.AccountService;
import com.youmanscode.personalfinancetrackerapi.service.DashBoardService;
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
    public List<AccountDetails> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("getAccounts/{id}")
    public Account getAccountsById(@PathVariable Long id) {
        return accountService.getAccountsById(id);
    }

    @PutMapping("updateAccount/{id}")
    public void updateAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        accountService.updateAccount(id, account);
    }

    @PostMapping("createAccount")
    public void createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
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
