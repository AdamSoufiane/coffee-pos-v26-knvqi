package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterUpdateProductRequest { 
     private String name; 
     private String description; 
     private BigDecimal price; 
     private Boolean availability; 
     private UUID categoryId; 
 } 
 // Note: Include Lombok annotations for getters, setters, constructors, and builders.