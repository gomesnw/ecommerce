package dev.branchesandgomes.service;

import dev.branchesandgomes.exception.NotFountException;
import dev.branchesandgomes.model.entity.CustomerEntity;
import dev.branchesandgomes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetCustomerByExternalIdService {
    private final CustomerRepository customerRepository;

    public CustomerEntity execute(UUID externalId) {
        return customerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new NotFountException("User with id '%s' not found".formatted(externalId)));
    }
}
