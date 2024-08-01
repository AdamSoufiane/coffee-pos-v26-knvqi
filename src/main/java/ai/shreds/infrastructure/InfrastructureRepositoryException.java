package ai.shreds.infrastructure; 
  
 import lombok.Getter; 
  
 /** 
  * Exception class to handle repository-related errors in the infrastructure layer. 
  */ 
 @Getter 
 public class InfrastructureRepositoryException extends RuntimeException { 
  
     /** 
      * Constructs a new InfrastructureRepositoryException with the specified detail message. 
      * 
      * @param message the detail message 
      */ 
     public InfrastructureRepositoryException(String message) { 
         super(message); 
     } 
  
     /** 
      * Constructs a new InfrastructureRepositoryException with the specified detail message and cause. 
      * 
      * @param message the detail message 
      * @param cause the cause of the exception 
      */ 
     public InfrastructureRepositoryException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     /** 
      * Constructs a new InfrastructureRepositoryException with the specified cause. 
      * 
      * @param cause the cause of the exception 
      */ 
     public InfrastructureRepositoryException(Throwable cause) { 
         super(cause); 
     } 
 } 
 