package ai.shreds.application.port.in;

import ai.shreds.shared.dto.SharedDeleteProductResponseDTO;
import java.util.UUID;

/**
 * Interface for deleting a product from the catalogue.
 */
@FunctionalInterface
public interface ApplicationDeleteProductInputPort {
    /**
     * Deletes a product from the catalogue.
     *
     * @param id the UUID of the product to be deleted
     * @return a SharedDeleteProductResponseDTO containing the result of the deletion operation
     */
    SharedDeleteProductResponseDTO deleteProduct(UUID id);
}