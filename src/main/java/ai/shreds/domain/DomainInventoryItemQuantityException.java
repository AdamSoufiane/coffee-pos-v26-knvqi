package ai.shreds.domain;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when the quantity of an inventory item is below the threshold.
 */
@Getter
public class DomainInventoryItemQuantityException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(DomainInventoryItemQuantityException.class);
    private final int quantity;
    private final int threshold;

    /**
     * Constructs a new exception with the specified quantity and threshold.
     *
     * @param quantity the current quantity of the inventory item
     * @param threshold the threshold below which the exception is thrown
     */
    public DomainInventoryItemQuantityException(int quantity, int threshold) {
        super(String.format("Inventory item quantity %d is below the threshold %d.", quantity, threshold));
        this.quantity = quantity;
        this.threshold = threshold;
    }

    /**
     * Handles the exception by logging the error and triggering an alert.
     */
    public void handleException() {
        // Log the error using SLF4J
        logger.error(getMessage());
        // Perform any necessary cleanup or alerting
        triggerAlert();
    }

    /**
     * Triggers an alert when the quantity is below the threshold.
     */
    private void triggerAlert() {
        // Implementation for triggering an alert when the quantity is below the threshold
        // Example: sendAlert(getMessage());
        logger.warn("Triggering alert: {}", getMessage());
        // Add actual alerting mechanism here
    }
}