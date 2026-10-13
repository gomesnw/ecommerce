package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long>{
}
