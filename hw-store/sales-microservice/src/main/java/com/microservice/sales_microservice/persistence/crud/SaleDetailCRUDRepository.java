package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDetailCRUDRepository extends JpaRepository<SaleDetail, Long> {
}