package dev.branchesandgomes.controller;

import dev.branchesandgomes.config.context.AuthContext;
import dev.branchesandgomes.dto.CategoryRequestDTO;
import dev.branchesandgomes.dto.CategoryResponseDTO;
import dev.branchesandgomes.service.CreateCategoryService;
import dev.branchesandgomes.service.ListCategoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('SELLER')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CreateCategoryService createCategoryService;
    private final ListCategoriesService getAllCategoriesService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO request) {
        Long tenantId = AuthContext.getTenantId();

        var response = createCategoryService.execute(request, tenantId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        Long tenantId = AuthContext.getTenantId();

        var response = getAllCategoriesService.execute(tenantId);

        return ResponseEntity.ok(response);
    }
}
