package com.neoApps.neoApps.validation;


import com.neoApps.neoApps.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {

    public void validate(Customer customer) {
        validateCpf(customer.getCpf());
    }

    // --- Identificação ---
    private void validateCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("Formato de CPF inválido. Esperado: 11 dígitos.");
        }
    }


}
