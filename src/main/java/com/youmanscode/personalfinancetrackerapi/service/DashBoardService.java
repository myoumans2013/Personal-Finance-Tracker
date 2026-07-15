package com.youmanscode.personalfinancetrackerapi.service;

import com.youmanscode.personalfinancetrackerapi.dto.DashBoardDetails;
import com.youmanscode.personalfinancetrackerapi.entity.Account;
import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.enums.TransactionType;
import com.youmanscode.personalfinancetrackerapi.exceptionhandling.ResourceNotFoundException;
import com.youmanscode.personalfinancetrackerapi.repository.AccountRepository;
import com.youmanscode.personalfinancetrackerapi.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DashBoardService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public DashBoardService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public DashBoardDetails getDashBoardDetails(Long id) {
        DashBoardDetails details = new DashBoardDetails();

        details.setAccountId(id);
        details.setCurrentBalance(getCurrentAccountBalance(id));
        details.setTotalIncome(getTotalIncome(id));
        details.setTotalExpenses(getTotalExpenses(id));
        details.setTransactionCount(getTransactionCount(id));
        details.setLargestIncome(getLargestIncome(id));
        details.setLargestExpense(getLargestExpense(id));

        return details;
    }

    public BigDecimal getCurrentAccountBalance(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find account with the Id: #" + id + "."));
        List<Transaction> transactions = transactionRepository.findByAccount_Id(id);
        BigDecimal getBalance = account.getStartingBalance();

        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(TransactionType.INCOME)) {
                getBalance = getBalance.add(transaction.getAmount());
            } else {
                getBalance = getBalance.subtract(transaction.getAmount());
            }
        }
        return getBalance;
    }

    public BigDecimal getTotalIncome(Long id) {
        BigDecimal income = BigDecimal.valueOf(0.00);
        List<Transaction> transactions = transactionRepository.findByAccount_Id(id);

        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(TransactionType.INCOME)) {
                income = income.add(transaction.getAmount());
            }
        }
        return income;
    }

    public BigDecimal getTotalExpenses(Long id) {
        BigDecimal income = BigDecimal.valueOf(0.00);
        List<Transaction> transactions = transactionRepository.findByAccount_Id(id);

        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(TransactionType.EXPENSE)) {
                income = income.add(transaction.getAmount());
            }
        }
        return income;
    }

    public int getTransactionCount(Long id) {
        List<Transaction> transactions = transactionRepository.findByAccount_Id(id);
        return transactions.size();
    }

    public BigDecimal getLargestIncome(Long id) {
        List<Transaction> transactions = transactionRepository.findByAccount_Id(id);
        BigDecimal income = BigDecimal.valueOf(0.00);

        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(TransactionType.INCOME)) {
                if (transaction.getAmount().compareTo(income) > 0) {
                    income = income.add(transaction.getAmount());
                }
            }
        }
        return income;
    }

    public BigDecimal getLargestExpense(Long id) {
        List<Transaction> transactions = transactionRepository.findByAccount_Id(id);
        BigDecimal expense = BigDecimal.valueOf(0.00);

        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(TransactionType.EXPENSE)) {
                if (transaction.getAmount().compareTo(expense) > 0) {
                    expense = expense.add(transaction.getAmount());
                }
            }
        }
        return expense;
    }
}
