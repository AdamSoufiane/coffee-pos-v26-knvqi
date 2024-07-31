package ai.shreds.adapter;

import ai.shreds.application.ApplicationValidateCategoryInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

/**
 * Validator class to check if a given category ID exists in the database.
 */
@Component
public class AdapterCategoryValidator {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryValidator.class);

    private final ApplicationValidateCategoryInputPort validateCategoryInputPort;

    @Autowired
    public AdapterCategoryValidator(ApplicationValidateCategoryInputPort validateCategoryInputPort) {
        this.validateCategoryInputPort = validateCategoryInputPort;
    }

    /**
     * Validates if the given category ID exists in the database.
     *
     * @param categoryId the UUID of the category to be validated
     * @return boolean indicating whether the category exists
     */
    public boolean validate(UUID categoryId) {
        try {
            return validateCategoryInputPort.validateCategoryExists(categoryId);
        } catch (Exception e) {
            // Log the exception and return false indicating the category does not exist
            logger.error("Exception occurred while validating category: ", e);
            return false;
        }
    }
}