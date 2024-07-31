package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.stereotype.Component; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import ai.shreds.domain.DomainProductEntity; 
 import java.util.UUID; 
 import lombok.extern.slf4j.Slf4j; 
  
 /** 
  * Handles exceptions that occur within the InfrastructureProductRepositoryImpl class. 
  */ 
 @Slf4j 
 @Component 
 public class InfrastructureProductRepositoryImplException { 
  
     /** 
      * Handles exceptions by logging them and rethrowing as custom runtime exceptions. 
      * 
      * @param exception the exception to handle 
      */ 
     public void handleException(Exception exception) { 
         if (exception instanceof DataAccessException) { 
             log.error("Database access error: {}", exception.getMessage(), exception); 
             throw new CustomDatabaseException("Database access error", exception); 
         } else if (exception instanceof IllegalArgumentException) { 
             log.error("Illegal argument error: {}", exception.getMessage(), exception); 
             throw new CustomIllegalArgumentException("Illegal argument error", exception); 
         } else { 
             log.error("Unexpected error: {}", exception.getMessage(), exception); 
             throw new CustomUnexpectedErrorException("Unexpected error", exception); 
         } 
     } 
 } 
  
 // Custom exceptions 
 class CustomDatabaseException extends RuntimeException { 
     public CustomDatabaseException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class CustomIllegalArgumentException extends RuntimeException { 
     public CustomIllegalArgumentException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class CustomUnexpectedErrorException extends RuntimeException { 
     public CustomUnexpectedErrorException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }