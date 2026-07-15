package com.youmanscode.personalfinancetrackerapi.service;

import com.youmanscode.personalfinancetrackerapi.dto.AccountDetails;
import com.youmanscode.personalfinancetrackerapi.dto.TransactionDetails;
import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.exceptionhandling.ResourceNotFoundException;
import com.youmanscode.personalfinancetrackerapi.repository.AccountRepository;
import com.youmanscode.personalfinancetrackerapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Cannot find any accounts.");
        }
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
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID: #" + id + " not found."));
    }

    public void updateAccount(Long id, Account newAccountInfo) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID: #" + id + " not found."));

        existingAccount.setAccountName(newAccountInfo.getName());
        existingAccount.setStartingBalance(newAccountInfo.getStartingBalance());

        accountRepository.save(existingAccount);

    }

    public void deleteAccountById(Long accountId) throws Exception {
       Account account = accountRepository.findById(accountId)
               .orElseThrow(() -> new ResourceNotFoundException("Account with ID: #" + accountId + " not found."));
        if (transactionRepository.existsByAccount_Id(accountId)) {
            throw new Exception("Transactions must be deleted before account deletion.");
        }
        accountRepository.delete(account);
    }

    public void deleteAllAccountsAndTransactions() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
    }

}
