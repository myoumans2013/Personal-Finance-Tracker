package com.youmanscode.personalfinancetrackerapi.dto;

import com.youmanscode.personalfinancetrackerapi.enums.AccountName;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AccountRequest {
    @NotNull(message = "Account name cannot be blank.")
    AccountName accountName;
    @NotNull(message = "Starting balance cannot be empty.")
    BigDecimal startingBalance;

    public AccountRequest() {
    }

    public AccountName getAccountName() {
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
