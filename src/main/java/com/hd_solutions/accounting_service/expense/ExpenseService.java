package com.hd_solutions.accounting_service.expense;

import com.hd_solutions.accounting_service.budget.BudgetCategory;
import com.hd_solutions.accounting_service.budget.BudgetCategoryService;
import com.hd_solutions.accounting_service.expense.dto.CreateExpenseRequest;
import com.hd_solutions.accounting_service.expense.dto.ExpenseResponse;
import com.hd_solutions.accounting_service.production.Production;
import com.hd_solutions.accounting_service.production.ProductionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ProductionService productionService;
    private final BudgetCategoryService budgetCategoryService;

    public ExpenseResponse create(UUID productionId, CreateExpenseRequest request) {
        Production production = productionService.getProductionEntity(productionId);

        BudgetCategory budgetCategory = null;
        if (request.budgetCategoryId() != null) {
            budgetCategory = budgetCategoryService.getBudgetCategoryEntity(request.budgetCategoryId());
            // Verify the budget category belongs to this production
            if (!budgetCategory.getProduction().getId().equals(productionId)) {
                throw new IllegalArgumentException("Budget category does not belong to this production");
            }
        }

        Expense expense = new Expense(
                production,
                budgetCategory,
                request.description(),
                request.amount(),
                request.expenseDate(),
                request.vendorName()
        );

        return ExpenseResponse.from(expenseRepository.save(expense));
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> findByProductionId(UUID productionId, UUID categoryId) {
        // Verify production exists
        productionService.getProductionEntity(productionId);

        List<Expense> expenses;
        if (categoryId != null) {
            expenses = expenseRepository.findByProductionIdAndBudgetCategoryId(productionId, categoryId);
        } else {
            expenses = expenseRepository.findByProductionId(productionId);
        }

        return expenses.stream()
                .map(ExpenseResponse::from)
                .toList();
    }

    public ExpenseResponse update(UUID id, CreateExpenseRequest request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));

        if (request.budgetCategoryId() != null) {
            BudgetCategory budgetCategory = budgetCategoryService.getBudgetCategoryEntity(request.budgetCategoryId());
            // Verify the budget category belongs to the same production
            if (!budgetCategory.getProduction().getId().equals(expense.getProduction().getId())) {
                throw new IllegalArgumentException("Budget category does not belong to this production");
            }
            expense.setBudgetCategory(budgetCategory);
        }
        if (request.description() != null) {
            expense.setDescription(request.description());
        }
        if (request.amount() != null) {
            expense.setAmount(request.amount());
        }
        if (request.expenseDate() != null) {
            expense.setExpenseDate(request.expenseDate());
        }
        if (request.vendorName() != null) {
            expense.setVendorName(request.vendorName());
        }

        return ExpenseResponse.from(expenseRepository.save(expense));
    }

    public void delete(UUID id) {
        if (!expenseRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }
}
