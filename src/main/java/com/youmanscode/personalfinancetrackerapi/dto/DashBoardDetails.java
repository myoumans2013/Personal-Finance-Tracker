package com.youmanscode.personalfinancetrackerapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
@JsonPropertyOrder({
        "accountId",
        "transactionCount",
        "currentBalance",
        "totalIncome",
        "totalExpenses",
        "largestIncome",
        "largestExpense"
})
public class DashBoardDetails {
    private Long accountId;
    private BigDecimal currentBalance;
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private int transactionCount;
    private BigDecimal largestIncome;
    private BigDecimal largestExpense;

    public DashBoardDetails() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public BigDecimal getLargestIncome() {
        return largestIncome;
    }

    public void setLargestIncome(BigDecimal largestIncome) {
        this.largestIncome = largestIncome;
    }

    public BigDecimal getLargestExpense() {
        return largestExpense;
    }

    public void setLargestExpense(BigDecimal largestExpense) {
        this.largestExpense = largestExpense;
    }
}
