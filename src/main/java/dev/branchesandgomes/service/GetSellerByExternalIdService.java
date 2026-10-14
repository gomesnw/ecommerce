package dev.branchesandgomes.service;

import dev.branchesandgomes.exception.NotFountException;
import dev.branchesandgomes.model.SellerEntity;
import dev.branchesandgomes.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetSellerByExternalIdService {
    private final SellerRepository sellerRepository;

    public SellerEntity execute(UUID externalId) {
        return sellerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new NotFountException("Seller with id '%s' not found".formatted(externalId)));
    }
}
