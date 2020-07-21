package com.ovi.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotBlank(message = "Please enter your full name")
    private String fullName;

    @NotBlank(message = "Password field is required")
    private String password;

    @Column(unique = true)
    @Email(message = "Please enter your email")
    private String email;
}
