package com.hd_solutions.accounting_service.expense;

import com.hd_solutions.accounting_service.budget.BudgetCategory;
import com.hd_solutions.accounting_service.production.Production;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_category_id")
    private BudgetCategory budgetCategory;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    private LocalDate expenseDate;

    private String vendorName;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Expense(Production production, BudgetCategory budgetCategory, String description,
                   BigDecimal amount, LocalDate expenseDate, String vendorName) {
        this.production = production;
        this.budgetCategory = budgetCategory;
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.vendorName = vendorName;
    }
}
