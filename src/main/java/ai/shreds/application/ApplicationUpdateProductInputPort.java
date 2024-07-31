package ai.shreds.application;

import ai.shreds.adapter.AdapterProductResponse;
import ai.shreds.adapter.AdapterUpdateProductRequest;
import java.util.UUID;

/**
 * Interface for updating an existing product in the catalogue.
 */
public interface ApplicationUpdateProductInputPort {
    /**
     * Updates an existing product in the catalogue.
     *
     * @param id the UUID of the product to be updated
     * @param request the updated product details
     * @return the updated product information
     */
    AdapterProductResponse updateProduct(UUID id, AdapterUpdateProductRequest request);
}