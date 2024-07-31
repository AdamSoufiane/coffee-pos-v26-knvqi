package ai.shreds.application;

import ai.shreds.shared.ApplicationSharedProductDTO;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductMapper;
import ai.shreds.domain.DomainProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationProductService implements ApplicationCreateProductInputPort, ApplicationUpdateProductInputPort, ApplicationDeleteProductInputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProductService.class);

    private final DomainProductRepositoryPort productRepository;
    private final DomainCategoryRepositoryPort categoryRepository;
    private final DomainProductMapper productMapper;
    private final ApplicationValidateCategoryInputPort categoryValidator;

    @Override
    @Transactional
    public ApplicationSharedProductDTO createProduct(ApplicationSharedProductDTO params) {
        if (!validateCategoryExists(params.getCategory_id())) {
            throw new IllegalArgumentException("Category ID does not exist.");
        }
        if (productRepository.findByName(params.getName()).isPresent()) {
            throw new IllegalArgumentException("Product name must be unique.");
        }
        if (params.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value.");
        }
        DomainProductEntity productEntity = productMapper.toDomainEntity(params);
        DomainProductEntity savedEntity = productRepository.save(productEntity);
        logger.info("Product created: {}", savedEntity);
        return productMapper.toSharedDTO(savedEntity);
    }

    @Override
    @Transactional
    public ApplicationSharedProductDTO updateProduct(UUID id, ApplicationSharedProductDTO params) {
        if (!validateCategoryExists(params.getCategory_id())) {
            throw new IllegalArgumentException("Category ID does not exist.");
        }
        DomainProductEntity existingProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found."));
        if (!existingProduct.getName().equals(params.getName()) && productRepository.findByName(params.getName()).isPresent()) {
            throw new IllegalArgumentException("Product name must be unique.");
        }
        if (params.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value.");
        }
        existingProduct.setName(params.getName());
        existingProduct.setDescription(params.getDescription());
        existingProduct.setPrice(params.getPrice());
        existingProduct.setAvailability(params.getAvailability());
        existingProduct.setCategoryId(params.getCategory_id());
        DomainProductEntity updatedEntity = productRepository.save(existingProduct);
        logger.info("Product updated: {}", updatedEntity);
        return productMapper.toSharedDTO(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID id) {
        DomainProductEntity existingProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found."));
        productRepository.deleteById(id);
        logger.info("Product deleted: {}", id);
    }

    private boolean validateCategoryExists(UUID categoryId) {
        return categoryValidator.validateCategoryExists(categoryId);
    }
}