package ai.shreds.infrastructure;

import ai.shreds.domain.exception.DomainInventoryItemNotFoundException;
import ai.shreds.domain.DomainInventoryItemAlreadyExistsException;
import ai.shreds.domain.DomainInventoryItemQuantityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Handles exceptions occurring within the InfrastructureInventoryRepositoryImpl class.
 */
@Component
public class InfrastructureInventoryRepositoryImplException {
    private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImplException.class);

    /**
     * Handles DomainInventoryItemNotFoundException.
     *
     * @param e the exception to handle
     */
    public void handleException(DomainInventoryItemNotFoundException e) {
        logger.error("Inventory item not found: {}", e.getMessage());
        throw new CustomRuntimeException("Inventory item not found", e);
    }

    /**
     * Handles DomainInventoryItemAlreadyExistsException.
     *
     * @param e the exception to handle
     */
    public void handleException(DomainInventoryItemAlreadyExistsException e) {
        logger.error("Inventory item already exists: {}", e.getMessage());
        throw new CustomRuntimeException("Inventory item already exists", e);
    }

    /**
     * Handles DomainInventoryItemQuantityException.
     *
     * @param e the exception to handle
     */
    public void handleException(DomainInventoryItemQuantityException e) {
        logger.error("Invalid inventory item quantity: {}", e.getMessage());
        throw new CustomRuntimeException("Invalid inventory item quantity", e);
    }

    /**
     * Handles generic exceptions.
     *
     * @param e the exception to handle
     */
    public void handleException(Exception e) {
        logger.error("An unexpected error occurred: {}", e.getMessage());
        throw new CustomRuntimeException("An unexpected error occurred", e);
    }
}

/**
 * CustomRuntimeException is a custom exception that extends RuntimeException.
 */
class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}