package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedUUIDValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedDecimalValueObject; 
 import ai.shreds.shared.SharedBooleanValueObject; 
 import ai.shreds.application.ApplicationSharedProductDTO; 
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
 import lombok.Builder; 
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.Column; 
 import javax.persistence.Table; 
 import java.util.UUID; 
 import java.util.Optional; 
 import java.math.BigDecimal; 
  
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Builder 
 @Entity 
 @Table(name = "product") 
 public class DomainProductEntity { 
     @Id 
     private UUID id; 
  
     @Column(name = "name", nullable = false, unique = true) 
     private String name; 
  
     @Column(name = "description", nullable = false) 
     private String description; 
  
     @Column(name = "price", nullable = false) 
     private BigDecimal price; 
  
     @Column(name = "availability", nullable = false) 
     private Boolean availability; 
  
     @Column(name = "category_id", nullable = false) 
     private UUID categoryId; 
  
     private final DomainProductRepositoryPort productRepository; 
     private final DomainCategoryRepositoryPort categoryRepository; 
  
     public DomainProductEntity(UUID id, String name, String description, BigDecimal price, Boolean availability, UUID categoryId, DomainProductRepositoryPort productRepository, DomainCategoryRepositoryPort categoryRepository) { 
         this.id = id; 
         this.name = name; 
         this.description = description; 
         this.price = price; 
         this.availability = availability; 
         this.categoryId = categoryId; 
         this.productRepository = productRepository; 
         this.categoryRepository = categoryRepository; 
     } 
  
     public ApplicationSharedProductDTO toSharedProductDTO() { 
         return ApplicationSharedProductDTO.builder() 
                 .id(this.id) 
                 .name(this.name) 
                 .description(this.description) 
                 .price(this.price) 
                 .availability(this.availability) 
                 .categoryId(this.categoryId) 
                 .build(); 
     } 
  
     public static DomainProductEntity fromSharedProductDTO(ApplicationSharedProductDTO dto) { 
         return DomainProductEntity.builder() 
                 .id(dto.getId()) 
                 .name(dto.getName()) 
                 .description(dto.getDescription()) 
                 .price(dto.getPrice()) 
                 .availability(dto.getAvailability()) 
                 .categoryId(dto.getCategoryId()) 
                 .build(); 
     } 
  
     public void validateProductData() { 
         if (productRepository.existsByName(this.name)) { 
             throw new IllegalArgumentException("Product name must be unique."); 
         } 
         if (this.price.compareTo(BigDecimal.ZERO) <= 0) { 
             throw new IllegalArgumentException("Product price must be a positive value."); 
         } 
         if (!categoryRepository.existsById(this.categoryId)) { 
             throw new IllegalArgumentException("Category ID must exist in the database."); 
         } 
     } 
 }