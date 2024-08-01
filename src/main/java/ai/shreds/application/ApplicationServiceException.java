package ai.shreds.application;

import ai.shreds.shared.AdapterResponseDTO;
import ai.shreds.domain.DomainProductValidationException;
import ai.shreds.domain.DomainProductTransformationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApplicationServiceException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterResponseDTO> handleException(Exception e) {
        AdapterResponseDTO response = new AdapterResponseDTO();
        if (e instanceof ValidationException) {
            log.error("Validation failed", e);
            response.setMessage("Validation failed: " + e.getMessage());
            response.setErrorCode("VALIDATION_ERROR");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (e instanceof TransformationException) {
            log.error("Transformation failed", e);
            response.setMessage("Transformation failed: " + e.getMessage());
            response.setErrorCode("TRANSFORMATION_ERROR");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (e instanceof DomainProductValidationException) {
            log.error("Domain product validation failed", e);
            response.setMessage("Domain product validation failed: " + e.getMessage());
            response.setErrorCode("DOMAIN_VALIDATION_ERROR");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (e instanceof DomainProductTransformationException) {
            log.error("Domain product transformation failed", e);
            response.setMessage("Domain product transformation failed: " + e.getMessage());
            response.setErrorCode("DOMAIN_TRANSFORMATION_ERROR");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            log.error("Internal server error", e);
            response.setMessage("Internal server error: " + e.getMessage());
            response.setErrorCode("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Custom exception classes
    @Slf4j
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    @Slf4j
    public static class TransformationException extends RuntimeException {
        public TransformationException(String message) {
            super(message);
        }
    }
}