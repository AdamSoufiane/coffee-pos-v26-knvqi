package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.stereotype.Component; 
  
 @Component 
 public class InfrastructureInventoryException { 
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryException.class); 
  
     public void handleException(Exception exception) { 
         if (exception instanceof DataAccessException) { 
             logger.error("Data access exception occurred: {}", exception.getMessage(), exception); 
             throw new RuntimeException("Failed to access data from MongoDB", exception); 
         } else { 
             logger.error("An unexpected error occurred: {}", exception.getMessage(), exception); 
             throw new RuntimeException("An unexpected error occurred", exception); 
         } 
     } 
 } 
 // Note: Add Lombok annotations for any required getters and setters.