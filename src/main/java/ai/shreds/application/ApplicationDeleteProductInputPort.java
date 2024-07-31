package ai.shreds.application;

import ai.shreds.adapter.AdapterDeleteProductResponse;
import java.util.UUID;

/**
 * Interface for deleting a product in the application layer.
 */
public interface ApplicationDeleteProductInputPort {
    /**
     * Deletes a product by its ID.
     * 
     * @param id the UUID of the product to be deleted
     * @return AdapterDeleteProductResponse containing the result of the deletion operation
     */
    AdapterDeleteProductResponse deleteProduct(UUID id);
}