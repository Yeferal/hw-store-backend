package com.microservice.inventory_microservice.web.controller;

import com.microservice.inventory_microservice.domain.dto.ProductIncomeBodyDTO;
import com.microservice.inventory_microservice.domain.dto.ProductIncomeDTO;
import com.microservice.inventory_microservice.domain.service.ProductIncomeService;
import com.microservice.inventory_microservice.source.exception.ExistRegisterException;
import com.microservice.inventory_microservice.source.exception.RegisterNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/inventories")
public class InventoryController {

    @Autowired
    private ProductIncomeService productIncomeService;

    @PostMapping("/register-incomes")
    public ResponseEntity<ProductIncomeDTO> registerIncome(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody ProductIncomeBodyDTO productIncomeBodyDTO
    ) {
        try {
            ProductIncomeDTO productIncomeDTO = productIncomeService.addIncomeProduct(productIncomeBodyDTO, authorizationHeader.substring(7));
            if (productIncomeDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.ok().body(productIncomeDTO);
        } catch (ExistRegisterException | RegisterNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
