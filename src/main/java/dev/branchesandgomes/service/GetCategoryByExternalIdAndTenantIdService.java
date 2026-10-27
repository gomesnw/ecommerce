package dev.branchesandgomes.service;

import dev.branchesandgomes.exception.NotFoundException;
import dev.branchesandgomes.model.entity.CategoryEntity;
import dev.branchesandgomes.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetCategoryByExternalIdAndTenantIdService {
    private final CategoryRepository categoryRepository;

    public CategoryEntity execute(UUID externalId, Long tenantId){
        return categoryRepository.findByExternalIdAndTenantId(externalId, tenantId)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }
}
