package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByExternalIdAndTenantId(UUID externalId, Long tenantId);

    @EntityGraph(attributePaths = {"category"})
    Page<ProductEntity> findAllByTenantIdAndActiveIsTrue(Long tenantId, Pageable pageable);
}