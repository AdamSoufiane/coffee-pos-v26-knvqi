package ai.shreds.domain;

import java.util.Optional;
import java.util.UUID;
import ai.shreds.domain.DomainProductEntity;

/**
 * DomainProductRepositoryPort interface serves as the primary contract for the product repository in the domain layer.
 * It abstracts the data access layer and ensures that the application core is decoupled from the actual database implementation.
 */
public interface DomainProductRepositoryPort {

    /**
     * Saves a given product entity to the database. Returns the saved entity.
     * @param product the product entity to save
     * @return the saved product entity
     */
    DomainProductEntity save(DomainProductEntity product);

    /**
     * Finds a product entity by its ID. Returns an Optional.
     * @param id the unique identifier of the product
     * @return an Optional containing the found product entity, or empty if not found
     */
    Optional<DomainProductEntity> findById(UUID id);

    /**
     * Deletes a product entity by its ID.
     * @param id the unique identifier of the product to delete
     */
    void deleteById(UUID id);
}