package dev.branchesandgomes.service;

import dev.branchesandgomes.exception.NotFoundException;
import dev.branchesandgomes.model.entity.ProductEntity;
import dev.branchesandgomes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetProductByExternalIdAndTenantIdService {
    private final ProductRepository productRepository;

    public ProductEntity execute(UUID productId, Long tenantId) {
        return productRepository.findByExternalIdAndTenantId(productId, tenantId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }
}
