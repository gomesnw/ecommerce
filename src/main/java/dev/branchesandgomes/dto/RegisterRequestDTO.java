package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(@NotBlank @Size(min = 2, max = 120)
                                 String name,
                                 @NotBlank @Size(min = 10, max = 11)
                                 String phone
                                 ) {
}
