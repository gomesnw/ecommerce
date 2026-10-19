package dev.branchesandgomes.controller;

import dev.branchesandgomes.config.context.AuthContext;
import dev.branchesandgomes.dto.CartItemResponseDto;
import dev.branchesandgomes.dto.PageResponse;
import dev.branchesandgomes.dto.PageableRequest;
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
    public ResponseEntity<PageResponse<CartItemResponseDto>> getAllCartItems(@Valid PageableRequest pageableRequest) {
        CustomerEntity customer = AuthContext.getCustomer();

        PageResponse<CartItemResponseDto> response = getCustomerAllCartItemsService.execute(customer.getId(), pageableRequest);

        return ResponseEntity.ok(response);
    }
}
