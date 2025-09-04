package com.neoApps.neoApps.controller;

import com.neoApps.neoApps.dto.CustomerCreateDTO;
import com.neoApps.neoApps.dto.CustomerResponseDTO;
import com.neoApps.neoApps.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "Cria um novo cliente", description = "Recebe os dados de um cliente e cria um registro no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerCreateDTO dto) {
        return ResponseEntity.ok(customerService.create(dto));
    }

    // --- Listar todos ---
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> listAll() {
        List<CustomerResponseDTO> response = customerService.listAllDTO();
        return ResponseEntity.ok(response);
    }

    //  EXCLUIR POR ID
    @Operation(summary = "Exclui por ID", description = "Deleta um registro pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso",
                    content = @Content), // sem corpo retornado
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> delete(@PathVariable Long id) {
        return customerService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // BUSCA DINÃMICA
    @Operation(summary = "Filtro dinâmico", description = " Permite buscar por : | CPF|" +
            "CEP| Estado | Nome | Email")
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

}
