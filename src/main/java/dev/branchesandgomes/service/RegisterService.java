package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.RegisterRequestDTO;
import dev.branchesandgomes.exception.ForbiddenException;
import dev.branchesandgomes.model.UserEntity;
import dev.branchesandgomes.model.enums.RoleType;
import dev.branchesandgomes.repository.UserRepository;

public class RegisterService {
    UserRepository userRepository;

    public void register(RegisterRequestDTO registerRequest, RoleType role){
        boolean exists = userRepository.existsByEmail(registerRequest.email());

        if(exists){
        throw new ForbiddenException("Email já está em uso.");
        }

        UserEntity userEntity = new UserEntity(registerRequest.name(),
                registerRequest.cpf(),
                registerRequest.email(),
                registerRequest.password(),
                registerRequest.phone(),
                role);

        userRepository.save(userEntity);
    }
}
