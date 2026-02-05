package com.hd_solutions.accounting_service.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateExpenseRequest(
        UUID budgetCategoryId,
        String description,
        BigDecimal amount,
        LocalDate expenseDate,
        String vendorName
) {
}
