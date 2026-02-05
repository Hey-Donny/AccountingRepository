package com.hd_solutions.accounting_service.production;

import com.hd_solutions.accounting_service.production.dto.CreateProductionRequest;
import com.hd_solutions.accounting_service.production.dto.ProductionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productions")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionService productionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductionResponse create(@RequestBody CreateProductionRequest request) {
        return productionService.create(request);
    }

    @GetMapping
    public List<ProductionResponse> findAll() {
        return productionService.findAll();
    }

    @GetMapping("/{id}")
    public ProductionResponse findById(@PathVariable UUID id) {
        return productionService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductionResponse update(@PathVariable UUID id, @RequestBody CreateProductionRequest request) {
        return productionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        productionService.delete(id);
    }
}
