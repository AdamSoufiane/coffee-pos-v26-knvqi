package ai.shreds.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * DomainCategoryRepositoryPort is a contract for category-related data access operations.
 * It should be implemented by any class that interacts with the data source to manage categories.
 */
public interface DomainCategoryRepositoryPort {
    /**
     * Finds a category entity by its ID.
     *
     * @param categoryId The unique identifier of the category.
     * @return An Optional containing the found category entity, or an empty Optional if not found.
     */
    Optional<DomainCategoryEntity> findById(UUID categoryId);
}

// Note: Use Lombok annotations for getter and setter methods in the implementation.
// Note: Include proper exception handling for cases where the category might not be found.