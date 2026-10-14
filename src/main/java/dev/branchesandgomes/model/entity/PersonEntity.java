package dev.branchesandgomes.model.entity;

import dev.branchesandgomes.utils.formatter.NameFormatter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "person_entity")
public class PersonEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    public PersonEntity(String name) {
        this.name = NameFormatter.capitalize(name);
    }
}
