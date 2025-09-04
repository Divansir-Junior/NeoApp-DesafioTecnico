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

    private void validateCellPhone(String cellPhone) {
        if (cellPhone == null) {
            throw new IllegalArgumentException("Número de celular não pode ser nulo");
        }

        String regex = "^\\d{11}$";
        if (!cellPhone.matches(regex)) {
            throw new IllegalArgumentException("Número de celular inválido. Formato esperado: 11 dígitos sem espaços ou símbolos.");
        }
    }

    private void validateCep(String cep) {
        if (cep == null || !cep.matches("^\\d{8}$")) {
            throw new IllegalArgumentException("Formato de CEP inválido. Esperado: 8 dígitos.");
        }
    }



}
