package ai.shreds.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface for Category Repository in the domain layer.
 * Provides methods to interact with category data.
 */
public interface DomainCategoryRepositoryPort {
    /**
     * Finds a category entity by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return an Optional containing the found category entity, or empty if not found
     */
    Optional<DomainCategoryEntity> findById(UUID id);
}