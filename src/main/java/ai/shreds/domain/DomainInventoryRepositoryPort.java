package ai.shreds.domain; 
  
 import java.util.List; 
 import org.bson.types.ObjectId; 
  
 public interface DomainInventoryRepositoryPort { 
     void save(DomainInventoryItemEntity item); 
     DomainInventoryItemEntity findById(ObjectId id); 
     List<DomainInventoryItemEntity> findAll(); 
     void deleteById(ObjectId id); 
 } 
  
 // Note: Use Lombok annotations in DomainInventoryItemEntity to generate getters and setters.