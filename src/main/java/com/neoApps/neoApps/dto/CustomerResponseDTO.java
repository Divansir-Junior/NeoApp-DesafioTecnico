package com.neoApps.neoApps.dto;

import com.neoApps.neoApps.model.Customer;

import java.time.LocalDate;

public record CustomerResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        String cellPhone,
        String street,
        String city,
        int numberHouse,
        String state,
        String gender,
        LocalDate dateOfBirth,
        int age
) {
    public CustomerResponseDTO(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getEmail(),
                customer.getCellPhone(),
                customer.getStreet(),
                customer.getCity(),
                customer.getNumberHouse(),
                customer.getState() != null ? customer.getState().name() : null,
                customer.getGender() != null ? customer.getGender().name() : null,
                customer.getDateOfBirth(),
                customer.getAge()
        );
    }
}
