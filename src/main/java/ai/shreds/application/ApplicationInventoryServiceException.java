package ai.shreds.application;

import ai.shreds.adapter.AdapterInventoryDeleteResponse;
import ai.shreds.domain.DomainInventoryItemAlreadyExistsException;
import ai.shreds.domain.DomainInventoryItemNotFoundException;
import ai.shreds.domain.DomainInventoryItemQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationInventoryServiceException {

    public ResponseEntity<AdapterInventoryDeleteResponse> handleException(Exception e) {
        if (e instanceof DomainInventoryItemNotFoundException) {
            log.error("Inventory item not found: {}", e.getMessage());
            return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item not found"), HttpStatus.NOT_FOUND);
        } else if (e instanceof DomainInventoryItemAlreadyExistsException) {
            log.error("Inventory item already exists: {}", e.getMessage());
            return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item already exists"), HttpStatus.BAD_REQUEST);
        } else if (e instanceof DomainInventoryItemQuantityException) {
            log.error("Invalid inventory item quantity: {}", e.getMessage());
            return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Invalid item quantity"), HttpStatus.BAD_REQUEST);
        } else {
            log.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}