package ai.shreds.application;

import ai.shreds.shared.AdapterResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ai.shreds.domain.DomainProductValidationException;
import ai.shreds.domain.DomainProductTransformationException;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import javax.validation.ValidationException;

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
        } else if (e instanceof MethodArgumentNotValidException) {
            log.error("Validation failed - MethodArgumentNotValidException", e);
            response.setMessage("Validation failed: "+e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
            response.setErrorCode("VALIDATION_ERROR");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (e instanceof ConstraintViolationException) {
            log.error("Validation failed - ConstraintViolationException", e);
            response.setMessage("Validation failed: "+Stream.of(((ConstraintViolationException) e).getConstraintViolations()).map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));
            response.setErrorCode("VALIDATION_ERROR");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            log.error("Internal server error", e);
            response.setMessage("Internal server error: " + e.getMessage());
            response.setErrorCode("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Custom exception classes
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    public static class TransformationException extends RuntimeException {
        public TransformationException(String message) {
            super(message);
        }
    }
}