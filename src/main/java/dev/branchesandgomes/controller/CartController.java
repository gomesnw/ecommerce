package dev.branchesandgomes.controller;

import dev.branchesandgomes.config.context.AuthContext;
import dev.branchesandgomes.dto.AddItemToCartRequestDTO;
import dev.branchesandgomes.dto.CartItemResponseDTO;
import dev.branchesandgomes.dto.PageResponseDTO;
import dev.branchesandgomes.dto.PageableRequestDTO;
import dev.branchesandgomes.model.entity.CustomerEntity;
import dev.branchesandgomes.service.AddItemToCartService;
import dev.branchesandgomes.service.GetCustomerAllCartItemsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@PreAuthorize("hasRole('CUSTOMER')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/tenantId/{tenantExternalId}/cart")
public class CartController {
    private final GetCustomerAllCartItemsService getCustomerAllCartItemsService;
    private final AddItemToCartService addItemToCartService;

    @GetMapping
    public ResponseEntity<PageResponseDTO<CartItemResponseDTO>> getAllCartItems(@PathVariable UUID tenantExternalId, @Valid PageableRequestDTO pageableRequest) {
        CustomerEntity customer = AuthContext.getCustomer();

        PageResponseDTO<CartItemResponseDTO> response = getCustomerAllCartItemsService.execute(tenantExternalId, customer.getId(), pageableRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartItemResponseDTO> addItemToCart(@PathVariable UUID tenantExternalId,@RequestBody @Valid AddItemToCartRequestDTO request) {
        CustomerEntity customer = AuthContext.getCustomer();

        CartItemResponseDTO response = addItemToCartService.execute(request, customer, tenantExternalId);

        return ResponseEntity.ok(response);
    }
}
