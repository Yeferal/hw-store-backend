package com.microservice.sales_microservice.domain.service;

import com.microservice.admin_microservice.domain.dto.PaginateAndSortDTO;
import com.microservice.sales_microservice.domain.dto.ClientDTO;
import com.microservice.sales_microservice.domain.dto.SaleDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {

    ClientDTO findById(Long id);
    ClientDTO fingByNit(String nit);
    Page<SaleDTO> findAllPendingPayment(PaginateAndSortDTO paginateAndSortDTO);
}
