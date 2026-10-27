package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.CategoryRequestDTO;
import dev.branchesandgomes.dto.CategoryResponseDTO;
import dev.branchesandgomes.model.entity.CategoryEntity;
import dev.branchesandgomes.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponseDTO execute(CategoryRequestDTO request, Long tenantId) {
        CategoryEntity category = new CategoryEntity(request.name(), request.description(), null, tenantId);

        CategoryEntity savedCategory = categoryRepository.save(category);

        return CategoryResponseDTO.from(savedCategory);
    }
}
