package com.hd_solutions.accounting_service.production.dto;

import com.hd_solutions.accounting_service.production.Production;
import com.hd_solutions.accounting_service.production.ProductionStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ProductionResponse(
        UUID id,
        String name,
        String description,
        ProductionStatus status,
        LocalDate startDate,
        LocalDate endDate,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProductionResponse from(Production production) {
        return new ProductionResponse(
                production.getId(),
                production.getName(),
                production.getDescription(),
                production.getStatus(),
                production.getStartDate(),
                production.getEndDate(),
                production.getCreatedAt(),
                production.getUpdatedAt()
        );
    }
}
