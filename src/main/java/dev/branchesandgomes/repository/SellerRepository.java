package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    Optional<SellerEntity> findByEmail(String username);

    Optional<SellerEntity> findByExternalId(UUID externalId);
}
