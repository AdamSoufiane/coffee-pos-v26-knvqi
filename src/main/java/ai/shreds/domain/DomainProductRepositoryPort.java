package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import java.util.Optional; 
 import java.util.UUID; 
 import org.springframework.dao.DataAccessException; 
  
 /** 
  * Repository port for product entities in the domain layer. 
  * This interface defines methods for CRUD operations on Product entities. 
  */ 
 public interface DomainProductRepositoryPort { 
     /** 
      * Saves a given product entity to the database. 
      *  
      * @param product the product entity to be saved 
      * @return the saved product entity 
      * @throws DataAccessException if there is an error during the save operation 
      */ 
     DomainProductEntity save(DomainProductEntity product) throws DataAccessException; 
  
     /** 
      * Finds a product entity by its ID. 
      *  
      * @param id the UUID of the product entity 
      * @return an Optional containing the product entity if found 
      * @throws DataAccessException if there is an error during the find operation 
      */ 
     Optional<DomainProductEntity> findById(UUID id) throws DataAccessException; 
  
     /** 
      * Deletes a product entity by its ID. 
      *  
      * @param id the UUID of the product entity to be deleted 
      * @throws DataAccessException if there is an error during the delete operation 
      */ 
     void deleteById(UUID id) throws DataAccessException; 
 }