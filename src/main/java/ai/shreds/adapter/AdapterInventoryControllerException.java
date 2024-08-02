package ai.shreds.adapter;

import ai.shreds.domain.DomainInventoryItemAlreadyExistsException;
import ai.shreds.domain.DomainInventoryItemNotFoundException;
import ai.shreds.domain.DomainInventoryItemQuantityException;
import ai.shreds.adapter.AdapterInventoryDeleteResponse;
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
        return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item not found: " + e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DomainInventoryItemAlreadyExistsException.class)
    public ResponseEntity<AdapterInventoryDeleteResponse> handleItemAlreadyExistsException(DomainInventoryItemAlreadyExistsException e) {
        logger.error("Item already exists: {}", e.getMessage());
        return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item already exists: " + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainInventoryItemQuantityException.class)
    public ResponseEntity<AdapterInventoryDeleteResponse> handleItemQuantityException(DomainInventoryItemQuantityException e) {
        logger.error("Invalid item quantity: {}", e.getMessage());
        return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Invalid item quantity: " + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterInventoryDeleteResponse> handleGeneralException(Exception e) {
        logger.error("Internal server error: {}", e.getMessage());
        return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Internal server error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}