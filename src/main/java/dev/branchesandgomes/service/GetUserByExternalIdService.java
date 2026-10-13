package dev.branchesandgomes.service;

import dev.branchesandgomes.exception.NotFountException;
import dev.branchesandgomes.model.UserEntity;
import dev.branchesandgomes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetUserByExternalIdService {
    private final UserRepository userRepository;

    public UserEntity execute(UUID externalId) {
        return userRepository.findByExternalId(externalId)
                .orElseThrow(() -> new NotFountException("User with id '%s' not found".formatted(externalId)));
    }
}
