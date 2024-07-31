package ai.shreds.application;

import ai.shreds.domain.DomainCategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for validating the existence of a category in the database.
 */
@Service
@RequiredArgsConstructor
public class ApplicationCategoryValidationService implements ApplicationValidateCategoryInputPort {

    private final DomainCategoryRepositoryPort categoryRepository;

    /**
     * Validates if the category with the given ID exists in the database.
     *
     * @param categoryId the UUID of the category to validate
     * @return true if the category exists, false otherwise
     */
    @Override
    public boolean validateCategoryExists(UUID categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}