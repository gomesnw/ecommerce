package dev.branchesandgomes.service;

import dev.branchesandgomes.exception.NotFoundException;
import dev.branchesandgomes.model.entity.TenantEntity;
import dev.branchesandgomes.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetTenantByExternalIdService {
    private final TenantRepository tenantRepository;

    public TenantEntity execute(UUID tenantId) {
        return tenantRepository.findByExternalId(tenantId)
                .orElseThrow(() -> new NotFoundException("Tenant não encontrado"));
    }
}
