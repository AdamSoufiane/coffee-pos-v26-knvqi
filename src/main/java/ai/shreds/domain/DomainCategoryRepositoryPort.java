package ai.shreds.domain;

import java.util.Optional;

/**
 * DomainCategoryRepositoryPort provides an abstraction for accessing category data from the PostgreSQL database.
 */
public interface DomainCategoryRepositoryPort {
    /**
     * Finds a category by its name, case-insensitive.
     *
     * @param name the name of the category to find
     * @return the Optional<DomainCategoryEntity> representing the category
     */
    Optional<DomainCategoryEntity> findByNameIgnoreCase(String name);
}