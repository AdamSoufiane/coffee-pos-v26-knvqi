package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedDeleteProductResponseDTO; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import java.util.UUID; 
 import java.util.logging.Logger; 
  
 @Service 
 public class DomainDeleteProductService implements DomainProductServicePort { 
  
     private final DomainProductRepositoryPort repository; 
     private static final Logger LOGGER = Logger.getLogger(DomainDeleteProductService.class.getName()); 
  
     @Autowired 
     public DomainDeleteProductService(DomainProductRepositoryPort repository) { 
         this.repository = repository; 
     } 
  
     @Override 
     public SharedDeleteProductResponseDTO deleteProduct(UUID id) { 
         return repository.findById(id).map(product -> { 
             repository.deleteById(id); 
             LOGGER.info("Product with ID " + id + " deleted successfully."); 
             return new SharedDeleteProductResponseDTO("Operation successful"); 
         }).orElseThrow(() -> { 
             LOGGER.severe("Product with ID " + id + " not found."); 
             return new ProductNotFoundException("Product not found"); 
         }); 
     } 
 } 
  
 class ProductNotFoundException extends RuntimeException { 
     public ProductNotFoundException(String message) { 
         super(message); 
     } 
 } 
 // Note: Consider adding Lombok annotations for logging and exception handling.