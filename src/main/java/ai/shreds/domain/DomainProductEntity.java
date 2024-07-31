package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.persistence.Column; 
 import javax.persistence.Entity; 
 import javax.persistence.GeneratedValue; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "Product") 
 public class DomainProductEntity { 
  
     @Id 
     @GeneratedValue 
     @Column(name = "id", updatable = false, nullable = false) 
     private UUID id; 
  
     @Column(name = "name", nullable = false, length = 255) 
     private String name; 
  
     @Column(name = "description", nullable = false) 
     private String description; 
  
     @Column(name = "price", nullable = false, precision = 10, scale = 2) 
     private BigDecimal price; 
  
     @Column(name = "availability", nullable = false) 
     private Boolean availability; 
  
     @Column(name = "category_id", nullable = false) 
     private UUID categoryId; 
 } 
 