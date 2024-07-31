package ai.shreds.infrastructure;

import ai.shreds.shared.AdapterErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles exceptions that occur in the infrastructure layer.
 */
public class InfrastructureException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureException.class);

    /**
     * Handles an exception and returns an AdapterErrorResponse.
     *
     * @param e the exception to handle
     * @return an AdapterErrorResponse containing the error details
     */
    public AdapterErrorResponse handleException(Exception e) {
        logger.error("Error occurred: ", e);
        return AdapterErrorResponse.builder()
                .message("Error occurred")
                .error(e.getMessage())
                .build();
    }
}