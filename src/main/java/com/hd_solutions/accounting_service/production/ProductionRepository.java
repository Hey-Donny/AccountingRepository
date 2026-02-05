package com.hd_solutions.accounting_service.production;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductionRepository extends JpaRepository<Production, UUID> {
}
