package com.hd_solutions.accounting_service.budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, UUID> {

    List<BudgetCategory> findByProductionId(UUID productionId);
}
