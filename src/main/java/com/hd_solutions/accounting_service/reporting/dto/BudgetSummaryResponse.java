package com.hd_solutions.accounting_service.reporting.dto;

import com.hd_solutions.accounting_service.budget.BudgetCategoryType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record BudgetSummaryResponse(
        UUID productionId,
        String productionName,
        BigDecimal totalBudgeted,
        BigDecimal totalSpent,
        BigDecimal totalRemaining,
        List<CategorySummary> categories
) {
    public record CategorySummary(
            UUID categoryId,
            String categoryName,
            BudgetCategoryType type,
            BigDecimal budgetedAmount,
            BigDecimal spentAmount,
            BigDecimal remainingAmount,
            double percentUsed
    ) {
    }
}
