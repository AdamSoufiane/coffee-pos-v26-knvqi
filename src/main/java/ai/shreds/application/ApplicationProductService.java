package ai.shreds.application;

import ai.shreds.adapter.AdapterCreateProductRequest;
import ai.shreds.adapter.AdapterDeleteProductResponse;
import ai.shreds.adapter.AdapterProductResponse;
import ai.shreds.adapter.AdapterSyncProductRequest;
import ai.shreds.adapter.AdapterSyncProductResponse;
import ai.shreds.adapter.AdapterUpdateProductRequest;
import ai.shreds.domain.DomainCategoryService;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for managing products in the catalogue.
 */
@Service
@RequiredArgsConstructor
public class ApplicationProductService implements ApplicationCreateProductInputPort, ApplicationUpdateProductInputPort, ApplicationDeleteProductInputPort, ApplicationSyncProductInputPort {

    private final DomainProductService domainProductService;
    private final DomainCategoryService domainCategoryService;

    /**
     * Creates a new product in the catalogue.
     * @param request the product creation request
     * @return the created product response
     */
    @Override
    public AdapterProductResponse createProduct(AdapterCreateProductRequest request) {
        try {
            if (!domainCategoryService.validateCategoryExists(request.getCategoryId())) {
                throw new IllegalArgumentException("Category does not exist");
            }
            DomainProductEntity productEntity = domainProductService.createProduct(request);
            return new AdapterProductResponse(productEntity.getId(), productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getAvailability(), productEntity.getCategoryId());
        } catch (Exception e) {
            throw new ApplicationProductServiceException(e.getMessage(), e);
        }
    }

    /**
     * Updates an existing product in the catalogue.
     * @param id the product ID
     * @param request the product update request
     * @return the updated product response
     */
    @Override
    public AdapterProductResponse updateProduct(UUID id, AdapterUpdateProductRequest request) {
        try {
            if (!domainCategoryService.validateCategoryExists(request.getCategoryId())) {
                throw new IllegalArgumentException("Category does not exist");
            }
            DomainProductEntity productEntity = domainProductService.updateProduct(id, request);
            return new AdapterProductResponse(productEntity.getId(), productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getAvailability(), productEntity.getCategoryId());
        } catch (Exception e) {
            throw new ApplicationProductServiceException(e.getMessage(), e);
        }
    }

    /**
     * Deletes a product from the catalogue.
     * @param id the product ID
     * @return the delete product response
     */
    @Override
    public AdapterDeleteProductResponse deleteProduct(UUID id) {
        try {
            String message = domainProductService.deleteProduct(id);
            return new AdapterDeleteProductResponse(message);
        } catch (Exception e) {
            throw new ApplicationProductServiceException(e.getMessage(), e);
        }
    }

    /**
     * Synchronizes the product with the catalogue.
     * @param request the sync product request
     * @return the sync product response
     */
    @Override
    public AdapterSyncProductResponse syncProduct(AdapterSyncProductRequest request) {
        try {
            // Assuming syncProduct returns void and we only need to handle synchronization success message
            domainProductService.syncProduct(request);
            return new AdapterSyncProductResponse("Synchronization successful");
        } catch (Exception e) {
            throw new ApplicationProductServiceException(e.getMessage(), e);
        }
    }
}

/**
 * Custom exception for ApplicationProductService.
 */
class ApplicationProductServiceException extends RuntimeException {
    public ApplicationProductServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}