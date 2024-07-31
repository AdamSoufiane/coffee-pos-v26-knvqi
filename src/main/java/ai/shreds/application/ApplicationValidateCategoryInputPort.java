package ai.shreds.application;

import java.util.UUID;

public interface ApplicationValidateCategoryInputPort {
    boolean validateCategoryExists(UUID categoryId);
}