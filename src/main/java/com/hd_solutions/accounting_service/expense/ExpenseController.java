package com.hd_solutions.accounting_service.expense;

import com.hd_solutions.accounting_service.expense.dto.CreateExpenseRequest;
import com.hd_solutions.accounting_service.expense.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/api/productions/{productionId}/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse create(
            @PathVariable UUID productionId,
            @RequestBody CreateExpenseRequest request) {
        return expenseService.create(productionId, request);
    }

    @GetMapping("/api/productions/{productionId}/expenses")
    public List<ExpenseResponse> findByProductionId(
            @PathVariable UUID productionId,
            @RequestParam(required = false) UUID categoryId) {
        return expenseService.findByProductionId(productionId, categoryId);
    }

    @PutMapping("/api/expenses/{id}")
    public ExpenseResponse update(
            @PathVariable UUID id,
            @RequestBody CreateExpenseRequest request) {
        return expenseService.update(id, request);
    }

    @DeleteMapping("/api/expenses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        expenseService.delete(id);
    }
}
