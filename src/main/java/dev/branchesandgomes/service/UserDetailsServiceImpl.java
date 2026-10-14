package dev.branchesandgomes.service;

import dev.branchesandgomes.model.entity.CustomerEntity;
import dev.branchesandgomes.model.entity.SellerEntity;
import dev.branchesandgomes.model.entity.UserDetailsImpl;
import dev.branchesandgomes.repository.CustomerRepository;
import dev.branchesandgomes.repository.SellerRepository;
import dev.branchesandgomes.utils.checks.NumberChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (NumberChecker.isNumber(username)) {
            CustomerEntity foundCustomer = customerRepository.findByPhone(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Customer não encontrado: " + username));

            return new UserDetailsImpl(foundCustomer);
        }

        SellerEntity foundSeller = sellerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Seller não encontrado: " + username));

        return new UserDetailsImpl(foundSeller);
    }
}