package ai.shreds.domain; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 public class DomainInventoryServiceException { 
     private static final Logger logger = LoggerFactory.getLogger(DomainInventoryServiceException.class); 
  
     /** 
      * Handles generic exceptions. 
      * @param e The exception to handle. 
      */ 
     public void handleException(Exception e) { 
         logger.error("An error occurred in handleException: {}", e.getMessage(), e); 
         throw new RuntimeException(e); 
     } 
  
     /** 
      * Handles not found exceptions for inventory items. 
      * @param id The ID of the inventory item that was not found. 
      */ 
     public void handleException(ObjectId id) { 
         String message = "Inventory item with id " + id + " not found."; 
         logger.error("An error occurred in handleException(ObjectId): {}", message); 
         throw new DomainInventoryItemNotFoundException(message); 
     } 
  
     /** 
      * Handles already exists exceptions for inventory items. 
      * @param name The name of the inventory item that already exists. 
      */ 
     public void handleException(String name) { 
         String message = "Inventory item with name " + name + " already exists."; 
         logger.error("An error occurred in handleException(String): {}", message); 
         throw new DomainInventoryItemAlreadyExistsException(message); 
     } 
  
     /** 
      * Handles quantity threshold exceptions for inventory items. 
      * @param quantity The current quantity of the inventory item. 
      * @param threshold The threshold value for the inventory item. 
      */ 
     public void handleException(Integer quantity, Integer threshold) { 
         String message = "Inventory item quantity " + quantity + " is below the threshold " + threshold + "."; 
         logger.error("An error occurred in handleException(Integer, Integer): {}", message); 
         throw new DomainInventoryItemQuantityException(message); 
     } 
 } 
 