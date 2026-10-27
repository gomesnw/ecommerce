package dev.branchesandgomes.controller;

import dev.branchesandgomes.config.context.AuthContext;
import dev.branchesandgomes.dto.CreateProductRequestDTO;
import dev.branchesandgomes.dto.ProductResponseDTO;
import dev.branchesandgomes.model.entity.SellerEntity;
import dev.branchesandgomes.service.CreateProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('SELLER')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProductService createProductService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid CreateProductRequestDTO request) {
        SellerEntity seller = AuthContext.getSeller();
        Long tenantId = AuthContext.getTenantId();

        ProductResponseDTO response = createProductService.execute(request, tenantId, seller);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
