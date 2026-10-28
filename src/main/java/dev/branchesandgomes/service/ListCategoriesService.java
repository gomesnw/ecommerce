package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.CategoryResponseDTO;
import dev.branchesandgomes.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ListCategoriesService {
    private final CategoryRepository categoryRepository;
    private final GetTenantByExternalIdService tenantByExternalIdService;

    public List<CategoryResponseDTO> execute(Long tenantId) {
        List<CategoryResponseDTO> response = categoryRepository.findAllByTenantId(tenantId, Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(CategoryResponseDTO::from)
                .toList();
        return new ArrayList<>(response);
    }
}
