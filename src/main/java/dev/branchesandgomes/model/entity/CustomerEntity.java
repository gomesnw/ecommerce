package dev.branchesandgomes.model.entity;

import dev.branchesandgomes.utils.formatter.NumberFormatter;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity(name = "customer_entity")
public class CustomerEntity extends BaseEntity {
    @JoinColumn(name = "person_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private PersonEntity person;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public CustomerEntity(String name,
                          String phone) {
        this.person = new PersonEntity(name);
        this.phone = NumberFormatter.onlyNumbers(phone);
    }

    public CustomerEntity() {}
}
