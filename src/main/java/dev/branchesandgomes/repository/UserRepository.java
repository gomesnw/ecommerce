package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <UserEntity, Long>{
    Optional<UserEntity> findByExternalId(UUID externalId);
}
