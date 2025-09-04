package com.neoApps.neoApps.controller;

import com.neoApps.neoApps.model.Customer;
import com.neoApps.neoApps.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerValidation customerValidation;

    // Endpoint de teste para criar um cliente
    @PostMapping("/test")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerValidation.validate(customer); // chama validação
            return ResponseEntity.ok("Cliente válido!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}
