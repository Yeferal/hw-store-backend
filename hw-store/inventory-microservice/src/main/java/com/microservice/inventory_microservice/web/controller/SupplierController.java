package com.microservice.inventory_microservice.web.controller;

import com.microservice.inventory_microservice.domain.dto.SupplierBodyDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController {

    @PostMapping
    public ResponseEntity<?> createSupplier(
            @Valid @RequestBody SupplierBodyDTO supplierBodyDTO
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
