package dev.branchesandgomes.dto;

import dev.branchesandgomes.model.entity.CategoryEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponseDTO(UUID externalId, LocalDateTime createdAt, String name,
                                  String description) {
    public static CategoryResponseDTO from(CategoryEntity entity) {
        return new CategoryResponseDTO(
                entity.getExternalId(),
                entity.getCreatedAt(),
                entity.getName(),
                entity.getDescription()
        );
    }
}