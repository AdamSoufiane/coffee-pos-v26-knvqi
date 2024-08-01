package ai.shreds.infrastructure; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.web.bind.annotation.ResponseStatus; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Exception thrown when there is an error in the InfrastructureMessagingClient. 
  */ 
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
 public class InfrastructureMessagingClientException extends RuntimeException { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureMessagingClientException.class); 
  
     /** 
      * Constructs a new InfrastructureMessagingClientException with the specified detail message. 
      * 
      * @param message the detail message. 
      */ 
     public InfrastructureMessagingClientException(String message) { 
         super(message); 
         logger.error("InfrastructureMessagingClientException: {}", message); 
     } 
  
     /** 
      * Constructs a new InfrastructureMessagingClientException with the specified detail message and cause. 
      * 
      * @param message the detail message. 
      * @param cause the cause of the exception. 
      */ 
     public InfrastructureMessagingClientException(String message, Throwable cause) { 
         super(message, cause); 
         logger.error("InfrastructureMessagingClientException: {}", message, cause); 
     } 
 }