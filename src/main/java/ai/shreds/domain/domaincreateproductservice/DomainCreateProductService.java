package ai.shreds.domain.service;

import ai.shreds.domain.model.DomainProductEntity;
import ai.shreds.domain.port.DomainProductRepositoryPort;
import ai.shreds.domain.port.DomainProductServicePort;
import ai.shreds.shared.dto.SharedCreateProductRequestParams;
import ai.shreds.shared.dto.SharedProductResponseDTO;
import ai.shreds.shared.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainCreateProductService implements DomainProductServicePort {

    private final DomainProductRepositoryPort repository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public SharedProductResponseDTO createProduct(SharedCreateProductRequestParams requestParams) {
        // Validate product data
        validateProductData(requestParams);

        // Check for unique product name
        Optional<DomainProductEntity> existingProduct = repository.findByName(requestParams.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product name must be unique");
        }

        // Map request params to domain entity
        DomainProductEntity productEntity = productMapper.toDomainEntity(requestParams);

        try {
            // Save product entity to repository
            repository.save(productEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the product", e);
        }

        // Map saved entity to response DTO
        SharedProductResponseDTO responseDTO = productMapper.toResponseDTO(productEntity);

        return responseDTO;
    }

    private void validateProductData(SharedCreateProductRequestParams requestParams) {
        if (requestParams.getName() == null || requestParams.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty");
        }
        if (requestParams.getPrice() == null || requestParams.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }
        if (requestParams.getAvailability() == null) {
            throw new IllegalArgumentException("Product availability must be specified");
        }
    }
}