package com.youmanscode.personalfinancetrackerapi.service;

import com.youmanscode.personalfinancetrackerapi.dto.TransactionDetails;
import com.youmanscode.personalfinancetrackerapi.dto.TransactionRequest;
import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.enums.Category;
import com.youmanscode.personalfinancetrackerapi.enums.TransactionType;
import com.youmanscode.personalfinancetrackerapi.repository.AccountRepository;
import com.youmanscode.personalfinancetrackerapi.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction transferToTransactionEntity(TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDescription(request.getDescription());
        transaction.setTransactionDate(request.getTransactionDate());
        return transaction;
    }

    public TransactionDetails transferToTransactionDetails(Transaction transaction) {
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setId(transaction.getId());
        transactionDetails.setUserId(transaction.getUserId());
        transactionDetails.setAmount(transaction.getAmount());
        transactionDetails.setTransactionType(transaction.getType());
        transactionDetails.setCategory(transaction.getCategory());
        transactionDetails.setDescription(transaction.getDescription());
        transactionDetails.setTransactionDate(transaction.getTransactionDate());

        return transactionDetails;
    }

    public List<TransactionDetails> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAllByOrderByTransactionDateDesc();
        List<TransactionDetails> transactionDetails = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDetails.add(transferToTransactionDetails(transaction));
        }
        return transactionDetails;
    }

    public List<TransactionDetails> getAllTransactionsByAccountId(Long id) {
        List<Transaction> transactions = transactionRepository.findByAccount_Id(id);
        List<TransactionDetails> transactionList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionList.add(transferToTransactionDetails(transaction));
        }

        return transactionList;
    }

    public void createTransaction(TransactionRequest request) {
        Transaction transaction = transferToTransactionEntity(request);
        Optional<Account> account = accountRepository.findById(request.getAccountId());
        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Account getAccount = account.get();
        transaction.setAccount(getAccount);

        transactionRepository.save(transaction);
    }

    public List<TransactionDetails> findByCategory(Category category) {
        List<Transaction> categoryList = transactionRepository.findByCategoryOrderByTransactionDateDesc(category);
        if (categoryList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<TransactionDetails> newList = new ArrayList<>();

        for (Transaction transaction : categoryList) {
            newList.add(transferToTransactionDetails(transaction));
        }
        return newList;
    }

    public List<TransactionDetails> getTransactionsByTransactionType(TransactionType type) {
        List<Transaction> transactions = transactionRepository.findByTransactionTypeOrderByTransactionDateDesc(type);
        if (transactions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<TransactionDetails> transactionDetails = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDetails.add(transferToTransactionDetails(transaction));
        }

        return transactionDetails;
    }

    public List<TransactionDetails> findTransactionsInBetweenDates(LocalDate date1, LocalDate date2) {
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetweenOrderByTransactionDateDesc(date1, date2);
        List<TransactionDetails> transactionDetails = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDetails.add(transferToTransactionDetails(transaction));
        }

        return transactionDetails;
    }

    public void updateTransaction(TransactionRequest transactionRequest, Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Transaction getTransaction = transaction.get();

        getTransaction.setAmount(transactionRequest.getAmount());
        getTransaction.setType(transactionRequest.getType());
        getTransaction.setCategory(transactionRequest.getCategory());
        getTransaction.setDescription(transactionRequest.getDescription());
        getTransaction.setTransactionDate(transactionRequest.getTransactionDate());

        transferToTransactionDetails(getTransaction);

        transactionRepository.save(getTransaction);
    }


}
