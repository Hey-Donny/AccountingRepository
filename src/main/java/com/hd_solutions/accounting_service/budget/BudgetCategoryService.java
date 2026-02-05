package com.hd_solutions.accounting_service.budget;

import com.hd_solutions.accounting_service.budget.dto.BudgetCategoryResponse;
import com.hd_solutions.accounting_service.budget.dto.CreateBudgetCategoryRequest;
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
public class BudgetCategoryService {

    private final BudgetCategoryRepository budgetCategoryRepository;
    private final ProductionService productionService;

    public BudgetCategoryResponse create(UUID productionId, CreateBudgetCategoryRequest request) {
        Production production = productionService.getProductionEntity(productionId);

        BudgetCategory category = new BudgetCategory(
                production,
                request.name(),
                request.type(),
                request.budgetedAmount()
        );

        return BudgetCategoryResponse.from(budgetCategoryRepository.save(category));
    }

    @Transactional(readOnly = true)
    public List<BudgetCategoryResponse> findByProductionId(UUID productionId) {
        // Verify production exists
        productionService.getProductionEntity(productionId);

        return budgetCategoryRepository.findByProductionId(productionId).stream()
                .map(BudgetCategoryResponse::from)
                .toList();
    }

    public BudgetCategoryResponse update(UUID id, CreateBudgetCategoryRequest request) {
        BudgetCategory category = budgetCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget category not found with id: " + id));

        if (request.name() != null) {
            category.setName(request.name());
        }
        if (request.type() != null) {
            category.setType(request.type());
        }
        if (request.budgetedAmount() != null) {
            category.setBudgetedAmount(request.budgetedAmount());
        }

        return BudgetCategoryResponse.from(budgetCategoryRepository.save(category));
    }

    public void delete(UUID id) {
        if (!budgetCategoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Budget category not found with id: " + id);
        }
        budgetCategoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public BudgetCategory getBudgetCategoryEntity(UUID id) {
        return budgetCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget category not found with id: " + id));
    }
}
