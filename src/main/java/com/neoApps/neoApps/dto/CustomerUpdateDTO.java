package com.neoApps.neoApps.dto;

import jakarta.validation.constraints.Email;
import java.time.LocalDate;

/**
 * DTO para atualizar dados do cliente.
 * Todos os campos são opcionais.
 */
public record CustomerUpdateDTO(
        String name,
        String password,
        String cpf,
        String cep,
        String cellPhone,
        LocalDate dateOfBirth,
        @Email String email,
        String street,
        String city,
        Integer numberHouse,
        String state,   // opcional
        String gender   // opcional
) {}
