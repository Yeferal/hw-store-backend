package com.microservice.sales_microservice.persistence.prepository;

import com.microservice.sales_microservice.domain.repository.ProductRepository;
import com.microservice.sales_microservice.persistence.crud.ProductCRUDRepository;
import com.microservice.sales_microservice.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class ProductPRepository implements ProductRepository {

    @Autowired
    private ProductCRUDRepository productCRUDRepository;

    @Override
    public Product updateProduct(Product product) {
        return productCRUDRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productCRUDRepository.findById(id);
    }

    @Override
    public Product updateStock(Long productId, BigDecimal amount) {
        Product product = productCRUDRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setAmount(amount);
        return productCRUDRepository.save(product);
    }
}
