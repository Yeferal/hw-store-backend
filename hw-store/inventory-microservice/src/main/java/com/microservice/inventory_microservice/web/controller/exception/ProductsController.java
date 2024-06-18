package com.microservice.inventory_microservice.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Objects product) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
