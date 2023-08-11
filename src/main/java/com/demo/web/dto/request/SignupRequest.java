package com.demo.web.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {
    @NotBlank(message = "email is required")
    @Size(max = 50, message = "email is max with 50 character")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "username is required")
    @Size(max = 50, message = "username is max with 50 character")
    private String username;
    @NotBlank(message = "password is required")
    @Size(min = 5, max = 40, message = "password is min with 5 character and max with 40 character")
    private String password;
}
