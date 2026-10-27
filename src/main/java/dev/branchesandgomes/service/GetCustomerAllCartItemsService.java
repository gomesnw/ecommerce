package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.CartItemResponseDTO;
import dev.branchesandgomes.dto.PageResponseDTO;
import dev.branchesandgomes.dto.PageableRequestDTO;
import dev.branchesandgomes.model.entity.CartItemEntity;
import dev.branchesandgomes.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCustomerAllCartItemsService {
    private final CartItemRepository cartItemRepository;

    public PageResponseDTO<CartItemResponseDTO> execute(Long customerId, PageableRequestDTO pageableRequest) {
        PageRequest pageRequest = pageableRequest.toPageRequest("createdAt");

        Page<CartItemEntity> items = cartItemRepository.findAllByCustomerAndProductActiveTrue(customerId, pageRequest);

        Page<CartItemResponseDTO> itemResponse = items.map(CartItemResponseDTO::from);

        return PageResponseDTO.from(itemResponse);
    }
}
