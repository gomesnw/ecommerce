package dev.branchesandgomes.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponseDTO<T> (
        int pageSize,
        int pageNumber,
        long totalElements,
        List<T> content,
        boolean isFirstPage,
        boolean isLastPage
) {
    public static <T> PageResponseDTO<T> from(Page<T> page) {
        return new PageResponseDTO<>(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getContent(),
                page.isFirst(),
                page.isLast()
        );
    }
}
