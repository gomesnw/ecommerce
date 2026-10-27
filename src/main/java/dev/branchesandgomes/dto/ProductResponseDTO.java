package dev.branchesandgomes.dto;

import dev.branchesandgomes.model.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(UUID externalId,
                                 LocalDateTime createdAt,
                                 String name,
                                 String description,
                                 BigDecimal unitPrice,
                                 Long categoryId,
                                 String imageUrl,
                               Integer stock) {
    public static ProductResponseDTO from(ProductEntity entity) {
        return new ProductResponseDTO(
                entity.getExternalId(),
                entity.getCreatedAt(),
                entity.getName(),
                entity.getDescription(),
                entity.getUnitPrice(),
                entity.getCategoryId(),
                entity.getImageUrl(),
                entity.getStock()
        );
    }
}