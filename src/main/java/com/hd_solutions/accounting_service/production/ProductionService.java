package com.hd_solutions.accounting_service.production;

import com.hd_solutions.accounting_service.production.dto.CreateProductionRequest;
import com.hd_solutions.accounting_service.production.dto.ProductionResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductionService {

    private final ProductionRepository productionRepository;

    public ProductionResponse create(CreateProductionRequest request) {
        Production production = new Production(
                request.name(),
                request.description(),
                request.status(),
                request.startDate(),
                request.endDate()
        );
        return ProductionResponse.from(productionRepository.save(production));
    }

    @Transactional(readOnly = true)
    public List<ProductionResponse> findAll() {
        return productionRepository.findAll().stream()
                .map(ProductionResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductionResponse findById(UUID id) {
        return productionRepository.findById(id)
                .map(ProductionResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("Production not found with id: " + id));
    }

    public ProductionResponse update(UUID id, CreateProductionRequest request) {
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Production not found with id: " + id));

        if (request.name() != null) {
            production.setName(request.name());
        }
        if (request.description() != null) {
            production.setDescription(request.description());
        }
        if (request.status() != null) {
            production.setStatus(request.status());
        }
        if (request.startDate() != null) {
            production.setStartDate(request.startDate());
        }
        if (request.endDate() != null) {
            production.setEndDate(request.endDate());
        }

        return ProductionResponse.from(productionRepository.save(production));
    }

    public void delete(UUID id) {
        if (!productionRepository.existsById(id)) {
            throw new EntityNotFoundException("Production not found with id: " + id);
        }
        productionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Production getProductionEntity(UUID id) {
        return productionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Production not found with id: " + id));
    }
}
