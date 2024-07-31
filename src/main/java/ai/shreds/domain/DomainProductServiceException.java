package ai.shreds.domain;

import ai.shreds.shared.AdapterErrorResponse;

public final class DomainProductServiceException {

    /**
     * Handles exceptions by converting them to a standard AdapterErrorResponse.
     * @param e The exception to handle
     * @return AdapterErrorResponse
     */
    public AdapterErrorResponse handleException(Exception e) {
        AdapterErrorResponse response = new AdapterErrorResponse();
        response.setMessage("An error occurred in the product service.");
        response.setError(e.getMessage());
        return response;
    }
}