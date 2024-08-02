package ai.shreds.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when product data validation fails in the domain layer.
 */
public class DomainProductValidationException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(DomainProductValidationException.class);

    /**
     * Constructs a new DomainProductValidationException with the specified detail message.
     * @param message the detail message.
     */
    public DomainProductValidationException(String message) {
        super(message);
        logException(message, null);
    }

    /**
     * Constructs a new DomainProductValidationException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public DomainProductValidationException(String message, Throwable cause) {
        super(message, cause);
        logException(message, cause);
    }

    /**
     * Logs the exception details for better traceability and debugging.
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    private void logException(String message, Throwable cause) {
        logger.error("DomainProductValidationException: {}", message, cause);
    }
}