package dev.branchesandgomes.model;

import jakarta.persistence.Column;

public abstract class TenantOwnedBaseEntity extends BaseEntity {
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;
}
