package com.neoApps.neoApps.controller;

import com.neoApps.neoApps.dto.CustomerCreateDTO;
import com.neoApps.neoApps.dto.CustomerResponseDTO;
import com.neoApps.neoApps.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // --- Criar um cliente ---
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerCreateDTO dto) {
        return ResponseEntity.ok(customerService.create(dto));
    }

    // --- Listar todos ---
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> listAll() {
        List<CustomerResponseDTO> response = customerService.listAllDTO();
        return ResponseEntity.ok(response);
    }

    // --- Busca dinâmica com filtros opcionais ---
    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDTO>> search(
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String cep,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        List<CustomerResponseDTO> response = customerService.search(cpf, cep, state, name, email);
        return ResponseEntity.ok(response);
    }

    // --- Deletar por ID ---
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> delete(@PathVariable Long id) {
        return customerService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
