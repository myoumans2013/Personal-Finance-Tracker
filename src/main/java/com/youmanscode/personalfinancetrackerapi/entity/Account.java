package com.youmanscode.personalfinancetrackerapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    Long userId;
    String name;
    BigDecimal startingBalance;
    @OneToMany
    List<Transaction> transactions;

    public Account() {
    }

    public Account(Long userId, Long id, String name, BigDecimal startingBalance) {
        this.userId = userId;
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }
}
