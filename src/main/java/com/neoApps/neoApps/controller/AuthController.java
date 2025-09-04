package com.neoApps.neoApps.controller;

import com.neoApps.neoApps.model.Customer;
import com.neoApps.neoApps.repository.CustomerRepository;
import com.neoApps.neoApps.security.JwtUtil;
import com.neoApps.neoApps.security.Password;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável pelo login e geração de token JWT.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomerRepository customerRepository;

    public AuthController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Endpoint de login.
     * @param email do usuário
     * @param password senha do usuário
     * @return token JWT
     */
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {

        // Busca o usuário pelo email
        Customer customer = customerRepository.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica senha
        if (!Password.matches(password, customer.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        // Gera token JWT
        return JwtUtil.generateToken(email);
    }
}
