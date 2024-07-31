package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
  
 import java.util.Optional; 
 import java.util.UUID; 
  
 @Repository 
 public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     private final JpaCategoryRepository jpaCategoryRepository; 
  
     @Autowired 
     public InfrastructureCategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository) { 
         this.jpaCategoryRepository = jpaCategoryRepository; 
     } 
  
     @Override 
     public Optional<DomainCategoryEntity> findById(UUID categoryId) { 
         try { 
             return jpaCategoryRepository.findById(categoryId).map(this::toDomainEntity); 
         } catch (Exception e) { 
             // Handle exception or log it 
             return Optional.empty(); 
         } 
     } 
  
     private DomainCategoryEntity toDomainEntity(CategoryEntity categoryEntity) { 
         return new DomainCategoryEntity( 
                 categoryEntity.getId(), 
                 categoryEntity.getName(), 
                 categoryEntity.getDescription() 
         ); 
     } 
 } 
  
 interface JpaCategoryRepository extends org.springframework.data.jpa.repository.JpaRepository<CategoryEntity, UUID> {} 
  
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
 import javax.persistence.Entity; 
 import javax.persistence.GeneratedValue; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
  
 @Entity 
 @Table(name = "category") 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class CategoryEntity { 
  
     @Id 
     @GeneratedValue 
     private UUID id; 
  
     private String name; 
  
     private String description; 
 }