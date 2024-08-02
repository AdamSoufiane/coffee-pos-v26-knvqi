package ai.shreds.adapter;

import ai.shreds.domain.DomainInventoryItemAlreadyExistsException;
import ai.shreds.domain.DomainInventoryItemNotFoundException;
import ai.shreds.domain.DomainInventoryItemQuantityException;
import ai.shreds.shared.AdapterInventoryDeleteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class AdapterInventoryControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryControllerException.class);

    @ExceptionHandler(DomainInventoryItemNotFoundException.class)
    public ResponseEntity<AdapterInventoryDeleteResponse> handleItemNotFoundException(DomainInventoryItemNotFoundException e) {
        logger.error("Item not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new AdapterInventoryDeleteResponse("Item not found: " + e.getMessage()));
    }

    @ExceptionHandler(DomainInventoryItemAlreadyExistsException.class)
    public ResponseEntity<AdapterInventoryDeleteResponse> handleItemAlreadyExistsException(DomainInventoryItemAlreadyExistsException e) {
        logger.error("Item already exists: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new AdapterInventoryDeleteResponse("Item already exists: " + e.getMessage()));
    }

    @ExceptionHandler(DomainInventoryItemQuantityException.class)
    public ResponseEntity<AdapterInventoryDeleteResponse> handleItemQuantityException(DomainInventoryItemQuantityException e) {
        logger.error("Invalid item quantity: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new AdapterInventoryDeleteResponse("Invalid item quantity: " + e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterInventoryDeleteResponse> handleGeneralException(Exception e) {
        logger.error("Internal server error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new AdapterInventoryDeleteResponse("Internal server error: " + e.getMessage()));
    }
}