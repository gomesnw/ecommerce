package dev.branchesandgomes.repository;

import dev.branchesandgomes.model.entity.CartItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    @Query("SELECT c FROM cart_item_entity c join fetch c.product WHERE c.customerId = :customerId AND c.product.active = true")
    Page<CartItemEntity> findAllByCustomerAndProductActiveTrue(Long customerId, Pageable pageable);

}
