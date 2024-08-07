package com.microservice.inventory_microservice.web.controller;

import com.microservice.inventory_microservice.domain.dto.SupplierBodyDTO;
import com.microservice.inventory_microservice.domain.dto.SupplierDTO;
import com.microservice.inventory_microservice.domain.service.SupplierService;
import com.microservice.inventory_microservice.source.exception.ExistRegisterException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<?> createSupplier(
            @Valid @RequestBody SupplierBodyDTO supplierBodyDTO
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplier(@PathVariable Long id) {
        try {
            SupplierDTO supplierDTO = supplierService.getSupplier(id);
            if (supplierDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(supplierDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
