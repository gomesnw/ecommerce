package dev.branchesandgomes.dto;

import dev.branchesandgomes.model.entity.CartItemEntity;

import java.math.BigDecimal;

public record CartItemResponseDTO(
        String productName,
        String productDescription,
        BigDecimal productUnitPrice,
        String productImageUrl,
        Integer productStock,
        Integer quantity,
        BigDecimal totalValue,
        Boolean available
) {
  public static CartItemResponseDTO from(CartItemEntity entity) {
    return new CartItemResponseDTO(
            entity.getProduct().getName(),
            entity.getProduct().getDescription(),
            entity.getProduct().getUnitPrice(),
            entity.getProduct().getImageUrl(),
            entity.getProduct().getStock(),
            entity.getQuantity(),
            entity.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(entity.getQuantity())),
            entity.getProduct().getStock() >= entity.getQuantity()
    );
  }
}