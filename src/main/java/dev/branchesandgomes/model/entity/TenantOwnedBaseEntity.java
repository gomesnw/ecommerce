package dev.branchesandgomes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TenantOwnedBaseEntity extends BaseEntity {
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;
}
