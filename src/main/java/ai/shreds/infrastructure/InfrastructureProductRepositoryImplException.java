package ai.shreds.infrastructure;

import ai.shreds.shared.AdapterErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InfrastructureProductRepositoryImplException {

    public AdapterErrorResponse handleException(Exception e) {
        log.error("Exception occurred in Product Repository: ", e);
        AdapterErrorResponse errorResponse = new AdapterErrorResponse();
        errorResponse.setMessage("Error occurred in Product Repository");
        errorResponse.setError(e.getMessage());
        return errorResponse;
    }
}