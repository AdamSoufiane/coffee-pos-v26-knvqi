package ai.shreds.domain;

/**
 * Custom exception class for handling not found scenarios in the domain layer.
 * This exception is thrown when a specific resource or item is not found.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified detail message.
     * @param message the detail message
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new NotFoundException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new NotFoundException with the specified cause.
     * @param cause the cause
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}