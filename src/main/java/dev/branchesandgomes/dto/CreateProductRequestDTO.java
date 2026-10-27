package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;


public record CreateProductRequestDTO(@NotBlank(message = "O campo 'name' é obrigatório")
                                      String name,
                                      String description,
                                      @NotNull(message = "O campo 'unitPrice' é obrigatório")
                                      BigDecimal unitPrice,
                                      @NotNull(message = "O campo 'categoryId' é obrigatório")
                                      UUID categoryId,
                                      String imageUrl,
                                      Integer stock){
}