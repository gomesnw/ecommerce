package dev.branchesandgomes.service;


import dev.branchesandgomes.dto.AddItemToCartRequestDTO;
import dev.branchesandgomes.dto.CartItemResponseDTO;
import dev.branchesandgomes.exception.BadRequestException;
import dev.branchesandgomes.model.entity.CartItemEntity;
import dev.branchesandgomes.model.entity.CustomerEntity;
import dev.branchesandgomes.model.entity.ProductEntity;
import dev.branchesandgomes.model.entity.TenantEntity;
import dev.branchesandgomes.repository.CartItemRepository;
import dev.branchesandgomes.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AddItemToCartService {

    private final GetProductByExternalIdAndTenantIdService getProductByExternalIdAndTenantIdService;
    private final TenantRepository tenantRepository;
    private final GetTenantByExternalIdService getTenantByExternalIdService;
    private final CartItemRepository cartItemRepository;

    public CartItemResponseDTO execute(AddItemToCartRequestDTO request, CustomerEntity customer, UUID tenantExternalId) {
        TenantEntity tenant = getTenantByExternalIdService.execute(tenantExternalId);

        Long tenantId = tenant.getId();
        ProductEntity product = getProductByExternalIdAndTenantIdService.execute(request.productId(), tenantId);

        Optional<CartItemEntity> existingCartItemOpt = cartItemRepository.findByProductIdAndTenantId(product.getId(), tenantId);
        CartItemEntity cartItem = existingCartItemOpt
                .map(cartItemEntity -> addQuantityToExistingCartItem(cartItemEntity, request.quantity()))
                .orElseGet(() -> new CartItemEntity(product, customer, request.quantity(), tenant.getId()));

        if (product.getStock() < cartItem.getQuantity()) {
            throw new BadRequestException("Quantidade solicitada excede o estoque disponível");
        }

        cartItemRepository.save(cartItem);
        return CartItemResponseDTO.from(cartItem);
    }

    private CartItemEntity addQuantityToExistingCartItem(CartItemEntity existingCartItem, int quantityToAdd) {
        existingCartItem.addQuantity(quantityToAdd);

        return existingCartItem;
    }

}
