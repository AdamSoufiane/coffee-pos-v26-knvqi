package ai.shreds.application;

import ai.shreds.shared.AdapterRealTimeUpdateRequest;
import ai.shreds.shared.AdapterRealTimeUpdateResponse;
import ai.shreds.adapter.AdapterProductDTO;
import ai.shreds.domain.DomainProductDomainEntity;
import ai.shreds.domain.DomainProductSyncServicePort;
import ai.shreds.domain.DomainProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationRealTimeUpdateService implements ApplicationRealTimeUpdateServicePort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRealTimeUpdateService.class);

    private final DomainProductSyncServicePort domainProductSyncServicePort;
    private final DomainProductRepositoryPort domainProductRepositoryPort;

    @Override
    public AdapterRealTimeUpdateResponse handleRealTimeUpdate(AdapterRealTimeUpdateRequest request) {
        if (request == null || request.getId() == null || request.getName() == null || request.getDescription() == null || request.getPrice() == null || request.getAvailability() == null) {
            return new AdapterRealTimeUpdateResponse("Real-time update failed", new AdapterProductDTO(request.getId(), request.getName(), request.getDescription(), request.getPrice(), request.isAvailability()));
        }

        logger.info("Received real-time update request for product ID: {}", request.getId());

        try {
            DomainProductDomainEntity domainEntity = new DomainProductDomainEntity(
                    request.getId(),
                    request.getName(),
                    request.getDescription(),
                    request.getPrice(),
                    request.isAvailability(),
                    null, // created_at will be handled internally
                    null  // updated_at will be handled internally
            );

            if (!domainProductSyncServicePort.validateProductData(domainEntity)) {
                return new AdapterRealTimeUpdateResponse("Real-time update failed", new AdapterProductDTO(request.getId(), request.getName(), request.getDescription(), request.getPrice(), request.isAvailability()));
            }

            domainProductRepositoryPort.save(domainEntity);

            domainProductSyncServicePort.publishRealTimeUpdate(domainEntity);

            AdapterProductDTO responseDTO = new AdapterProductDTO(
                    domainEntity.getId(),
                    domainEntity.getName(),
                    domainEntity.getDescription(),
                    domainEntity.getPrice(),
                    domainEntity.isAvailability()
            );

            logger.info("Real-time update successful for product ID: {}", request.getId());

            return new AdapterRealTimeUpdateResponse("Real-time update successful", responseDTO);
        } catch (Exception e) {
            logger.error("Error handling real-time update for product ID: {}", request.getId(), e);
            return new AdapterRealTimeUpdateResponse("Real-time update failed", new AdapterProductDTO(request.getId(), request.getName(), request.getDescription(), request.getPrice(), request.isAvailability()));
        }
    }
}