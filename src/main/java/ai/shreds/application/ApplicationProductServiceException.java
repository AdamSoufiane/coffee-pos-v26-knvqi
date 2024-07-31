package ai.shreds.application;

import ai.shreds.shared.AdapterErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationProductServiceException {

    public AdapterErrorResponse handleException(Exception e) {
        log.error("Exception occurred during product service operation: ", e);
        return new AdapterErrorResponse("Service operation failed", e.getMessage());
    }
}