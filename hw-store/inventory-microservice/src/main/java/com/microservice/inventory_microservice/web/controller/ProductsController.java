package com.microservice.inventory_microservice.web.controller;

import com.microservice.inventory_microservice.domain.dto.*;
import com.microservice.inventory_microservice.domain.service.ProductService;
import com.microservice.inventory_microservice.persistence.model.Brand;
import com.microservice.inventory_microservice.persistence.model.Category;
import com.microservice.inventory_microservice.source.exception.ExistRegisterException;
import com.microservice.inventory_microservice.source.exception.RegisterNotFoundException;
import com.microservice.inventory_microservice.source.utils.ConstData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDefaultDTO> addProduct(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @ModelAttribute ProductBodyDTO productBodyDTO
    ) {
        try {
            ProductDefaultDTO productDefaultDTO = productService.addProduct(productBodyDTO, authorizationHeader.substring(7));
            if (productDefaultDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(productDefaultDTO);
        } catch (ExistRegisterException | RegisterNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductDefaultDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(required = false) String searchValue
    ) {
        try {
            PaginateAndSortDTO paginateAndSortDTO = new PaginateAndSortDTO(page, size, sortOrder, sortField, searchValue);
            Page<ProductDefaultDTO> products = productService.getAllProducts(paginateAndSortDTO);
            if (products == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDefaultDTO> getProduct(
            @PathVariable Long id
    ) {
        try {
            ProductDefaultDTO productDefaultDTO = productService.getProduct(id);
            if (productDefaultDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(productDefaultDTO);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/codes/{code}")
    public ResponseEntity<ProductDefaultDTO> getProductCode(
            @PathVariable String code
    ) {
        try {
            ProductDefaultDTO productDefaultDTO = productService.getProductByCode(code);
            if (productDefaultDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(productDefaultDTO);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/brands")
    public ResponseEntity<BrandDTO> addBrand(
            @Valid @ModelAttribute BrandBodyDTO brandBodyDTO
    ) {
        try {
            BrandDTO brandDTO = productService.addBrand(brandBodyDTO);
            if (brandDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(brandDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/brands")
    public ResponseEntity<Page<BrandDTO>> getBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(required = false) String searchValue
    ) {
        try {
            PaginateAndSortDTO paginateAndSortDTO = new PaginateAndSortDTO(page, size, sortOrder, sortField, searchValue);
            Page<BrandDTO> brandsDTO = productService.getBrands(paginateAndSortDTO);
            if (brandsDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(brandsDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all-brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        try {
            List<BrandDTO> brandsDTO = productService.getAllBrands();
            if (brandsDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(brandsDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> addCategory(
            @Valid @ModelAttribute CategoryBodyDTO categoryBodyDTO
    ) {
        try {
            CategoryDTO categoryDTO = productService.addCategory(categoryBodyDTO);
            if (categoryDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryDTO>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(required = false) String searchValue
    ) {
        try {
            PaginateAndSortDTO paginateAndSortDTO = new PaginateAndSortDTO(page, size, sortOrder, sortField, searchValue);
            Page<CategoryDTO> categoriesDTO = productService.getCategories(paginateAndSortDTO);
            if (categoriesDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(categoriesDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        try {
            List<CategoryDTO> categoriesDTO = productService.getAllCategories();
            if (categoriesDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(categoriesDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/measurement-units")
    public ResponseEntity<MeasurementUnitDTO> addMeasurementUnit(
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

    @GetMapping("/measurement-units")
    public ResponseEntity<Page<MeasurementUnitDTO>> getMeasurementUnit(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(required = false) String searchValue
    ) {
        try {
            PaginateAndSortDTO paginateAndSortDTO = new PaginateAndSortDTO(page, size, sortOrder, sortField, searchValue);
            Page<MeasurementUnitDTO> measurementsUnitDTO = productService.getMeasurementUnits(paginateAndSortDTO);
            if (measurementsUnitDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(measurementsUnitDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all-measurement-units")
    public ResponseEntity<List<MeasurementUnitDTO>> getAllMeasurementUnit() {
        try {
            List<MeasurementUnitDTO> measurementsUnitDTO = productService.getAllMeasurementUnits();
            if (measurementsUnitDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(measurementsUnitDTO);
        } catch (ExistRegisterException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
