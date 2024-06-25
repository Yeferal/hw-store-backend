package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleCRUDRepository extends JpaRepository<Sale, Long> {
}