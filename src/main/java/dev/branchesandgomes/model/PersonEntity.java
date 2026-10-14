package dev.branchesandgomes.model;

import dev.branchesandgomes.utils.formatter.NameFormatter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PersonEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    public PersonEntity(String name) {
        this.name = NameFormatter.capitalize(name);
    }
}
