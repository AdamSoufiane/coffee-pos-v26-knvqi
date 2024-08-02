package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryItemEntity; 
 import ai.shreds.domain.DomainInventoryItemNotFoundException; 
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import ai.shreds.domain.DomainInventoryItemAlreadyExistsException; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.persistence.TypedQuery; 
 import java.util.List; 
 import java.util.Optional; 
  
 @Repository 
 public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImpl.class); 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainInventoryItemEntity item) { 
         Optional<DomainInventoryItemEntity> existingItem = findByName(item.getName()); 
         if (existingItem.isPresent()) { 
             throw new DomainInventoryItemAlreadyExistsException(item.getName()); 
         } 
         try { 
             entityManager.persist(item); 
             logger.info("Inventory item saved: {}", item); 
         } catch (Exception e) { 
             logger.error("Error saving item", e); 
             throw new RuntimeException("Error saving item", e); 
         } 
     } 
  
     @Override 
     public DomainInventoryItemEntity findById(ObjectId id) { 
         try { 
             DomainInventoryItemEntity item = entityManager.find(DomainInventoryItemEntity.class, id); 
             if (item == null) { 
                 throw new DomainInventoryItemNotFoundException(id); 
             } 
             return item; 
         } catch (Exception e) { 
             logger.error("Error finding item by id", e); 
             throw new RuntimeException("Error finding item by id", e); 
         } 
     } 
  
     @Override 
     public List<DomainInventoryItemEntity> findAll() { 
         try { 
             TypedQuery<DomainInventoryItemEntity> query = entityManager.createQuery("SELECT i FROM DomainInventoryItemEntity i", DomainInventoryItemEntity.class); 
             return query.getResultList(); 
         } catch (Exception e) { 
             logger.error("Error finding all items", e); 
             throw new RuntimeException("Error finding all items", e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void deleteById(ObjectId id) { 
         DomainInventoryItemEntity item = findById(id); 
         if (item == null) { 
             throw new DomainInventoryItemNotFoundException(id); 
         } 
         try { 
             entityManager.remove(item); 
             logger.info("Inventory item deleted: {}", item); 
         } catch (Exception e) { 
             logger.error("Error deleting item by id", e); 
             throw new RuntimeException("Error deleting item by id", e); 
         } 
     } 
  
     private Optional<DomainInventoryItemEntity> findByName(String name) { 
         TypedQuery<DomainInventoryItemEntity> query = entityManager.createQuery("SELECT i FROM DomainInventoryItemEntity i WHERE i.name = :name", DomainInventoryItemEntity.class); 
         query.setParameter("name", name); 
         return query.getResultList().stream().findFirst(); 
     } 
 }