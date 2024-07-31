package ai.shreds.domain;

import ai.shreds.adapter.AdapterErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainProductServiceException {

    public AdapterErrorResponse handleException(Exception e) {
        AdapterErrorResponse response = new AdapterErrorResponse();
        response.setMessage("An error occurred in the product service.");
        response.setError(e.getMessage());
        return response;
    }
}