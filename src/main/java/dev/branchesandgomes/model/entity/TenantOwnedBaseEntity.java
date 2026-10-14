package dev.branchesandgomes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Setter;

@Setter
@MappedSuperclass
public abstract class TenantOwnedBaseEntity extends BaseEntity {
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;
}
