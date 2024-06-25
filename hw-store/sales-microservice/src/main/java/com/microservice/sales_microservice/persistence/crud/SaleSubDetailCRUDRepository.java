package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.SaleSubDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleSubDetailCRUDRepository extends JpaRepository<SaleSubDetail, Long> {
}