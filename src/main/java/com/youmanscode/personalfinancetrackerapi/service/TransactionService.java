package com.youmanscode.personalfinancetrackerapi.service;

import com.youmanscode.personalfinancetrackerapi.dto.TransactionDetails;
import com.youmanscode.personalfinancetrackerapi.dto.TransactionRequest;
import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.enums.Category;
import com.youmanscode.personalfinancetrackerapi.enums.TransactionType;
import com.youmanscode.personalfinancetrackerapi.exceptionhandling.IllegalArgumentException;
import com.youmanscode.personalfinancetrackerapi.exceptionhandling.ResourceNotFoundException;
import com.youmanscode.personalfinancetrackerapi.repository.AccountRepository;
import com.youmanscode.personalfinancetrackerapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.math.BigDecimal;
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
        if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be less than 0.");
        }
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
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be less than 0.");
        }
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

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("Account with ID: #" + id + " not found.");
        }

        for (Transaction transaction : transactions) {
            transactionList.add(transferToTransactionDetails(transaction));
        }

        return transactionList;
    }

    public void createTransaction(TransactionRequest request) {
        Transaction transaction = transferToTransactionEntity(request);
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID: #" + request.getAccountId() + " not found."));
        transaction.setAccount(account);

        transactionRepository.save(transaction);
    }

    public void updateTransaction(TransactionRequest transactionRequest, Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find transaction with ID: #" + id));

        transaction.setAmount(transactionRequest.getAmount());
        transaction.setType(transactionRequest.getType());
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setTransactionDate(transactionRequest.getTransactionDate());

        transferToTransactionDetails(transaction);

        transactionRepository.save(transaction);
    }

    public List<TransactionDetails> getByCategory(Category category) {
        List<Transaction> categoryList = transactionRepository.findByCategoryOrderByTransactionDateDesc(category);
        if (categoryList.isEmpty()) {
            throw new ResourceNotFoundException("Cannot find any transactions with the category: " + category.toString().toLowerCase() + ".");
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
            throw new ResourceNotFoundException("Cannot find any transactions that match the transaction type: " + type);
        }
        List<TransactionDetails> transactionDetails = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDetails.add(transferToTransactionDetails(transaction));
        }

        return transactionDetails;
    }

    public List<TransactionDetails> getTransactionsInBetweenDates(LocalDate date1, LocalDate date2) {
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetweenOrderByTransactionDateDesc(date1, date2);
        List<TransactionDetails> transactionDetails = new ArrayList<>();

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("Cannot find any transactions between " + date1 + " and " + date2 + ".");
        }

        for (Transaction transaction : transactions) {
            transactionDetails.add(transferToTransactionDetails(transaction));
        }

        return transactionDetails;
    }

    public List<TransactionDetails> getTransactionsGreaterThanAmount(BigDecimal amount) {
        List<Transaction> transactions = transactionRepository.findAllByAmountGreaterThan(amount);
        List<TransactionDetails> transactionDetails = new ArrayList<>();

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("Cannot find any transactions greater than $" + amount + ".");
        }

        for (Transaction transaction : transactions) {
            transactionDetails.add(transferToTransactionDetails(transaction));
        }
        return transactionDetails;
    }

    public void deleteTransactionByID(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find transaction with ID: #" + id));
        transactionRepository.delete(transaction);
    }

    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }


}
