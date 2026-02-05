package com.hd_solutions.accounting_service.budget.dto;

import com.hd_solutions.accounting_service.budget.BudgetCategoryType;

import java.math.BigDecimal;

public record CreateBudgetCategoryRequest(
        String name,
        BudgetCategoryType type,
        BigDecimal budgetedAmount
) {
}
