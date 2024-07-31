package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class AdapterProductControllerException {

    @Data
    @AllArgsConstructor
    public static class Error {
        private String message;
        private String error;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception, WebRequest request) {
        Error errorResponse = new Error("Error occurred", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}