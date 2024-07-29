package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Positive; 
 import java.math.BigDecimal; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedUpdateProductRequestParams { 
     @NotBlank 
     private String name; 
  
     @NotBlank 
     private String description; 
  
     @NotNull 
     @Positive 
     private BigDecimal price; 
  
     @NotNull 
     private Boolean availability; 
 }