package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByExternalIdAndTenantId(UUID externalId, Long tenantId);
}