package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.persistence.Column; 
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import java.util.UUID; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "category") 
 public class DomainCategoryEntity { 
  
     @Id 
     @Column(name = "id", nullable = false) 
     private UUID id; 
  
     @Column(name = "name", nullable = false, length = 255) 
     private String name; 
  
     @Column(name = "description", columnDefinition = "TEXT") 
     private String description; 
 } 
 