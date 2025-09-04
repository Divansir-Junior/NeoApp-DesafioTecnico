package com.neoApps.neoApps.repository.specification;

import com.neoApps.neoApps.model.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecifications {

    // FILTRO DE CPF
    public static Specification<Customer> hasCpf(String cpf) {
        return (root, query, cb) -> cpf == null ? null : cb.equal(root.get("cpf"), cpf);
    }

    // FILTRO DE CEP
    public static Specification<Customer> hasCep(String cep) {
        return (root, query, cb) -> cep == null ? null : cb.equal(root.get("cep"), cep);
    }

    // FILTRO DE ESTADO
    public static Specification<Customer> hasState(String state) {
        return (root, query, cb) -> state == null ? null : cb.equal(root.get("state"), state);
    }

    // FILTRO DE NOME
    public static Specification<Customer> nameContains(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    // FILTRO DE EMAIL
    public static Specification<Customer> emailEquals(String email) {
        return (root, query, cb) -> email == null ? null : cb.equal(cb.lower(root.get("email")), email.toLowerCase());
    }
}
