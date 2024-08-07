package com.microservice.sales_microservice.persistence.prepository;

import com.microservice.sales_microservice.domain.repository.ClientRepository;
import com.microservice.sales_microservice.persistence.crud.ClientCRUDRepository;
import com.microservice.sales_microservice.persistence.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientPRepository implements ClientRepository {

    @Autowired
    private ClientCRUDRepository clientCRUDRepository;

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientCRUDRepository.findById(id);
    }

    @Override
    public Optional<Client> getClientByNit(String nit) {
        return clientCRUDRepository.findByNit(nit);
    }
}
