package dev.branchesandgomes.controller;

import dev.branchesandgomes.config.context.AuthContext;
import dev.branchesandgomes.dto.*;
import dev.branchesandgomes.model.entity.SellerEntity;
import dev.branchesandgomes.service.CreateProductService;
import dev.branchesandgomes.service.ListProductsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProductService createProductService;
    private final ListProductsService listProductsService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid CreateProductRequestDTO request) {
        SellerEntity seller = AuthContext.getSeller();
        Long tenantId = AuthContext.getTenantId();

        ProductResponseDTO response = createProductService.execute(request, tenantId, seller);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<ListProductsResponseDTO>> listProducts(@Valid PageableRequestDTO pageable) {
        Long tenantId = AuthContext.getTenantId();
        PageResponseDTO<ListProductsResponseDTO> response = listProductsService.execute(pageable, tenantId);
        return ResponseEntity.ok(response);
    }
}
