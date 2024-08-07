package com.microservice.sales_microservice.domain.serviceimpl;

import com.microservice.sales_microservice.domain.dto.ClientDTO;
import com.microservice.sales_microservice.domain.map.ClientMapper;
import com.microservice.sales_microservice.domain.repository.ClientRepository;
import com.microservice.sales_microservice.domain.service.ClientService;
import com.microservice.sales_microservice.persistence.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDTO findById(Long id) {
        Optional<Client> client = clientRepository.getClientById(id);
        return client.map(value -> clientMapper.toDto(value)).orElse(null);
    }

    @Override
    public ClientDTO fingByNit(String nit) {
        Optional<Client> client = clientRepository.getClientByNit(nit);
        return client.map(value -> clientMapper.toDto(value)).orElse(null);
    }
}
