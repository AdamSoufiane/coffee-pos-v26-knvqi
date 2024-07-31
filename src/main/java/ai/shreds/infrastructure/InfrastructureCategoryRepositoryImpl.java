package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.data.jpa.repository.JpaRepository; 
 import org.springframework.stereotype.Repository; 
 import lombok.AllArgsConstructor; 
 import lombok.NoArgsConstructor; 
  
 import java.util.Optional; 
 import java.util.UUID; 
  
 @Repository 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     private SpringDataCategoryRepository springDataCategoryRepository; 
  
     /** 
      * Method to find a category by its ID. 
      * @param id The UUID of the category to be found. 
      * @return An Optional containing the found category or an empty Optional if not found. 
      */ 
     @Override 
     public Optional<DomainCategoryEntity> findById(UUID id) { 
         try { 
             return springDataCategoryRepository.findById(id); 
         } catch (Exception e) { 
             throw new InfrastructureCategoryRepositoryImplException("Error finding category by ID: " + e.getMessage(), e); 
         } 
     } 
 } 
  
 interface SpringDataCategoryRepository extends JpaRepository<DomainCategoryEntity, UUID> { 
 } 
  
 @AllArgsConstructor 
 @NoArgsConstructor 
 class InfrastructureCategoryRepositoryImplException extends RuntimeException { 
     public InfrastructureCategoryRepositoryImplException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }