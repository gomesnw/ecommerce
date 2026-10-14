package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.CustomerLoginRequestDTO;
import dev.branchesandgomes.dto.LoginResponseDTO;
import dev.branchesandgomes.dto.SellerLoginRequestDTO;
import dev.branchesandgomes.exception.InternalServerErrorException;
import dev.branchesandgomes.model.SellerEntity;
import dev.branchesandgomes.model.UserDetailsImpl;
import dev.branchesandgomes.model.CustomerEntity;
import dev.branchesandgomes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;

    public LoginResponseDTO customerLogin(CustomerLoginRequestDTO request) {
        CustomerEntity customer = customerRepository.findByPhone(request.phone())
                .filter(CustomerEntity::getActive)
                .orElseThrow(() -> new UsernameNotFoundException("Customer não encontrado: " + request.phone()));

        String accessToken = jwtService.generateToken(customer);

        return new LoginResponseDTO(accessToken);
    }

    public LoginResponseDTO sellerLogin(SellerLoginRequestDTO request) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (userDetails == null || userDetails.getSeller() == null) {
            throw new InternalServerErrorException("Seller não encontrado para o token");
        }

        SellerEntity seller = userDetails.getSeller();

        String accessToken = jwtService.generateToken(seller);

        return new LoginResponseDTO(accessToken);
    }

}
