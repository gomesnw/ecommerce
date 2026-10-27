package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "O campo 'name' é obrigatório")
        String name,
        String description
) {
}
