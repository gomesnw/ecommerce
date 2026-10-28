package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.*;
import dev.branchesandgomes.model.entity.CategoryEntity;
import dev.branchesandgomes.model.entity.ProductEntity;
import dev.branchesandgomes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ListProductsService {
    private final ProductRepository productRepository;

    public PageResponseDTO<ListProductsResponseDTO> execute(PageableRequestDTO pageable, Long tenantId) {
        PageRequest pageRequest = pageable.toPageRequest("name");

        Page<ProductEntity> productsPage = productRepository.findAllByTenantIdAndActiveIsTrue(tenantId, pageRequest);
        var productList = productsPage.getContent();
        Map<CategoryEntity, List<ProductEntity>> productsByCategory = productList.stream().collect(Collectors.groupingBy(ProductEntity::getCategory));

        List<ListProductsResponseDTO> responseList = productsByCategory.entrySet().stream()
                .map(entry -> {
                    CategoryEntity category = entry.getKey();
                    List<ProductEntity> productsInCategory = entry.getValue();
                    var productResponseList = productsInCategory.stream()
                            .map(ListProductsResponseDTO.ProductResponseDTO::from)
                            .toList();
                    return new ListProductsResponseDTO(CategoryResponseDTO.from(category), productResponseList);
                })
                .toList();

        return new PageResponseDTO<>(
                productsPage.getSize(),
                productsPage.getNumber(),
                productsPage.getTotalElements(),
                responseList,
                productsPage.isFirst(),
                productsPage.isLast()
        );
    }
}
