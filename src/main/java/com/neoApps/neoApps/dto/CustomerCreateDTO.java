package com.neoApps.neoApps.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CustomerCreateDTO(
        @NotBlank String name,
        @NotBlank String password,
        @NotBlank String cpf,
        @NotBlank String cep,
        @NotBlank String cellPhone,
        @NotNull LocalDate dateOfBirth,
        @NotBlank @Email String email,
        @NotBlank String street,
        @NotBlank String city,
        @NotNull int numberHouse,
        String state, // opcional
        String gender // opcional
) {}
