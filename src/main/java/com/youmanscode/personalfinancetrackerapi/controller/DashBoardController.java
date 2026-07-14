package com.youmanscode.personalfinancetrackerapi.controller;

import com.youmanscode.personalfinancetrackerapi.dto.DashBoardDetails;
import com.youmanscode.personalfinancetrackerapi.service.DashBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {
    private final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @GetMapping("getDashBoard/{id}")
    public DashBoardDetails getDashBoardDetailsOfAccount(@PathVariable Long id) {
        return dashBoardService.getDashBoardDetails(id);
    }

    @GetMapping("getCurrentBalance/{id}")
    public BigDecimal getCurrentBalance(@PathVariable Long id) {
        return dashBoardService.getCurrentAccountBalance(id);
    }

    @GetMapping("getTotalIncome/{id}")
    public BigDecimal getTotalIncome(@PathVariable Long id) {
        return dashBoardService.getTotalIncome(id);
    }

    @GetMapping("getTotalExpenses/{id}")
    public BigDecimal getTotalExpenses(@PathVariable Long id) {
        return dashBoardService.getTotalExpenses(id);
    }

    @GetMapping("getTransactionCountOfAccount/{id}")
    public int getTransactionCount(@PathVariable Long id) {
        return dashBoardService.getTransactionCount(id);
    }

    @GetMapping("getLargestIncome/{id}")
    public BigDecimal getLargestIncome(@PathVariable Long id) {
        return dashBoardService.getLargestIncome(id);
    }

    @GetMapping("getLargestExpense/{id}")
    public BigDecimal getLargestExpense(@PathVariable Long id) {
        return dashBoardService.getLargestExpense(id);
    }
}
