package dev.branchesandgomes.controller;

import dev.branchesandgomes.dto.RegisterRequestDTO;
import dev.branchesandgomes.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController("/auth")
public class AuthController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDTO request) {
        registerService.execute(request);

        return ResponseEntity.noContent().build();
    }

}
