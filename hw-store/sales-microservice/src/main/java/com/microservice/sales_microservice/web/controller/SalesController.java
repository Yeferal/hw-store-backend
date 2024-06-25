package com.microservice.sales_microservice.web.controller;

import com.microservice.sales_microservice.domain.dto.SaleBodyDTO;
import com.microservice.sales_microservice.domain.dto.SaleDTO;
import com.microservice.sales_microservice.domain.service.SalesService;
import com.microservice.sales_microservice.source.exception.ExistRegisterException;
import com.microservice.sales_microservice.source.exception.RegisterNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping
    public ResponseEntity<?> createSale(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody SaleBodyDTO saleBodyDTO
    ) {
        try {
            SaleDTO saleDTO = salesService.createSale(saleBodyDTO, authorizationHeader.substring(7));

            if (saleDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(saleDTO);
        } catch (ExistRegisterException | RegisterNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
