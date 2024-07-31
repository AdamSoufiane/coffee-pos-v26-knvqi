package ai.shreds.infrastructure; 
  
 import ai.shreds.shared.AdapterErrorResponse; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataIntegrityViolationException; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
 import javax.persistence.EntityNotFoundException; 
 import java.sql.SQLException; 
  
 @RestControllerAdvice 
 public class InfrastructureCategoryRepositoryImplException { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImplException.class); 
  
     @ExceptionHandler(EntityNotFoundException.class) 
     public ResponseEntity<AdapterErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) { 
         logger.error("Entity not found", e); 
         AdapterErrorResponse errorResponse = new AdapterErrorResponse(); 
         errorResponse.setMessage("Entity not found"); 
         errorResponse.setError(e.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
     } 
  
     @ExceptionHandler(SQLException.class) 
     public ResponseEntity<AdapterErrorResponse> handleSQLException(SQLException e) { 
         logger.error("Database error", e); 
         AdapterErrorResponse errorResponse = new AdapterErrorResponse(); 
         errorResponse.setMessage("Database error"); 
         errorResponse.setError(e.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     @ExceptionHandler(DataIntegrityViolationException.class) 
     public ResponseEntity<AdapterErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) { 
         logger.error("Data integrity violation", e); 
         AdapterErrorResponse errorResponse = new AdapterErrorResponse(); 
         errorResponse.setMessage("Data integrity violation"); 
         errorResponse.setError(e.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT); 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<AdapterErrorResponse> handleException(Exception e) { 
         logger.error("An unexpected error occurred", e); 
         AdapterErrorResponse errorResponse = new AdapterErrorResponse(); 
         errorResponse.setMessage("An unexpected error occurred"); 
         errorResponse.setError(e.getMessage()); 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
 