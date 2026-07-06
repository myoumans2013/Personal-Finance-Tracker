package com.youmanscode.personalfinancetrackerapi.dto;

import com.youmanscode.personalfinancetrackerapi.enums.AccountName;

import java.math.BigDecimal;
import java.util.List;

public class AccountDetails {
    Long id;
    AccountName name;
    BigDecimal startingBalance;
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

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }
}
