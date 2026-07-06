package com.youmanscode.personalfinancetrackerapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youmanscode.personalfinancetrackerapi.enums.AccountName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountName accountName;
    @NotNull
    private BigDecimal startingBalance;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account() {

    }

    public Account(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AccountName getName() {
        return accountName;
    }

    public void setAccountName(AccountName accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }
}
