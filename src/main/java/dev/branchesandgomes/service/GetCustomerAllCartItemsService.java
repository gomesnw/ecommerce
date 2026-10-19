package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.CartItemResponseDto;
import dev.branchesandgomes.dto.PageResponse;
import dev.branchesandgomes.dto.PageableRequest;
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

    public PageResponse<CartItemResponseDto> execute(Long customerId, PageableRequest pageableRequest) {
        PageRequest pageRequest = pageableRequest.toPageRequest("createdAt");

        Page<CartItemEntity> items = cartItemRepository.findAllByCustomerAndProductActiveTrue(customerId, pageRequest);

        Page<CartItemResponseDto> itemResponse = items.map(CartItemResponseDto::from);

        return PageResponse.from(itemResponse);
    }
}
