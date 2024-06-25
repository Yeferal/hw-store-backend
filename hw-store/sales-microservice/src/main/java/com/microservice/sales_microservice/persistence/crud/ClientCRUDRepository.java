package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientCRUDRepository extends JpaRepository<Client, Long> {
  Optional<Client> findByNit(String nit);
}