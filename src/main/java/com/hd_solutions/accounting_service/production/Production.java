package com.hd_solutions.accounting_service.production;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "productions")
@Getter
@Setter
@NoArgsConstructor
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductionStatus status = ProductionStatus.DEVELOPMENT;

    private LocalDate startDate;

    private LocalDate endDate;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Production(String name, String description, ProductionStatus status, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.status = status != null ? status : ProductionStatus.DEVELOPMENT;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
