package com.hd_solutions.accounting_service.budget;

import com.hd_solutions.accounting_service.production.Production;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "budget_categories")
@Getter
@Setter
@NoArgsConstructor
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetCategoryType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal budgetedAmount;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public BudgetCategory(Production production, String name, BudgetCategoryType type, BigDecimal budgetedAmount) {
        this.production = production;
        this.name = name;
        this.type = type;
        this.budgetedAmount = budgetedAmount;
    }
}
