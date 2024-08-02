package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class AdapterInventoryControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryControllerException.class);

    /**
     * Handles general exceptions.
     * @param exception the exception that occurred
     * @param request the web request context
     * @return a ResponseEntity with an error message and HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
        logger.error("Exception occurred: ", exception);
        return new ResponseEntity<>(new ErrorResponse("Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles bad request exceptions.
     * @param exception the exception that occurred
     * @param request the web request context
     * @return a ResponseEntity with an error message and HTTP status
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException exception, WebRequest request) {
        logger.error("Bad request: ", exception);
        return new ResponseEntity<>(new ErrorResponse("Invalid input data."), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles not found exceptions.
     * @param exception the exception that occurred
     * @param request the web request context
     * @return a ResponseEntity with an error message and HTTP status
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
        logger.error("Not found: ", exception);
        return new ResponseEntity<>(new ErrorResponse("Inventory item not found."), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles illegal argument exceptions.
     * @param exception the exception that occurred
     * @param request the web request context
     * @return a ResponseEntity with an error message and HTTP status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        logger.error("Illegal argument: ", exception);
        return new ResponseEntity<>(new ErrorResponse("Invalid argument provided."), HttpStatus.BAD_REQUEST);
    }

    private static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}