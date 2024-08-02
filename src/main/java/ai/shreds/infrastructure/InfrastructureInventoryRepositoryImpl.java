package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import ai.shreds.shared.SharedInventoryDTO; 
 import ai.shreds.domain.DomainInventoryEntity; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.data.mongodb.core.MongoTemplate; 
 import org.springframework.data.mongodb.core.query.Criteria; 
 import org.springframework.data.mongodb.core.query.Query; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Repository 
 @RequiredArgsConstructor 
 @Slf4j 
 public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort { 
  
     private final MongoTemplate mongoTemplate; 
  
     @Override 
     public void save(SharedInventoryDTO inventory) { 
         try { 
             DomainInventoryEntity entity = new DomainInventoryEntity(); 
             entity.setId(inventory.getId()); 
             entity.setName(inventory.getName()); 
             entity.setQuantity(inventory.getQuantity()); 
             entity.setThreshold(inventory.getThreshold()); 
             mongoTemplate.save(entity); 
         } catch (DataAccessException e) { 
             log.error("Failed to save inventory data", e); 
             throw new InfrastructureInventoryRepositoryException("Failed to save inventory data", e); 
         } 
     } 
  
     @Override 
     public SharedInventoryDTO findById(String id) { 
         try { 
             Query query = new Query(Criteria.where("id").is(id)); 
             DomainInventoryEntity entity = mongoTemplate.findOne(query, DomainInventoryEntity.class); 
             if (entity == null) { 
                 return null; 
             } 
             SharedInventoryDTO dto = new SharedInventoryDTO(); 
             dto.setId(entity.getId()); 
             dto.setName(entity.getName()); 
             dto.setQuantity(entity.getQuantity()); 
             dto.setThreshold(entity.getThreshold()); 
             return dto; 
         } catch (DataAccessException e) { 
             log.error("Failed to retrieve inventory data", e); 
             throw new InfrastructureInventoryRepositoryException("Failed to retrieve inventory data", e); 
         } 
     } 
 } 
  
 class InfrastructureInventoryRepositoryException extends RuntimeException { 
     public InfrastructureInventoryRepositoryException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }