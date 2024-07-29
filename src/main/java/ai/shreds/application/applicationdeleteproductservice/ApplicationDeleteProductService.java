package ai.shreds.application; 
  
 import ai.shreds.domain.DomainProductServicePort; 
 import ai.shreds.shared.SharedDeleteProductResponseDTO; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
  
 import java.util.UUID; 
 import java.util.logging.Logger; 
  
 /** 
  * Service class responsible for handling product deletion logic. 
  */ 
 @Service 
 public class ApplicationDeleteProductService implements ApplicationDeleteProductInputPort { 
  
     private final DomainProductServicePort domainService; 
     private static final Logger LOGGER = Logger.getLogger(ApplicationDeleteProductService.class.getName()); 
  
     @Autowired 
     public ApplicationDeleteProductService(DomainProductServicePort domainService) { 
         this.domainService = domainService; 
     } 
  
     /** 
      * Deletes a product by its ID. 
      * 
      * @param id the UUID of the product to be deleted 
      * @return SharedDeleteProductResponseDTO containing the success message 
      */ 
     @Override 
     public SharedDeleteProductResponseDTO deleteProduct(UUID id) { 
         LOGGER.info("Attempting to delete product with ID: " + id); 
         try { 
             SharedDeleteProductResponseDTO response = domainService.deleteProduct(id); 
             LOGGER.info("Successfully deleted product with ID: " + id); 
             return response; 
         } catch (Exception e) { 
             LOGGER.severe("Failed to delete product with ID: " + id + ". Error: " + e.getMessage()); 
             throw new RuntimeException("Failed to delete product", e); 
         } 
     } 
 } 
 