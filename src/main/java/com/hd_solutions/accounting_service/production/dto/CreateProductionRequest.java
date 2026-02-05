package com.hd_solutions.accounting_service.production.dto;

import com.hd_solutions.accounting_service.production.ProductionStatus;

import java.time.LocalDate;

public record CreateProductionRequest(
        String name,
        String description,
        ProductionStatus status,
        LocalDate startDate,
        LocalDate endDate
) {
}
