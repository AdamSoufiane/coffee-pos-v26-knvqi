package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.NoArgsConstructor; 
  
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedException extends RuntimeException { 
     public SharedException(String message) { 
         super(message); 
     } 
  
     public SharedException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     public SharedException(Throwable cause) { 
         super(cause); 
     } 
 } 
  
 /* Implementation Note: Consider using Lombok annotations such as @NoArgsConstructor and @AllArgsConstructor to reduce boilerplate code for constructors. */