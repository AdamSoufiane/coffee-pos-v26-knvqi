package ai.shreds.application; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice; 
import com.mongodb.MongoException; 
import org.springframework.web.bind.MethodArgumentNotValidException; 
import lombok.extern.slf4j.Slf4j; 

@RestControllerAdvice 
@Slf4j 
public class ApplicationInventoryException { 

    private static final Logger log = LoggerFactory.getLogger(ApplicationInventoryException.class); 

    @ExceptionHandler(Exception.class) 
    public ResponseEntity<String> handleException(Exception exception) { 
        // Log the exception details 
        log.error("An error occurred: ", exception); 
        // Return a generic error response 
        return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR); 
    } 

    @ExceptionHandler(IllegalArgumentException.class) 
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) { 
        // Log the exception details 
        log.error("Invalid input: ", exception); 
        // Return a bad request response 
        return new ResponseEntity<>("Invalid input provided.", HttpStatus.BAD_REQUEST); 
    } 

    @ExceptionHandler(MongoException.class) 
    public ResponseEntity<String> handleMongoException(MongoException exception) { 
        // Log the exception details 
        log.error("MongoDB error: ", exception); 
        // Return a database error response 
        return new ResponseEntity<>("Database error occurred.", HttpStatus.INTERNAL_SERVER_ERROR); 
    } 

    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException exception) { 
        // Log the exception details 
        log.error("Validation error: ", exception); 
        // Return a validation error response 
        return new ResponseEntity<>("Validation failed: " + exception.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST); 
    } 

    // Additional specific exception handlers can be added here 
}