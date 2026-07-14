package com.youmanscode.personalfinancetrackerapi.service;

import com.youmanscode.personalfinancetrackerapi.dto.AccountDetails;
import com.youmanscode.personalfinancetrackerapi.dto.TransactionDetails;
import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.enums.TransactionType;
import com.youmanscode.personalfinancetrackerapi.repository.AccountRepository;
import com.youmanscode.personalfinancetrackerapi.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final DashBoardService dashBoardService;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository, TransactionService transactionService, DashBoardService dashBoardService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
        this.dashBoardService = dashBoardService;
    }

    public AccountDetails accountDetailsDTO(Account account) {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(account.getId());
        accountDetails.setName(account.getName());
        accountDetails.setStartingBalance(account.getStartingBalance());
        accountDetails.setCurrentBalance(dashBoardService.getCurrentAccountBalance(account.getId()));

        List<Transaction> transactions = transactionRepository.findByAccount_Id(account.getId());
        List<TransactionDetails> list = new ArrayList<>();
        for (Transaction transaction : transactions)
            list.add(transactionService.transferToTransactionDetails(transaction));

        accountDetails.setTransactionList(list);
        return accountDetails;
    }

    public List<AccountDetails> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDetails> setAccountDetails = new ArrayList<>();

        for (Account account : accounts) {
           setAccountDetails.add(accountDetailsDTO(account));
        }

        return setAccountDetails;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountsById(Long id) {
        Optional<Account> account = accountRepository.getAccountsById(id);
        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return account.get();
    }

    public void deleteAccountById(Long accountId) throws Exception {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (transactionRepository.existsByAccount_Id(accountId)) {
            throw new Exception("Transactions must be deleted before account deletion.");
        }
        Account deleteAccount = account.get();
        accountRepository.delete(deleteAccount);
    }

    public void deleteAllAccountsAndTransactions() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
    }

}
