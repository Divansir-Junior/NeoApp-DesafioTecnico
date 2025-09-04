package com.neoApps.neoApps.model;

import com.neoApps.neoApps.enums.Gender;
import com.neoApps.neoApps.enums.States;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cpf"}),
                @UniqueConstraint(columnNames = {"cellPhone"}),
                @UniqueConstraint(columnNames = {"email"})
        }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(min = 8, max = 64, message = "A senha deve ter entre 8 e 64 caracteres")
    @Column(nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private States state;

    @NotBlank
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank
    @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 8 dígitos")
    @Column(nullable = false, length = 8)
    private String cep;

    @NotBlank
    @Size(min = 11, max = 11, message = "O celular deve ter exatamente 11 dígitos")
    @Column(nullable = false, unique = true, length = 11)
    private String cellPhone;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Min(value = 1, message = "Idade deve ser maior que zero")
    @Max(value = 120, message = "Idade máxima permitida é 120 anos")
    @Column(nullable = false)
    private int age;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String street;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @NotNull
    @Min(1)
    @Max(9999)
    @Column(nullable = false)
    private int numberHouse;
}
