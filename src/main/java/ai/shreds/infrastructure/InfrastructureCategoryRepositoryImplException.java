package ai.shreds.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InfrastructureCategoryRepositoryImplException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImplException.class);

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Error> handleDataAccessException(DataAccessException exception) {
        logger.error("Data access exception occurred: ", exception);
        Error error = new Error("A database error occurred while processing your request.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception) {
        logger.error("General exception occurred: ", exception);
        Error error = new Error("An error occurred while processing your request.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class Error {
        private String message;

        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}