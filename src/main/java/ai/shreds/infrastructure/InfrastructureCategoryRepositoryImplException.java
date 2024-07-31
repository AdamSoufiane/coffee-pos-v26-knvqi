package ai.shreds.infrastructure;

import ai.shreds.adapter.AdapterErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import lombok.Data;

@RestControllerAdvice
public class InfrastructureCategoryRepositoryImplException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImplException.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AdapterErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.error("Entity not found", e);
        AdapterErrorResponse errorResponse = AdapterErrorResponse.builder().message("Entity not found").error(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<AdapterErrorResponse> handleSQLException(SQLException e) {
        logger.error("Database error", e);
        AdapterErrorResponse errorResponse = AdapterErrorResponse.builder().message("Database error").error(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<AdapterErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error("Data integrity violation", e);
        AdapterErrorResponse errorResponse = AdapterErrorResponse.builder().message("Data integrity violation").error(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterErrorResponse> handleException(Exception e) {
        logger.error("An unexpected error occurred", e);
        AdapterErrorResponse errorResponse = AdapterErrorResponse.builder().message("An unexpected error occurred").error(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    static class AdapterErrorResponse {
        private String message;
        private String error;
    }
}