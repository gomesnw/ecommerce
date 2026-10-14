package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.RegisterRequestDTO;
import dev.branchesandgomes.exception.ForbiddenException;
import dev.branchesandgomes.model.UserEntity;
import dev.branchesandgomes.model.enums.RoleType;
import dev.branchesandgomes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(RegisterRequestDTO registerRequest){
        boolean exists = userRepository.existsByEmail(registerRequest.email());

        if(exists){
            throw new ForbiddenException("Email já está em uso.");
        }

        UserEntity userEntity = new UserEntity(
                registerRequest.name(),
                registerRequest.cpf(),
                registerRequest.email(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.phone(),
                RoleType.CUSTOMER
        );

        userRepository.save(userEntity);
    }
}
