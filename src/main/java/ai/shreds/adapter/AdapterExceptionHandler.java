package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import ai.shreds.adapter.AdapterExceptionHandler.ResourceNotFoundException;
import ai.shreds.adapter.AdapterExceptionHandler.InvalidSearchParameterException;

@ControllerAdvice
public class AdapterExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdapterExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception, WebRequest request) {
        logger.error("Exception: ", exception);
        if (exception instanceof ResourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JSONObject().put("message", "Resource not found").put("error", exception.getMessage()).toString());
        } else if (exception instanceof InvalidSearchParameterException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JSONObject().put("message", "Invalid search parameter").put("error", exception.getMessage()).toString());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JSONObject().put("message", "Internal server error").put("error", "An unexpected error occurred").toString());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        logger.error("Validation Exception: ", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JSONObject().put("message", "Invalid input").put("error", "Validation failed").toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidSearchParameterException extends RuntimeException {
        public InvalidSearchParameterException(String message) {
            super(message);
        }
    }
}