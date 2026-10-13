package dev.branchesandgomes.model;

import dev.branchesandgomes.exception.InvalidCpfException;
import dev.branchesandgomes.utils.formatter.NameFormatter;
import dev.branchesandgomes.utils.formatter.NumberFormatter;
import dev.branchesandgomes.utils.validator.CpfValidator;
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

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    public PersonEntity(String name, String cpf) {
        if (!CpfValidator.isValid(cpf)) {
            throw new InvalidCpfException();
        }

        this.name = NameFormatter.capitalize(name);
        this.cpf = NumberFormatter.onlyNumbers(cpf);
    }
}
