package dev.branchesandgomes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity(name = "category_entity")
public class CategoryEntity extends TenantOwnedBaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    public CategoryEntity(String name, String description, String imageUrl, Long tenantId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.setTenantId(tenantId);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoryEntity that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
