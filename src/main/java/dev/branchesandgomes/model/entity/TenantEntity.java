package dev.branchesandgomes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "tenant_entity")
public class TenantEntity extends BaseEntity {
}
