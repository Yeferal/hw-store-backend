package com.microservice.sales_microservice.persistence.prepository;

import com.microservice.sales_microservice.domain.repository.ClientRepository;
import com.microservice.sales_microservice.domain.repository.SalesRepository;
import com.microservice.sales_microservice.persistence.crud.ClientCRUDRepository;
import com.microservice.sales_microservice.persistence.crud.SaleCRUDRepository;
import com.microservice.sales_microservice.persistence.model.Client;
import com.microservice.sales_microservice.persistence.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientPRepository implements ClientRepository {

    @Autowired
    private ClientCRUDRepository clientCRUDRepository;
    @Autowired
    private SaleCRUDRepository saleCRUDRepository;

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientCRUDRepository.findById(id);
    }

    @Override
    public Optional<Client> getClientByNit(String nit) {
        return clientCRUDRepository.findByNit(nit);
    }

    @Override
    public Page<Sale> getAllSalePaginate(Specification<Sale> specs, Pageable pageable) {
        return saleCRUDRepository.findAll(specs, pageable);
    }

    @Override
    public List<Sale> getAllSaleSpecs(Specification<Sale> specs) {
        return saleCRUDRepository.findAll(specs);
    }
}
