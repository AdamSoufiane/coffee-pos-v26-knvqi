package ai.shreds.domain;

import ai.shreds.infrastructure.InfrastructureMessagingClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class DomainProductSyncService implements DomainProductSyncServicePort {
    private static final Logger logger = LoggerFactory.getLogger(DomainProductSyncService.class);
    private final DomainProductRepositoryPort domainProductRepositoryPort;
    private final InfrastructureMessagingClient messagingClient;

    /**
     * Validates the product data before synchronization or real-time update.
     * @param entity The product entity to validate.
     * @return true if validation passes.
     */
    @Override
    public boolean validateProductData(DomainProductDomainEntity entity) {
        if (entity.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainProductValidationException("Price must be a positive value.");
        }
        if (!entity.isAvailability()) {
            throw new DomainProductValidationException("Product must be available.");
        }
        return true;
    }

    /**
     * Transforms the product data into the format required by the Menu Service.
     * @param entity The product entity to transform.
     * @return The transformed product entity.
     */
    @Override
    public DomainProductDomainEntity transformProductData(DomainProductDomainEntity entity) {
        // Transformation logic here (if needed)
        return entity;
    }

    /**
     * Sends a synchronization request to the Menu Service.
     * @param entity The product entity to synchronize.
     */
    @Override
    public void sendSyncRequest(DomainProductDomainEntity entity) {
        try {
            messagingClient.sendSyncRequest(entity);
            logger.info("Synchronization request sent for product: {}", entity.getId());
        } catch (Exception e) {
            logger.error("Failed to send sync request for product: {}", entity.getId(), e);
            throw new DomainProductDomainException("Failed to send sync request.", e);
        }
    }

    /**
     * Publishes the real-time update message to RabbitMQ/Kafka.
     * @param entity The product entity to update in real-time.
     */
    @Override
    public void publishRealTimeUpdate(DomainProductDomainEntity entity) {
        try {
            messagingClient.publishRealTimeUpdate(entity);
            logger.info("Real-time update published for product: {}", entity.getId());
        } catch (Exception e) {
            logger.error("Failed to publish real-time update for product: {}", entity.getId(), e);
            throw new DomainProductDomainException("Failed to publish real-time update.", e);
        }
    }

    // Exception classes 
    public static class DomainProductValidationException extends DomainProductBaseException { 
        public DomainProductValidationException(String message) { 
            super(message); 
        } 
    } 

    public static class DomainProductDomainException extends DomainProductBaseException { 
        public DomainProductDomainException(String message, Throwable cause) { 
            super(message, cause); 
        } 
    } 

    public static abstract class DomainProductBaseException extends RuntimeException { 
        public DomainProductBaseException(String message) { 
            super(message); 
        } 

        public DomainProductBaseException(String message, Throwable cause) { 
            super(message, cause); 
        } 
    } 
}