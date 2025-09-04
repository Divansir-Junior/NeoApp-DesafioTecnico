package com.neoApps.neoApps.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {

    // Instância do encoder BCrypt
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Criptografa uma senha em texto puro.
     * @param rawPassword senha em texto puro
     * @return senha criptografada
     */
    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * Verifica se a senha em texto puro corresponde à senha criptografada.
     * @param rawPassword senha em texto puro
     * @param encodedPassword senha criptografada
     * @return true se forem equivalentes
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
