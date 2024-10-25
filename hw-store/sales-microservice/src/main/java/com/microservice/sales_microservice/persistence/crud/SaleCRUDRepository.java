package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SaleCRUDRepository extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {

}