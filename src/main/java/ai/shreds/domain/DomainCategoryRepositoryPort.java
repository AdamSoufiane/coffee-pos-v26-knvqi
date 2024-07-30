package ai.shreds.domain;

/**
 * DomainCategoryRepositoryPort provides an abstraction for accessing category data from the PostgreSQL database.
 */
public interface DomainCategoryRepositoryPort {
    /**
     * Finds a category by its name, case-insensitive.
     *
     * @param name the name of the category to find
     * @return the DomainCategoryEntity representing the category
     */
    DomainCategoryEntity findByNameIgnoreCase(String name);
}