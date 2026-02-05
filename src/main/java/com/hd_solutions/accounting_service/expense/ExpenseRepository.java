package com.hd_solutions.accounting_service.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    List<Expense> findByProductionId(UUID productionId);

    List<Expense> findByProductionIdAndBudgetCategoryId(UUID productionId, UUID budgetCategoryId);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.budgetCategory.id = :categoryId")
    BigDecimal sumByBudgetCategoryId(@Param("categoryId") UUID categoryId);
}
