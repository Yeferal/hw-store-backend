package com.microservice.sales_microservice.domain.repository;

import com.microservice.sales_microservice.persistence.model.Client;
import com.microservice.sales_microservice.persistence.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Optional<Client> getClientById(Long id);
    Optional<Client> getClientByNit(String nit);
    Page<Sale> getAllSalePaginate(Specification<Sale> specs, Pageable pageable);
    List<Sale> getAllSaleSpecs(Specification<Sale> specs);
}
