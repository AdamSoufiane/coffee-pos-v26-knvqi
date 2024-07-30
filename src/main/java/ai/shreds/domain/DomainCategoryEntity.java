package ai.shreds.domain; 
  
 import lombok.Getter; 
 import lombok.Setter; 
 import org.hibernate.annotations.GenericGenerator; 
  
 import javax.persistence.*; 
 import java.util.List; 
 import java.util.UUID; 
  
 @Entity 
 @Table(name = "categories") 
 @Getter 
 @Setter 
 public class DomainCategoryEntity { 
  
     @Id 
     @GeneratedValue(generator = "UUID") 
     @GenericGenerator( 
         name = "UUID", 
         strategy = "org.hibernate.id.UUIDGenerator" 
     ) 
     @Column(name = "id", updatable = false, nullable = false) 
     private UUID id; 
  
     @Column(name = "name", length = 255, nullable = false) 
     private String name; 
  
     @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) 
     private List<DomainProductEntity> products; 
 } 
 