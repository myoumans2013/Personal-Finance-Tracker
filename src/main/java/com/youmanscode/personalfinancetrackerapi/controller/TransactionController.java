package com.youmanscode.personalfinancetrackerapi.controller;

import com.youmanscode.personalfinancetrackerapi.dto.TransactionDetails;
import com.youmanscode.personalfinancetrackerapi.dto.TransactionRequest;
import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.enums.Category;
import com.youmanscode.personalfinancetrackerapi.enums.TransactionType;
import com.youmanscode.personalfinancetrackerapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("getAllTransactions")
    public List<TransactionDetails> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("getTransactions/{id}")
    public List<TransactionDetails> getTransactions(@PathVariable Long id) {
        return transactionService.getAllTransactionsByAccountId(id);
    }

    @GetMapping("getTransactionsByCategory/{category}")
    public List<TransactionDetails> getTransactionsByCategory(@PathVariable Category category) {
        return transactionService.findByCategory(category);
    }

    @GetMapping("getTransactionsByType/{type}")
        public List<TransactionDetails> getTransactionsByType(@PathVariable TransactionType type) {
            return transactionService.getTransactionsByTransactionType(type);
        }

    @PostMapping("createTransaction")
    public void createTransaction(@Valid @RequestBody TransactionRequest request) {
        transactionService.createTransaction(request);
    }

    @PutMapping("updateTransaction/{id}")
    public void updateTransaction(@Valid @RequestBody TransactionRequest transactionRequest, @PathVariable Long id) {
        transactionService.updateTransaction(transactionRequest, id);
    }

}
