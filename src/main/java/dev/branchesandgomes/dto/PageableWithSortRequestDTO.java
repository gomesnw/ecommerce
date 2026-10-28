package dev.branchesandgomes.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public record PageableWithSortRequestDTO<T> (
        @NotNull(message = "O parâmetro 'pageSize' é obrigatório") Integer pageSize,
        @NotNull(message = "O parâmetro 'pageNumber' é obrigatório") Integer pageNumber,
        @NotNull(message = "O parâmetro 'sortBy' é obrigatório") T sortBy,
        @NotNull(message = "O parâmetro 'sortDirection' é obrigatório") Sort.Direction sortDirection
) {
    public PageRequest toPageRequest() {
        return PageRequest.of(this.pageNumber(), this.pageSize(), this.sortDirection(), this.sortBy().toString());
    }
}
