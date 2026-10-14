package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotBlank;

public record SellerLoginRequestDTO(
        @NotBlank(message = "O campo 'email' é obrigatório")
        String email,
        @NotBlank(message = "O campo 'password' é obrigatório")
        String password
) {
}
