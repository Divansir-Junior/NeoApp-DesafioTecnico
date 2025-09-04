package com.neoApps.neoApps.service;

import com.neoApps.neoApps.dto.CustomerCreateDTO;
import com.neoApps.neoApps.dto.CustomerResponseDTO;
import com.neoApps.neoApps.dto.CustomerUpdateDTO;
import com.neoApps.neoApps.enums.Gender;
import com.neoApps.neoApps.enums.States;
import com.neoApps.neoApps.model.Customer;
import com.neoApps.neoApps.repository.CustomerRepository;
import com.neoApps.neoApps.repository.specification.CustomerSpecifications;
import com.neoApps.neoApps.security.Password;
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

    // --- Cria um cliente ---
    public CustomerResponseDTO create(CustomerCreateDTO dto) {
        Customer customer = fromCreateDTO(dto);
        validator.validate(customer);
        return toDTO(repository.save(customer));
    }

    // --- Lista todos os clientes ---
    public List<CustomerResponseDTO> listAllDTO() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // --- Exclui por ID ---
    public Optional<CustomerResponseDTO> deleteById(Long id) {
        return repository.findById(id)
                .map(c -> {
                    repository.delete(c);
                    return toDTO(c);
                });
    }

    // --- Busca dinâmica ---
    public List<CustomerResponseDTO> search(String cpf, String cep, String state, String name, String email) {
        Specification<Customer> spec = Specification
                .where(CustomerSpecifications.hasCpf(cpf))
                .and(CustomerSpecifications.hasCep(cep))
                .and(CustomerSpecifications.hasState(state))
                .and(CustomerSpecifications.nameContains(name))
                .and(CustomerSpecifications.emailEquals(email));

        return repository.findAll(spec)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // --- Atualiza cliente ---
    public Optional<CustomerResponseDTO> update(Long id, CustomerUpdateDTO dto) {
        return repository.findById(id)
                .map(customer -> {
                    if(dto.name() != null) customer.setName(dto.name());
                    if(dto.password() != null) customer.setPassword(Password.encrypt(dto.password()));
                    if(dto.cpf() != null) customer.setCpf(dto.cpf());
                    if(dto.cep() != null) customer.setCep(dto.cep());
                    if(dto.cellPhone() != null) customer.setCellPhone(dto.cellPhone());
                    if(dto.dateOfBirth() != null) customer.setDateOfBirth(dto.dateOfBirth());
                    if(dto.email() != null) customer.setEmail(dto.email());
                    if(dto.street() != null) customer.setStreet(dto.street());
                    if(dto.city() != null) customer.setCity(dto.city());
                    if(dto.numberHouse() != null) customer.setNumberHouse(dto.numberHouse());
                    if(dto.state() != null) customer.setState(States.valueOf(dto.state()));
                    if(dto.gender() != null) customer.setGender(Gender.valueOf(dto.gender()));
                    customer.setAge(calculateAge(customer.getDateOfBirth()));
                    repository.save(customer);
                    return new CustomerResponseDTO(customer);
                });
    }

    // --- Converter DTO de criação para entidade ---
    private Customer fromCreateDTO(CustomerCreateDTO dto) {
        Customer c = new Customer();
        c.setName(dto.name());
        c.setPassword(dto.password() != null ? Password.encrypt(dto.password()) : null);
        c.setCpf(dto.cpf());
        c.setCep(dto.cep());
        c.setCellPhone(dto.cellPhone());
        c.setDateOfBirth(dto.dateOfBirth());
        c.setAge(calculateAge(dto.dateOfBirth()));
        c.setEmail(dto.email());
        c.setStreet(dto.street());
        c.setCity(dto.city());
        c.setNumberHouse(dto.numberHouse());
        c.setState(dto.state() != null ? States.valueOf(dto.state()) : null);
        c.setGender(dto.gender() != null ? Gender.valueOf(dto.gender()) : null);
        return c;
    }

    // --- Converter entidade para DTO ---
    private CustomerResponseDTO toDTO(Customer c) {
        return new CustomerResponseDTO(c);
    }

    // --- Calcula idade ---
    private int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
