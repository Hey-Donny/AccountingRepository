package com.hd_solutions.accounting_service.budget;

import com.hd_solutions.accounting_service.budget.dto.BudgetCategoryResponse;
import com.hd_solutions.accounting_service.budget.dto.CreateBudgetCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BudgetCategoryController {

    private final BudgetCategoryService budgetCategoryService;

    @PostMapping("/api/productions/{productionId}/budget-categories")
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetCategoryResponse create(
            @PathVariable UUID productionId,
            @RequestBody CreateBudgetCategoryRequest request) {
        return budgetCategoryService.create(productionId, request);
    }

    @GetMapping("/api/productions/{productionId}/budget-categories")
    public List<BudgetCategoryResponse> findByProductionId(@PathVariable UUID productionId) {
        return budgetCategoryService.findByProductionId(productionId);
    }

    @PutMapping("/api/budget-categories/{id}")
    public BudgetCategoryResponse update(
            @PathVariable UUID id,
            @RequestBody CreateBudgetCategoryRequest request) {
        return budgetCategoryService.update(id, request);
    }

    @DeleteMapping("/api/budget-categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        budgetCategoryService.delete(id);
    }
}
