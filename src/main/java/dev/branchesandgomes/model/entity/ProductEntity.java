package dev.branchesandgomes.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity(name = "product_entity")
public class ProductEntity extends TenantOwnedBaseEntity {
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @JoinColumn(name = "category_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoryEntity category;

    @NotNull
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock",  nullable = false)
    private Integer stock = 0;
}
