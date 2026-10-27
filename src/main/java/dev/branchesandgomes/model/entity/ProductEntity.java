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
    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @NotNull
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock",  nullable = false)
    private Integer stock;

    @Column(name = "active",  nullable = false)
    private Boolean active = true;

    @Column(name = "created_by", nullable = false, insertable = false, updatable = false)
    private Long createdBy;
    @JoinColumn(name = "created_by", updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SellerEntity createdBySeller;

    public ProductEntity(String description, String name, CategoryEntity category, BigDecimal unitPrice, String imageUrl, Integer stock, SellerEntity createdBySeller, Long tenantId) {
        super.setTenantId(tenantId);
        this.description = description;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
        this.stock = stock;
        this.createdBySeller = createdBySeller;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public void replaceStock(int quantity) {
        this.stock = quantity;
    }
}
