package ai.shreds.domain; 
  
 /** 
  * Exception thrown when there is an error during the transformation of product data. 
  */ 
 public class DomainProductTransformationException extends RuntimeException { 
  
     public DomainProductTransformationException() { 
         super(); 
     } 
  
     public DomainProductTransformationException(String message) { 
         super(message); 
     } 
  
     public DomainProductTransformationException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     public DomainProductTransformationException(Throwable cause) { 
         super(cause); 
     } 
 }