package ai.shreds.domain;

import ai.shreds.shared.AdapterErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainCategoryServiceException {

    public AdapterErrorResponse handleException(Exception e) {
        log.error("An error occurred in the category service: ", e);
        AdapterErrorResponse response = new AdapterErrorResponse();
        response.setMessage("An error occurred in the category service.");
        response.setError(e.getMessage());
        return response;
    }
}