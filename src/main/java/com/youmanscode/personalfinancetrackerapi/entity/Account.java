package com.youmanscode.personalfinancetrackerapi.entity;

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
    @OneToMany
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(Long userId, Long id, AccountName name, BigDecimal startingBalance) {
        this.userId = userId;
        this.id = id;
        this.accountName = name;
        this.startingBalance = startingBalance;
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
