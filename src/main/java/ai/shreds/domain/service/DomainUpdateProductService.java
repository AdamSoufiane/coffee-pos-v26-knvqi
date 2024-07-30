package ai.shreds.domain.service;

import ai.shreds.domain.model.DomainProductEntity;
import ai.shreds.domain.port.DomainProductRepositoryPort;
import ai.shreds.domain.port.DomainProductServicePort;
import ai.shreds.shared.dto.SharedUpdateProductRequestParams;
import ai.shreds.shared.dto.SharedProductResponseDTO;
import ai.shreds.shared.dto.SharedDeleteProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;

@Service
public class DomainUpdateProductService implements DomainProductServicePort {

    private final DomainProductRepositoryPort repository;
    private static final Logger logger = LoggerFactory.getLogger(DomainUpdateProductService.class);

    @Autowired
    public DomainUpdateProductService(DomainProductRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public SharedProductResponseDTO updateProduct(UUID id, SharedUpdateProductRequestParams requestParams) {
        logger.info("Starting update for product with ID: {}", id);
        validateProductData(requestParams);

        Optional<DomainProductEntity> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            logger.error("Product not found with ID: {}", id);
            throw new IllegalArgumentException("Product not found");
        }

        DomainProductEntity product = optionalProduct.get();
        product.setName(requestParams.getName());
        product.setDescription(requestParams.getDescription());
        product.setPrice(requestParams.getPrice());
        product.setAvailability(requestParams.getAvailability());

        repository.save(product);
        logger.info("Product updated successfully with ID: {}", id);

        return mapToSharedProductResponseDTO(product);
    }

    @Override
    @Transactional
    public SharedDeleteProductResponseDTO deleteProduct(UUID id) {
        logger.info("Starting delete for product with ID: {}", id);

        Optional<DomainProductEntity> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            logger.error("Product not found with ID: {}", id);
            throw new IllegalArgumentException("Product not found");
        }

        repository.deleteById(id);
        logger.info("Product deleted successfully with ID: {}", id);

        return new SharedDeleteProductResponseDTO("Operation successful");
    }

    private void validateProductData(SharedUpdateProductRequestParams requestParams) {
        if (requestParams.getName() == null || requestParams.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty");
        }
        if (requestParams.getPrice() == null || requestParams.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
        if (requestParams.getAvailability() == null) {
            throw new IllegalArgumentException("Product availability must be specified");
        }
    }

    private SharedProductResponseDTO mapToSharedProductResponseDTO(DomainProductEntity product) {
        return new SharedProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailability()
        );
    }
}