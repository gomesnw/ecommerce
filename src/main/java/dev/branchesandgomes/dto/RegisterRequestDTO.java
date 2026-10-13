package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(@NotBlank @Size(min = 2, max = 120)
                                 String name,
                                 @NotBlank @Size(min = 11, max = 11)
                                 String cpf,
                                 @NotBlank @Size(max = 255)
                                 String email,
                                 @NotBlank @Size(min = 8, max = 64)
                                 String password,
                                 @NotBlank @Size(min = 10, max = 11)
                                 String phone
                                 ) {
}
