package com.hd_solutions.accounting_service.reporting;

import com.hd_solutions.accounting_service.budget.BudgetCategory;
import com.hd_solutions.accounting_service.budget.BudgetCategoryRepository;
import com.hd_solutions.accounting_service.expense.ExpenseRepository;
import com.hd_solutions.accounting_service.production.Production;
import com.hd_solutions.accounting_service.production.ProductionService;
import com.hd_solutions.accounting_service.reporting.dto.BudgetSummaryResponse;
import com.hd_solutions.accounting_service.reporting.dto.BudgetSummaryResponse.CategorySummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BudgetSummaryService {

    private final ProductionService productionService;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetSummaryResponse getBudgetSummary(UUID productionId) {
        Production production = productionService.getProductionEntity(productionId);
        List<BudgetCategory> categories = budgetCategoryRepository.findByProductionId(productionId);

        List<CategorySummary> categorySummaries = new ArrayList<>();
        BigDecimal totalBudgeted = BigDecimal.ZERO;
        BigDecimal totalSpent = BigDecimal.ZERO;

        for (BudgetCategory category : categories) {
            BigDecimal budgeted = category.getBudgetedAmount();
            BigDecimal spent = expenseRepository.sumByBudgetCategoryId(category.getId());
            BigDecimal remaining = budgeted.subtract(spent);

            double percentUsed = 0.0;
            if (budgeted.compareTo(BigDecimal.ZERO) > 0) {
                percentUsed = spent.multiply(BigDecimal.valueOf(100))
                        .divide(budgeted, 2, RoundingMode.HALF_UP)
                        .doubleValue();
            }

            categorySummaries.add(new CategorySummary(
                    category.getId(),
                    category.getName(),
                    category.getType(),
                    budgeted,
                    spent,
                    remaining,
                    percentUsed
            ));

            totalBudgeted = totalBudgeted.add(budgeted);
            totalSpent = totalSpent.add(spent);
        }

        return new BudgetSummaryResponse(
                production.getId(),
                production.getName(),
                totalBudgeted,
                totalSpent,
                totalBudgeted.subtract(totalSpent),
                categorySummaries
        );
    }
}
