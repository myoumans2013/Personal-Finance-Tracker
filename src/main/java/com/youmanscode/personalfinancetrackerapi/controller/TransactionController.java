package com.youmanscode.personalfinancetrackerapi.controller;

import com.youmanscode.personalfinancetrackerapi.dto.TransactionDetails;
import com.youmanscode.personalfinancetrackerapi.dto.TransactionRequest;
import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("getTransactions/{id}")
    public List<TransactionDetails> getTransactions(@PathVariable Long id) {
        return transactionService.getAllTransactionsByAccountId(id);
    }


    @PostMapping("createTransaction")
    public void createTransaction(@RequestBody TransactionRequest request) {
        transactionService.createTransaction(request);
    }

}
