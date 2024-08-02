package ai.shreds.domain; 
  
 import lombok.NoArgsConstructor; 
  
 /** 
  * Exception class for handling inventory-related exceptions in the domain layer. 
  */ 
 @NoArgsConstructor 
 public class DomainInventoryException extends RuntimeException { 
     /** 
      * Constructs a new DomainInventoryException with the specified detail message. 
      * @param message the detail message 
      */ 
     public DomainInventoryException(String message) { 
         super(message); 
     } 
  
     /** 
      * Constructs a new DomainInventoryException with the specified detail message and cause. 
      * @param message the detail message 
      * @param cause the cause 
      */ 
     public DomainInventoryException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     /** 
      * Constructs a new DomainInventoryException with the specified cause. 
      * @param cause the cause 
      */ 
     public DomainInventoryException(Throwable cause) { 
         super(cause); 
     } 
 }