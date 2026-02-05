package com.hd_solutions.accounting_service.budget.dto;

import com.hd_solutions.accounting_service.budget.BudgetCategory;
import com.hd_solutions.accounting_service.budget.BudgetCategoryType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record BudgetCategoryResponse(
        UUID id,
        UUID productionId,
        String name,
        BudgetCategoryType type,
        BigDecimal budgetedAmount,
        Instant createdAt,
        Instant updatedAt
) {
    public static BudgetCategoryResponse from(BudgetCategory category) {
        return new BudgetCategoryResponse(
                category.getId(),
                category.getProduction().getId(),
                category.getName(),
                category.getType(),
                category.getBudgetedAmount(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}
