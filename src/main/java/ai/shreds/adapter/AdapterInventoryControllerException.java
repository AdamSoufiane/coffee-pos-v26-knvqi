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
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<AdapterInventoryDeleteResponse> handleException(Exception e) { 
         if (e instanceof DomainInventoryItemNotFoundException) { 
             logger.error("Item not found: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item not found: " + e.getMessage()), HttpStatus.NOT_FOUND); 
         } else if (e instanceof DomainInventoryItemAlreadyExistsException) { 
             logger.error("Item already exists: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Item already exists: " + e.getMessage()), HttpStatus.BAD_REQUEST); 
         } else if (e instanceof DomainInventoryItemQuantityException) { 
             logger.error("Invalid item quantity: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Invalid item quantity: " + e.getMessage()), HttpStatus.BAD_REQUEST); 
         } else { 
             logger.error("Internal server error: {}", e.getMessage()); 
             return new ResponseEntity<>(new AdapterInventoryDeleteResponse("Internal server error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
 } 
 