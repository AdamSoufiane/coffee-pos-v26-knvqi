package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 /** 
  * Represents a product in the domain layer. 
  * This entity includes fields such as id, name, description, price, availability, created_at, and updated_at. 
  */ 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainProductDomainEntity { 
     private UUID id; 
     private String name; 
     private String description; 
     private BigDecimal price; 
     private Boolean availability; 
     private Timestamp created_at; 
     private Timestamp updated_at; 
  
     /** 
      * Validates the product data before synchronization or real-time update. 
      * 
      * @throws DomainProductValidationException if any validation rule is violated. 
      */ 
     public void validateProductData() throws DomainProductValidationException { 
         if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) { 
             throw new DomainProductValidationException("Price must be a positive decimal value."); 
         } 
         if (name == null || name.isEmpty()) { 
             throw new DomainProductValidationException("Name cannot be null or empty."); 
         } 
         if (description == null || description.isEmpty()) { 
             throw new DomainProductValidationException("Description cannot be null or empty."); 
         } 
         if (availability == null) { 
             throw new DomainProductValidationException("Availability cannot be null."); 
         } 
     } 
  
     /** 
      * Transforms the product data into the format required by the Menu Service. 
      * 
      * @return the transformed product data. 
      */ 
     public DomainProductDomainEntity transformProductData() { 
         // Transformation logic here, if needed 
         return this; 
     } 
 } 
 