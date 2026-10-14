package dev.branchesandgomes.controller;

import dev.branchesandgomes.dto.LoginRequestDto;
import dev.branchesandgomes.dto.LoginResponseDto;
import dev.branchesandgomes.dto.RegisterRequestDTO;
import dev.branchesandgomes.service.LoginService;
import dev.branchesandgomes.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController("/auth")
public class AuthController {
    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDTO request) {
        registerService.execute(request);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto request) {
        LoginResponseDto response = loginService.execute(request);

        return ResponseEntity.ok(response);
    }

}
