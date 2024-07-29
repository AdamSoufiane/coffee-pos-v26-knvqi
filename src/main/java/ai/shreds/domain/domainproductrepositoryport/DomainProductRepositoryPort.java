package ai.shreds.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 * Interface for Product Repository operations in the domain layer.
 */
@Repository
public interface DomainProductRepositoryPort {

    /**
     * Saves a given product entity to the database.
     *
     * @param product the product entity to be saved
     */
    void save(DomainProductEntity product);

    /**
     * Finds a product entity by its ID.
     *
     * @param id the UUID of the product
     * @return an Optional containing the found product entity, or empty if not found
     */
    Optional<DomainProductEntity> findById(UUID id);

    /**
     * Deletes a product entity by its ID.
     *
     * @param id the UUID of the product to be deleted
     */
    void deleteById(UUID id);
}