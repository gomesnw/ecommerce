package dev.branchesandgomes.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name = "cart_item_entity")
public class CartItemEntity extends TenantOwnedBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    public CartItemEntity(ProductEntity product, CustomerEntity customer, Integer quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }
}
