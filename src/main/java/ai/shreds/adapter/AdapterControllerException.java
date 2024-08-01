package ai.shreds.adapter;

import ai.shreds.shared.AdapterResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class AdapterControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterControllerException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterResponseDTO> handleException(Exception e, WebRequest request) {
        logger.error("Exception occurred: ", e);
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("An error occurred: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<AdapterResponseDTO> handleValidationException(ConstraintViolationException e) {
        logger.error("Validation error: ", e);
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("Validation error: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AdapterResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("Method argument not valid: ", e);
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("Method argument not valid: " + e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AdapterResponseDTO> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("Illegal argument: ", e);
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("Illegal argument: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<AdapterResponseDTO> handleNullPointerException(NullPointerException e) {
        logger.error("Null pointer error: ", e);
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("Null pointer error: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}