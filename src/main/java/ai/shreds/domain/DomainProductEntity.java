package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.persistence.*; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Size; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "products") 
 public class DomainProductEntity { 
     @Id 
     @GeneratedValue(strategy = GenerationType.AUTO) 
     private UUID id; 
  
     @NotNull 
     @Size(max = 255) 
     @Column(name = "name", nullable = false, length = 255) 
     private String name; 
  
     @Column(name = "description", columnDefinition = "TEXT") 
     private String description; 
  
     @NotNull 
     @Column(name = "price", nullable = false, precision = 10, scale = 2) 
     private BigDecimal price; 
  
     @NotNull 
     @Column(name = "availability", nullable = false) 
     private Boolean availability; 
  
     @ManyToOne(fetch = FetchType.EAGER) 
     @JoinColumn(name = "category_id", nullable = false) 
     private DomainCategoryEntity category; 
 } 
 // Note: Include Lombok annotations for getters, setters, and constructors. 
 // Note: Include JPA validation annotations for fields. 
 // Note: Include a custom validator for price to ensure it's non-negative.