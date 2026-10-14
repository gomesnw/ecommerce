package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.RegisterRequestDTO;
import dev.branchesandgomes.model.CustomerEntity;
import dev.branchesandgomes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterService {
    private final CustomerRepository customerRepository;

    public void execute(RegisterRequestDTO registerRequest){
        CustomerEntity customerEntity = new CustomerEntity(
                registerRequest.name(),
                registerRequest.phone()
        );

        customerRepository.save(customerEntity);
    }
}
