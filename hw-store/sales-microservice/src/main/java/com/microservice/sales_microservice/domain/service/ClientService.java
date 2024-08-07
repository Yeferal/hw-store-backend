package com.microservice.sales_microservice.domain.service;

import com.microservice.sales_microservice.domain.dto.ClientDTO;

public interface ClientService {

    ClientDTO findById(Long id);
    ClientDTO fingByNit(String nit);
}
