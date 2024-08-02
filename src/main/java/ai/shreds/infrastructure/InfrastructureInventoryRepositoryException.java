package ai.shreds.infrastructure;

import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * Handles exceptions that occur within the InfrastructureInventoryRepositoryImpl class.
 */
@Component
public class InfrastructureInventoryRepositoryException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryException.class);

    /**
     * Handles exceptions thrown within the repository layer.
     *
     * @param exception the exception to handle
     */
    public void handleException(Exception exception) {
        if (exception instanceof DataAccessException) {
            logger.error("Data access exception occurred: {}", exception.getMessage());
            // Handle data access exception, possibly rethrow a custom exception
            throw new CustomInfrastructureException("Data access error", exception);
        } else if (exception instanceof MongoException) {
            logger.error("MongoDB exception occurred: {}", exception.getMessage());
            // Handle MongoDB specific exception, possibly rethrow a custom exception
            throw new CustomInfrastructureException("MongoDB error", exception);
        } else {
            logger.error("An unexpected exception occurred: {}", exception.getMessage());
            // Handle generic exceptions
            throw new CustomInfrastructureException("Unexpected error", exception);
        }
    }
}

class CustomInfrastructureException extends RuntimeException {
    public CustomInfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}