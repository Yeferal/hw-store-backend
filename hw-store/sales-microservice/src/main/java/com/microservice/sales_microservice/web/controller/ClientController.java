package com.microservice.sales_microservice.web.controller;


import com.microservice.sales_microservice.domain.dto.ClientDTO;
import com.microservice.sales_microservice.domain.dto.SaleDTO;
import com.microservice.sales_microservice.domain.service.ClientService;
import com.microservice.sales_microservice.source.exception.ExistRegisterException;
import com.microservice.sales_microservice.source.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
