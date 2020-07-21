package com.ovi.user.payload.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpRequest {

    @NotBlank(message = "Please enter your full name")
    private String fullName;

    @NotBlank(message = "Password field is required")
    private String password;

    @Column(unique = true)
    @Email(message = "Please enter your email")
    private String email;
}