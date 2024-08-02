package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterInventoryDeleteResponse; 
 import ai.shreds.domain.DomainInventoryItemAlreadyExistsException; 
 import ai.shreds.domain.DomainInventoryItemNotFoundException; 
 import ai.shreds.domain.DomainInventoryItemQuantityException; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.stereotype.Component; 
  
 @Component 
 public class ApplicationInventoryServiceException { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationInventoryServiceException.class); 
  
     public ResponseEntity<AdapterInventoryDeleteResponse> handleException(Exception e) { 
         if (e instanceof DomainInventoryItemNotFoundException) { 
             logger.error("Inventory item not found: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item not found"), HttpStatus.NOT_FOUND); 
         } else if (e instanceof DomainInventoryItemAlreadyExistsException) { 
             logger.error("Inventory item already exists: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item already exists"), HttpStatus.BAD_REQUEST); 
         } else if (e instanceof DomainInventoryItemQuantityException) { 
             logger.error("Invalid inventory item quantity: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Invalid item quantity"), HttpStatus.BAD_REQUEST); 
         } else { 
             logger.error("Internal server error: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
 } 
 // Note: Consider using Lombok's @Slf4j annotation for logging to reduce boilerplate code.