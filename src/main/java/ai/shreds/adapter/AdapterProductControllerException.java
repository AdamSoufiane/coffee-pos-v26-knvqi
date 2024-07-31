package ai.shreds.adapter;

import ai.shreds.adapter.AdapterErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class AdapterProductControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductControllerException.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AdapterErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("Illegal argument exception: ", e);
        AdapterErrorResponse response = new AdapterErrorResponse();
        response.setMessage("Invalid input provided");
        response.setError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AdapterErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.error("Entity not found exception: ", e);
        AdapterErrorResponse response = new AdapterErrorResponse();
        response.setMessage("Entity not found");
        response.setError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterErrorResponse> handleException(Exception e) {
        logger.error("Unexpected exception: ", e);
        AdapterErrorResponse response = new AdapterErrorResponse();
        response.setMessage("An unexpected error occurred");
        response.setError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}