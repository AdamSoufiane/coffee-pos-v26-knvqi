package ai.shreds.domain;

/**
 * Custom exception class for handling not found scenarios in the domain layer.
 * This exception is thrown when a specific inventory item is not found.
 */
public class InventoryNotFoundException extends RuntimeException {

    /**
     * Constructs a new InventoryNotFoundException with the specified detail message.
     * @param message the detail message
     */
    public InventoryNotFoundException(String message) {
        super(message);
    }
}