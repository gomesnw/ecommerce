package dev.branchesandgomes.controller;

import dev.branchesandgomes.config.context.AuthContext;
import dev.branchesandgomes.dto.CartItemResponseDTO;
import dev.branchesandgomes.dto.PageResponseDTO;
import dev.branchesandgomes.dto.PageableRequestDTO;
import dev.branchesandgomes.model.entity.CustomerEntity;
import dev.branchesandgomes.service.GetCustomerAllCartItemsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('CUSTOMER')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final GetCustomerAllCartItemsService getCustomerAllCartItemsService;

    @GetMapping
    public ResponseEntity<PageResponseDTO<CartItemResponseDTO>> getAllCartItems(@Valid PageableRequestDTO pageableRequest) {
        CustomerEntity customer = AuthContext.getCustomer();

        PageResponseDTO<CartItemResponseDTO> response = getCustomerAllCartItemsService.execute(customer.getId(), pageableRequest);

        return ResponseEntity.ok(response);
    }
}
