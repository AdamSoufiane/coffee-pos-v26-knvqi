package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Positive; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 /** 
  * DTO for creating a new product. 
  * Contains all necessary fields to create a product in the catalogue. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterCreateProductRequest { 
     /** 
      * Name of the product. Must be unique within the catalogue. 
      */ 
     @NotBlank(message = "Product name is mandatory") 
     private String name; 
  
     /** 
      * Detailed description of the product. 
      */ 
     @NotBlank(message = "Product description is mandatory") 
     private String description; 
  
     /** 
      * Price of the product. Must be a positive value. 
      */ 
     @NotNull(message = "Product price is mandatory") 
     @Positive(message = "Product price must be positive") 
     private BigDecimal price; 
  
     /** 
      * Indicates whether the product is currently available. 
      */ 
     @NotNull(message = "Product availability is mandatory") 
     private Boolean availability; 
  
     /** 
      * ID of the category to which the product belongs. Must exist in the database. 
      */ 
     @NotNull(message = "Category ID is mandatory") 
     private UUID categoryId; 
 }