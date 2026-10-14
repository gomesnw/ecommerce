package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository <CustomerEntity, Long>{
    Optional<CustomerEntity> findByExternalId(UUID externalId);

    Optional<CustomerEntity> findByPhone(String phone);
}
