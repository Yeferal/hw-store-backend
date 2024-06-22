package com.microservice.inventory_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Value
public class PaginateAndSortDTO implements Serializable {

    int page;
    int size;
    String sortOrder;
    String sortField;
    String searchValue;
}
