package com.neoApps.neoApps.controller;

import com.neoApps.neoApps.model.Customer;
import com.neoApps.neoApps.repository.CustomerRepository;
import com.neoApps.neoApps.security.JwtUtil;
import com.neoApps.neoApps.security.Password;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Autenticação via JWT",
            description = "Valida o token JWT enviado no header 'Authorization' no formato 'Bearer <token>'.\n\n" +
                    "Caso o token seja inválido ou expirado, retorna erro 401.\n"

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token válido, requisição autorizada"),
            @ApiResponse(responseCode = "401", description = "Token inválido ou expirado", content = @Content)
    })
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
