package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.entity.CartItemEntity;
import dev.branchesandgomes.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    @Query("SELECT c FROM cart_item_entity c join fetch c.product WHERE c.customerId = :customerId AND c.product.active = true AND c.tenantId = :tenantId")
    Page<CartItemEntity> findAllByCustomerAndProductActiveTrueAndTenantId(Long customerId, Long tenantId, Pageable pageable);

    Optional<CartItemEntity> findByProductIdAndTenantId(Long productId, Long tenantId);
}
