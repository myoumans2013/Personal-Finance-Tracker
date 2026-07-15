package com.youmanscode.personalfinancetrackerapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.youmanscode.personalfinancetrackerapi.enums.AccountName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "amount",
        "name",
        "startingBalance",
        "currentBalance",
        "transactionList",
})
public class AccountDetails {
    Long id;
    @NotNull
    AccountName name;
    @NotNull
    BigDecimal startingBalance;
    @NotBlank
    BigDecimal currentBalance;
    List<TransactionDetails> transactionList;

    public AccountDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountName getName() {
        return name;
    }

    public void setName(AccountName name) {
        this.name = name;
    }

    public List<TransactionDetails> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionDetails> transactionList) {
        this.transactionList = transactionList;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }
}
