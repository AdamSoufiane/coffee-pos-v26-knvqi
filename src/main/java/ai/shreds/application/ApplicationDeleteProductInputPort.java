package ai.shreds.application;

import java.util.UUID;

/**
 * Interface for deleting a product in the catalogue.
 */
public interface ApplicationDeleteProductInputPort {
    /**
     * Deletes a product by its ID.
     *
     * @param id the UUID of the product to be deleted
     * @throws IllegalArgumentException if the UUID is null
     */
    void deleteProduct(UUID id) throws IllegalArgumentException;

    /**
     * Validates the UUID parameter.
     *
     * @param id the UUID to be validated
     * @throws IllegalArgumentException if the UUID is null
     */
    default void validateUUID(UUID id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
    }
}