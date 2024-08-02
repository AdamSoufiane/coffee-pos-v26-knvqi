package ai.shreds.domain.exception; 
  
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 /** 
  * Exception thrown when an inventory item is not found by its ID. 
  */ 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainInventoryItemNotFoundException extends RuntimeException { 
     /** 
      * Constructs a new exception with the specified detail message. 
      * @param id the ID of the inventory item that was not found. 
      */ 
     public DomainInventoryItemNotFoundException(String id) { 
         super("Inventory item not found with id: " + id); 
     } 
  
     /** 
      * Constructs a new exception with the specified detail message and cause. 
      * @param id the ID of the inventory item that was not found. 
      * @param cause the cause of the exception. 
      */ 
     public DomainInventoryItemNotFoundException(String id, Throwable cause) { 
         super("Inventory item not found with id: " + id, cause); 
     } 
 }