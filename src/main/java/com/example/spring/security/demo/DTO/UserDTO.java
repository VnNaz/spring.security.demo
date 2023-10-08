package com.example.spring.security.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Size(min = 5, message = "username length required > 5 char/num")
    @NotBlank(message = "username cannot be empty")
    private String username;

    // for Bcrypt require exactly 68 length
    @Size(min = 5, message = "password length required > 5 char/num")
    @NotBlank(message = "password cannot be empty")
    private String password;

    @NotBlank(message = "first name cannot be empty")
    private String firstName;

    @NotBlank(message = "last name cannot be empty")
    private String lastName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
}
