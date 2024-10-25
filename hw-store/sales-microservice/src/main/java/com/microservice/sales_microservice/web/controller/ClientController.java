package com.microservice.sales_microservice.web.controller;


import com.microservice.admin_microservice.domain.dto.PaginateAndSortDTO;
import com.microservice.sales_microservice.domain.dto.ClientDTO;
import com.microservice.sales_microservice.domain.dto.SaleDTO;
import com.microservice.sales_microservice.domain.service.ClientService;
import com.microservice.sales_microservice.source.exception.ExistRegisterException;
import com.microservice.sales_microservice.source.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{nit}")
    public ResponseEntity<ClientDTO> createClient(@PathVariable String nit) {
        try {
            ClientDTO clientDTO = clientService.fingByNit(nit);

            if (clientDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
        } catch (ExistRegisterException | RegisterNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/payments-pending")
    public ResponseEntity<Page<SaleDTO>> getPaymentPending(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "date") String sortField,
            @RequestParam(required = false) String searchValue
    ) {
        try {
            PaginateAndSortDTO paginateAndSortDTO = new PaginateAndSortDTO(page, size, sortOrder, sortField, searchValue);
            Page<SaleDTO> sales = clientService.findAllPendingPayment(paginateAndSortDTO);
            if (sales == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(sales);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
