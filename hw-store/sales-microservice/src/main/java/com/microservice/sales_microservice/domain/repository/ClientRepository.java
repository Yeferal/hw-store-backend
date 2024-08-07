package com.microservice.sales_microservice.domain.repository;

import com.microservice.sales_microservice.persistence.model.Client;

import java.util.Optional;

public interface ClientRepository {

    Optional<Client> getClientById(Long id);
    Optional<Client> getClientByNit(String nit);
}
