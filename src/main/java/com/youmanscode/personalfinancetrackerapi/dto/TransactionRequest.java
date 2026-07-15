package com.youmanscode.personalfinancetrackerapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmanscode.personalfinancetrackerapi.enums.Category;
import com.youmanscode.personalfinancetrackerapi.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequest {
    private Long accountId;
    @NotNull(message = "Amount cannot be empty")
    private BigDecimal amount;
    @NotNull(message = "Type cannot be empty")
    private TransactionType type;
    @NotNull(message = "Category cannot be empty")
    private Category category;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Date cannot be empty")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate transactionDate;

    public TransactionRequest() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
