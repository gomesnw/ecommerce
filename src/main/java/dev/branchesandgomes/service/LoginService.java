package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.LoginRequestDto;
import dev.branchesandgomes.dto.LoginResponseDto;
import dev.branchesandgomes.model.UserDetailsImpl;
import dev.branchesandgomes.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDto execute(LoginRequestDto request) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = userDetails.getUser();

        String accessToken = jwtService.generateToken(user);

        return new LoginResponseDto(accessToken);
    }

}
