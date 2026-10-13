package dev.branchesandgomes.model;

import dev.branchesandgomes.exception.InvalidEmailException;
import dev.branchesandgomes.model.enums.RoleType;
import dev.branchesandgomes.utils.formatter.NumberFormatter;
import dev.branchesandgomes.utils.validator.EmailValidator;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity(name = "user_entity")
public class UserEntity extends BaseEntity {
    @JoinColumn(name = "person_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private PersonEntity person;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType role;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public UserEntity(String name,
                      String cpf,
                      String email,
                      String password,
                      String phone,
                      RoleType role) {

        if (!EmailValidator.isValid(email)){
            throw new InvalidEmailException();
        }

        this.person = new PersonEntity(name, cpf);
        this.email = email;
        this.password = password;
        this.phone = NumberFormatter.onlyNumbers(phone);
        this.role = role;
    }

    public UserEntity() {}
}
