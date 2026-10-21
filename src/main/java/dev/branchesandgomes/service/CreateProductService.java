package dev.branchesandgomes.service;

import dev.branchesandgomes.dto.CreateProductRequestDTO;
import dev.branchesandgomes.dto.ProductResponseDTO;
import dev.branchesandgomes.model.entity.CategoryEntity;
import dev.branchesandgomes.model.entity.ProductEntity;
import dev.branchesandgomes.model.entity.SellerEntity;
import dev.branchesandgomes.repository.ProductRepository;
import dev.branchesandgomes.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CreateProductService {
    private final SellerRepository sellerRepository;
    private final GetCategoryByExternalIdAndTenantIdService getCategoryByExternalIdAndTenantIdService;
    private final ProductRepository productRepository;

    public ProductResponseDTO execute(CreateProductRequestDTO request, Long tenantId, SellerEntity seller
    ){
      CategoryEntity category = getCategoryByExternalIdAndTenantIdService.execute(request.categoryId(), tenantId);
      ProductEntity product = new ProductEntity(
                request.description(),
                request.name(),
                category,
                request.unitPrice(),
                request.imageUrl(),
                request.stock(),
                seller,
                tenantId
      );
      productRepository.save(product);
      return ProductResponseDTO.from(product);
    }
}
