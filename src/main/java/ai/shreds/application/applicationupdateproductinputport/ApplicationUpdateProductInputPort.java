package ai.shreds.application;

import ai.shreds.shared.SharedUpdateProductRequestParams;
import ai.shreds.shared.SharedProductResponseDTO;
import java.util.UUID;

/**
 * Interface for updating a product in the catalogue.
 * This interface will be implemented by a service class that handles the business logic for updating a product.
 */
public interface ApplicationUpdateProductInputPort {
    /**
     * Updates an existing product in the catalogue.
     *
     * @param id the UUID of the product to be updated
     * @param requestParams the updated product details
     * @return the updated product information
     */
    SharedProductResponseDTO updateProduct(UUID id, SharedUpdateProductRequestParams requestParams);
}