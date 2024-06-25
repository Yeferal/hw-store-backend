package com.microservice.sales_microservice.domain.map;

import com.microservice.sales_microservice.domain.dto.ClientDTO;
import com.microservice.sales_microservice.persistence.model.Client;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client toEntity(ClientDTO clientDTO);

    ClientDTO toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client partialUpdate(ClientDTO clientDTO, @MappingTarget Client client);
}