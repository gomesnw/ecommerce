package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.CartItemResponseDTO;
import dev.branchesandgomes.dto.PageResponseDTO;
import dev.branchesandgomes.dto.PageableRequestDTO;
import dev.branchesandgomes.model.entity.CartItemEntity;
import dev.branchesandgomes.model.entity.TenantEntity;
import dev.branchesandgomes.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetCustomerAllCartItemsService {
    private final CartItemRepository cartItemRepository;
    private final GetTenantByExternalIdService getTenantByExternalIdService;

    public PageResponseDTO<CartItemResponseDTO> execute(UUID tenantExternalId, Long customerId, PageableRequestDTO pageableRequest) {
        TenantEntity tenant = getTenantByExternalIdService.execute(tenantExternalId);
        PageRequest pageRequest = pageableRequest.toPageRequest("createdAt");

        Page<CartItemEntity> items = cartItemRepository.findAllByCustomerAndProductActiveTrueAndTenantId(customerId, tenant.getId(), pageRequest);

        Page<CartItemResponseDTO> itemResponse = items.map(CartItemResponseDTO::from);

        return PageResponseDTO.from(itemResponse);
    }
}
