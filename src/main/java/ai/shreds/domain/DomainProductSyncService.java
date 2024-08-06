package ai.shreds.domain;

import ai.shreds.infrastructure.InfrastructureMessagingClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DomainProductSyncService implements DomainProductSyncServicePort {
    private static final Logger logger = LoggerFactory.getLogger(DomainProductSyncService.class);
    private final DomainProductRepositoryPort domainProductRepositoryPort;
    private final InfrastructureMessagingClient messagingClient;

    @Override
    public boolean validateProductData(DomainProductDomainEntity entity) {
        if (entity.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainProductValidationException("Price must be a positive value.");
        }
        if (!entity.getAvailability()) {
            throw new DomainProductValidationException("Product must be available.");
        }
        return true;
    }

    @Override
    public DomainProductDomainEntity transformProductData(DomainProductDomainEntity entity) {
        // Transformation logic here (if needed)
        return entity;
    }

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

    // Exceptions can be moved to a separate file or kept here for demonstration

    public class DomainProductValidationException extends DomainProductBaseException {
        public DomainProductValidationException(String message) {
            super(message);
        }
    }

    public class DomainProductDomainException extends DomainProductBaseException {
        public DomainProductDomainException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public abstract class DomainProductBaseException extends RuntimeException {
        public DomainProductBaseException(String message) {
            super(message);
        }

        public DomainProductBaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}