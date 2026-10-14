package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerLoginRequestDTO(
        @NotBlank(message = "O campo 'phone' é obrigatório")
        String phone
) {
}
