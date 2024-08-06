package ai.shreds.domain;

import java.util.UUID;

/**
 * Interface for accessing product data in the domain layer.
 */
public interface DomainProductRepositoryPort {
    /**
     * Finds a product by its unique identifier (UUID).
     *
     * @param id the unique identifier of the product
     * @return the product entity corresponding to the given UUID
     */
    DomainProductDomainEntity findById(UUID id);

    /**
     * Saves or updates the given product entity in the database.
     *
     * @param entity the product entity to be saved or updated
     */
    void save(DomainProductDomainEntity entity);

    /**
     * Deletes the product corresponding to the given UUID from the database.
     *
     * @param id the unique identifier of the product to be deleted
     */
    void deleteById(UUID id);
}