package ai.shreds.adapter;

import ai.shreds.application.ApplicationSyncServicePort;
import ai.shreds.application.ApplicationRealTimeUpdateServicePort;
import ai.shreds.shared.AdapterProductSyncRequestParams;
import ai.shreds.shared.AdapterProductSyncResponseDTO;
import ai.shreds.shared.AdapterRealTimeUpdateRequest;
import ai.shreds.shared.AdapterRealTimeUpdateResponse;
lombok.RequiredArgsConstructor;
lombok.NonNull;
lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
@Slf4j
public class AdapterProductSyncController {

    private final ApplicationSyncServicePort applicationSyncServicePort;
    private final ApplicationRealTimeUpdateServicePort applicationRealTimeUpdateServicePort;

    @PostMapping
    public AdapterProductSyncResponseDTO handleSyncRequest(@RequestBody @NonNull AdapterProductSyncRequestParams params) {
        try {
            validateParams(params);
            log.info("Received sync request: {}", params);
            AdapterProductSyncResponseDTO response = applicationSyncServicePort.syncProductData(params);
            log.info("Sync response: {}", response);
            return response;
        } catch (IllegalArgumentException e) {
            log.error("Validation error handling sync request", e);
            return new AdapterProductSyncResponseDTO("Synchronization failed: " + e.getMessage(), null);
        } catch (Exception e) {
            log.error("Error handling sync request", e);
            return new AdapterProductSyncResponseDTO("Synchronization failed: Internal server error", null);
        }
    }

    @PostMapping("/realtime-update")
    public AdapterRealTimeUpdateResponse handleRealTimeUpdate(@RequestBody @NonNull AdapterRealTimeUpdateRequest message) {
        try {
            validateMessage(message);
            log.info("Received real-time update message: {}", message);
            AdapterRealTimeUpdateResponse response = applicationRealTimeUpdateServicePort.handleRealTimeUpdate(message);
            log.info("Real-time update response: {}", response);
            return response;
        } catch (IllegalArgumentException e) {
            log.error("Validation error handling real-time update", e);
            return new AdapterRealTimeUpdateResponse("Real-time update failed: " + e.getMessage(), null);
        } catch (Exception e) {
            log.error("Error handling real-time update", e);
            return new AdapterRealTimeUpdateResponse("Real-time update failed: Internal server error", null);
        }
    }

    private void validateParams(AdapterProductSyncRequestParams params) {
        if (params.getId() == null || params.getName() == null || params.getPrice() == null || params.getAvailability() == null) {
            throw new IllegalArgumentException("Invalid product data: Missing required fields");
        }
        if (params.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid product data: Price must be a positive value");
        }
    }

    private void validateMessage(AdapterRealTimeUpdateRequest message) {
        if (message.getId() == null || message.getName() == null || message.getPrice() == null || message.getAvailability() == null) {
            throw new IllegalArgumentException("Invalid product data: Missing required fields");
        }
        if (message.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid product data: Price must be a positive value");
        }
    }

    @ExceptionHandler
    public AdapterProductSyncResponseDTO handleException(Exception e) {
        log.error("An exception occurred: ", e);
        return new AdapterProductSyncResponseDTO("Error occurred: " + e.getMessage(), null);
    }

    @ExceptionHandler
    public AdapterRealTimeUpdateResponse handleException(Exception e) {
        log.error("An exception occurred: ", e);
        return new AdapterRealTimeUpdateResponse("Error occurred: " + e.getMessage(), null);
    }
}