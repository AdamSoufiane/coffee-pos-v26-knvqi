package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.data.jpa.repository.JpaRepository; 
 import org.springframework.stereotype.Repository; 
 import java.util.Optional; 
 import java.util.UUID; 
 import lombok.RequiredArgsConstructor; 
  
 /** 
  * Repository implementation for Product entities. 
  */ 
 @Repository 
 @RequiredArgsConstructor 
 public class InfrastructureProductRepository implements DomainProductRepositoryPort { 
  
     @Autowired 
     private final JpaRepository<DomainProductEntity, UUID> jpaRepository; 
  
     /** 
      * Saves a given product entity to the database. 
      * @param product the product entity to save 
      * @return the saved product entity 
      */ 
     @Override 
     public DomainProductEntity save(DomainProductEntity product) { 
         return jpaRepository.save(product); 
     } 
  
     /** 
      * Finds a product entity by its ID. 
      * @param id the UUID of the product entity 
      * @return an Optional containing the found product entity, or empty if not found 
      */ 
     @Override 
     public Optional<DomainProductEntity> findById(UUID id) { 
         return jpaRepository.findById(id); 
     } 
  
     /** 
      * Deletes a product entity by its ID. 
      * @param id the UUID of the product entity 
      */ 
     @Override 
     public void deleteById(UUID id) { 
         jpaRepository.deleteById(id); 
     } 
 } 
 