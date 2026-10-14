package dev.branchesandgomes.dto;

import dev.branchesandgomes.model.enums.RoleType;

public record LoginResponseDto(
        String accessToken, RoleType role
) {
}
