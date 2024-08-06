package ai.shreds.domain;

import java.util.UUID;

/**
 * Interface defining the contract for synchronizing product data within the domain layer.
 * This interface ensures that product data is validated, transformed, synchronized, and updated in real-time.
 */
public interface DomainProductSyncServicePort {
    /**
     * Validates the product data before synchronization or real-time update.
     * @param entity the product entity to validate
     * @return true if the product data is valid, false otherwise
     */
    boolean validateProductData(DomainProductDomainEntity entity);

    /**
     * Transforms the product data into the format required by the Menu Service.
     * @param entity the product entity to transform
     * @return the transformed product entity
     */
    DomainProductDomainEntity transformProductData(DomainProductDomainEntity entity);

    /**
     * Sends a synchronization request to the Menu Service.
     * @param entity the product entity to synchronize
     */
    void sendSyncRequest(DomainProductDomainEntity entity);

    /**
     * Publishes the real-time update message to RabbitMQ/Kafka.
     * @param entity the product entity to update in real-time
     */
    void publishRealTimeUpdate(DomainProductDomainEntity entity);
}

// Implementation Note: Use Lombok annotations for getters and setters in DomainProductDomainEntity.
// Implementation Note: Include error handling in the interface methods.