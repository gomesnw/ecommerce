package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record AddItemToCartRequestDTO(
        @NotNull(message = "O campo 'productId' é obrigatório")
        UUID productId,
        @NotNull(message = "O campo 'quantity' é obrigatório")
        @Positive(message = "O campo 'quantity' deve ser um número positivo")
        Integer quantity
) {
}
