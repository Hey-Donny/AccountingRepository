package com.hd_solutions.accounting_service.expense.dto;

import com.hd_solutions.accounting_service.expense.Expense;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        UUID productionId,
        UUID budgetCategoryId,
        String description,
        BigDecimal amount,
        LocalDate expenseDate,
        String vendorName,
        Instant createdAt,
        Instant updatedAt
) {
    public static ExpenseResponse from(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getProduction().getId(),
                expense.getBudgetCategory() != null ? expense.getBudgetCategory().getId() : null,
                expense.getDescription(),
                expense.getAmount(),
                expense.getExpenseDate(),
                expense.getVendorName(),
                expense.getCreatedAt(),
                expense.getUpdatedAt()
        );
    }
}
