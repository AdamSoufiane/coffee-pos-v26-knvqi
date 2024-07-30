package ai.shreds.adapter; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.MethodArgumentNotValidException; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.context.request.WebRequest; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @ControllerAdvice 
 public class AdapterExceptionHandler { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterExceptionHandler.class); 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception exception, WebRequest request) { 
         logger.error("Exception: ", exception); 
         if (exception instanceof ResourceNotFoundException) { 
             return new ResponseEntity<>("{ \"message\": \"Error occurred\", \"error\": \"No products found\" }", HttpStatus.NOT_FOUND); 
         } else if (exception instanceof InvalidSearchParameterException) { 
             return new ResponseEntity<>("{ \"message\": \"Error occurred\", \"error\": \"Invalid search parameter\" }", HttpStatus.BAD_REQUEST); 
         } else { 
             return new ResponseEntity<>("{ \"message\": \"Error occurred\", \"error\": \"Internal server error\" }", HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
  
     @ExceptionHandler(MethodArgumentNotValidException.class) 
     public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException exception) { 
         logger.error("Validation Exception: ", exception); 
         return new ResponseEntity<>("{ \"message\": \"Error occurred\", \"error\": \"Invalid input\" }", HttpStatus.BAD_REQUEST); 
     } 
  
     // Custom exceptions can be defined here or in separate files 
     public static class ResourceNotFoundException extends RuntimeException { 
         public ResourceNotFoundException(String message) { 
             super(message); 
         } 
     } 
  
     public static class InvalidSearchParameterException extends RuntimeException { 
         public InvalidSearchParameterException(String message) { 
             super(message); 
         } 
     } 
 } 
 