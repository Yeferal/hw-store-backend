package com.microservice.sales_microservice.domain.serviceimpl;

import com.microservice.admin_microservice.domain.dto.PaginateAndSortDTO;
import com.microservice.sales_microservice.domain.dto.ClientDTO;
import com.microservice.sales_microservice.domain.dto.SaleDTO;
import com.microservice.sales_microservice.domain.map.ClientMapper;
import com.microservice.sales_microservice.domain.map.SaleMapper;
import com.microservice.sales_microservice.domain.repository.ClientRepository;
import com.microservice.sales_microservice.domain.service.ClientService;
import com.microservice.sales_microservice.domain.specs.SaleSpecifications;
import com.microservice.sales_microservice.persistence.model.Client;
import com.microservice.sales_microservice.persistence.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private SaleMapper saleMapper;

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

    @Override
    public Page<SaleDTO> findAllPendingPayment(PaginateAndSortDTO paginateAndSortDTO) {
        Sort sort = Sort.by(Sort.Direction.ASC, paginateAndSortDTO.getSortField());
        if (paginateAndSortDTO.getSortOrder().equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, paginateAndSortDTO.getSortField());
        }

        Pageable pageable = PageRequest.of(paginateAndSortDTO.getPage(), paginateAndSortDTO.getSize(), sort);
        Specification<Sale> specs = Specification.where(null);

        if (paginateAndSortDTO.getSearchValue() != null && !paginateAndSortDTO.getSearchValue().isEmpty()){
            String[] values = paginateAndSortDTO.getSearchValue().split(" ");
            for (String value : values) {
                specs = specs.or(SaleSpecifications.idContains(value.toUpperCase()));
                specs = specs.or(SaleSpecifications.nameContains(value.toUpperCase()));
                specs = specs.or(SaleSpecifications.addressContains(value.toUpperCase()));
            }
        }
        specs = specs.and(SaleSpecifications.isPendingPayment(true));

        Page<Sale> salePage = clientRepository.getAllSalePaginate(specs, pageable);
        List<SaleDTO> saleDTOs = salePage.getContent().stream()
                .map(saleMapper::toDto)
                .toList();
        return new PageImpl<>(saleDTOs, pageable, salePage.getTotalElements());
    }
}
