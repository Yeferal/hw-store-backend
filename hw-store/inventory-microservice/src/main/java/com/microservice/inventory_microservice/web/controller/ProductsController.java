package com.microservice.inventory_microservice.web.controller;

import com.microservice.inventory_microservice.domain.dto.*;
import com.microservice.inventory_microservice.domain.service.ProductService;
import com.microservice.inventory_microservice.source.exception.ExistRegisterException;
import com.microservice.inventory_microservice.source.utils.ConstData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(
            @Valid @ModelAttribute ProductBodyDTO productBodyDTO
    ) {
        System.out.println(productBodyDTO.toString());
//        Arrays.stream(images).forEach(System.out::println);
//        try {
//
//
//            return new ResponseEntity<>(null, HttpStatus.CREATED);
//        } catch (IOException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/brands")
    public ResponseEntity<?> addBrand(
            @Valid @ModelAttribute BrandBodyDTO brandBodyDTO
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/categories")
    public ResponseEntity<?> addCategory(
            @Valid @ModelAttribute CategoryBodyDTO categoryBodyDTO
    ) {
        try {
            System.out.println(categoryBodyDTO.toString());
            CategoryDTO categoryDTO = productService.addCategory(categoryBodyDTO);
            if (categoryDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
        } catch (ExistRegisterException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/measurement-units")
    public ResponseEntity<?> addMeasurementUnit(
            @Valid @RequestBody MeasurementUnitBodyDTO measurementUnitBodyDTO
    ) {
        try {
            MeasurementUnitDTO measurementUnitDTO = productService.addMeasurementUnit(measurementUnitBodyDTO);
            if (measurementUnitDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(measurementUnitDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
