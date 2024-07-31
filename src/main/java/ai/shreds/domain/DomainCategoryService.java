package ai.shreds.domain;

import ai.shreds.domain.DomainCategoryRepositoryPort;
import ai.shreds.domain.DomainCategoryEntity;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

/**
 * Service class for handling business logic related to Category entities.
 */
@RequiredArgsConstructor
public class DomainCategoryService {
    private static final Logger logger = LoggerFactory.getLogger(DomainCategoryService.class);
    private final DomainCategoryRepositoryPort categoryRepository;

    /**
     * Finds a category by its ID.
     *
     * @param categoryId the UUID of the category
     * @return an Optional containing the found DomainCategoryEntity, or empty if not found
     */
    public Optional<DomainCategoryEntity> findCategoryById(UUID categoryId) {
        try {
            return categoryRepository.findById(categoryId);
        } catch (Exception e) {
            logger.error("Error finding category by ID: {}", categoryId, e);
            return Optional.empty();
        }
    }
}