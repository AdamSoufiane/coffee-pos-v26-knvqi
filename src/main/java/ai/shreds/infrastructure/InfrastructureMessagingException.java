package ai.shreds.infrastructure; 
  
 /** 
  * Exception thrown when there is an error in messaging operations. 
  */ 
 public class InfrastructureMessagingException extends RuntimeException { 
  
     private static final long serialVersionUID = 1L; 
  
     /** 
      * Constructs a new InfrastructureMessagingException with {@code null} as its detail message. 
      */ 
     public InfrastructureMessagingException() { 
         super(); 
     } 
  
     /** 
      * Constructs a new InfrastructureMessagingException with the specified detail message. 
      * 
      * @param message the detail message. 
      */ 
     public InfrastructureMessagingException(String message) { 
         super(message); 
     } 
  
     /** 
      * Constructs a new InfrastructureMessagingException with the specified detail message and cause. 
      * 
      * @param message the detail message. 
      * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). 
      */ 
     public InfrastructureMessagingException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     /** 
      * Constructs a new InfrastructureMessagingException with the specified cause. 
      * 
      * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). 
      */ 
     public InfrastructureMessagingException(Throwable cause) { 
         super(cause); 
     } 
 } 
 