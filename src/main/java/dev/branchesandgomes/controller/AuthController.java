package dev.branchesandgomes.controller;

import dev.branchesandgomes.dto.CustomerLoginRequestDTO;
import dev.branchesandgomes.dto.LoginResponseDTO;
import dev.branchesandgomes.dto.RegisterRequestDTO;
import dev.branchesandgomes.dto.SellerLoginRequestDTO;
import dev.branchesandgomes.service.LoginService;
import dev.branchesandgomes.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("/customer/register")
    public ResponseEntity<Void> customerRegister(@RequestBody @Valid RegisterRequestDTO request) {
        registerService.customerRegister(request);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/customer/login")
    public ResponseEntity<LoginResponseDTO> customerLogin(@RequestBody @Valid CustomerLoginRequestDTO request) {
        LoginResponseDTO response = loginService.customerLogin(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/seller/login")
    public ResponseEntity<LoginResponseDTO> sellerLogin(@RequestBody @Valid SellerLoginRequestDTO request) {
        LoginResponseDTO response = loginService.sellerLogin(request);

        return ResponseEntity.ok(response);
    }

}
