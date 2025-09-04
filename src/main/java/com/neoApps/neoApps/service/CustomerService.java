package com.neoApps.neoApps.service;

import com.neoApps.neoApps.dto.CustomerCreateDTO;
import com.neoApps.neoApps.dto.CustomerResponseDTO;
import com.neoApps.neoApps.enums.Gender;
import com.neoApps.neoApps.enums.States;
import com.neoApps.neoApps.model.Customer;
import com.neoApps.neoApps.repository.CustomerRepository;
import com.neoApps.neoApps.repository.specification.CustomerSpecifications;
import com.neoApps.neoApps.validation.CustomerValidation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerValidation validator;
    private final CustomerRepository repository;

    public CustomerService(CustomerValidation validator, CustomerRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    // CRIA UM CLIENTE
    public CustomerResponseDTO create(CustomerCreateDTO dto) {
        Customer customer = fromCreateDTO(dto);
        validator.validate(customer);
        return toDTO(repository.save(customer));
    }

    // LISTA TODOS OS CLIENTES
    public List<CustomerResponseDTO> listAllDTO() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // Transferencia DTO para segurança
    private Customer fromCreateDTO(CustomerCreateDTO dto) {
        Customer c = new Customer();
        c.setName(dto.name());
        c.setPassword(dto.password());
        c.setCpf(dto.cpf());
        c.setCep(dto.cep());
        c.setCellPhone(dto.cellPhone());
        c.setDateOfBirth(dto.dateOfBirth());
        c.setAge(Period.between(dto.dateOfBirth(), LocalDate.now()).getYears());
        c.setEmail(dto.email());
        c.setStreet(dto.street());
        c.setCity(dto.city());
        c.setNumberHouse(dto.numberHouse());
        c.setState(dto.state() != null ? States.valueOf(dto.state()) : null);
        c.setGender(dto.gender() != null ? Gender.valueOf(dto.gender()) : null);
        return c;
    }

    private CustomerResponseDTO toDTO(Customer c) {
        return new CustomerResponseDTO(c);
    }
}
