package com.hd_solutions.accounting_service.reporting;

import com.hd_solutions.accounting_service.reporting.dto.BudgetSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/productions/{productionId}/budget-summary")
@RequiredArgsConstructor
public class BudgetSummaryController {

    private final BudgetSummaryService budgetSummaryService;

    @GetMapping
    public BudgetSummaryResponse getBudgetSummary(@PathVariable UUID productionId) {
        return budgetSummaryService.getBudgetSummary(productionId);
    }
}
