package ai.shreds.application;

import ai.shreds.shared.ApplicationSharedProductDTO;
import java.util.UUID;

/**
 * Interface for updating a product in the catalogue.
 */
public interface ApplicationUpdateProductInputPort {
    /**
     * Updates an existing product in the catalogue.
     *
     * @param id the UUID of the product to be updated
     * @param params the updated product details
     * @return the updated ApplicationSharedProductDTO
     * @throws Exception if any error occurs during the update process
     */
    ApplicationSharedProductDTO updateProduct(UUID id, ApplicationSharedProductDTO params) throws Exception;

    // Add relevant exception handling methods
}
// Implementation Note: Use Lombok annotations for getters and setters in the implementing classes.