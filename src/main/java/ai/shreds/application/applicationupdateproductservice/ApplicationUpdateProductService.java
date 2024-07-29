package ai.shreds.application; 
  
 import ai.shreds.domain.DomainProductServicePort; 
 import ai.shreds.shared.SharedProductResponseDTO; 
 import ai.shreds.shared.SharedUpdateProductRequestParams; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import java.util.UUID; 
 import lombok.RequiredArgsConstructor; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationUpdateProductService implements ApplicationUpdateProductInputPort { 
  
     private final DomainProductServicePort domainService; 
  
     /** 
      * Updates an existing product in the catalogue. 
      * 
      * @param id the UUID of the product to be updated 
      * @param requestParams the updated product details 
      * @return the updated product details encapsulated in a SharedProductResponseDTO object 
      */ 
     @Override 
     public SharedProductResponseDTO updateProduct(UUID id, SharedUpdateProductRequestParams requestParams) { 
         // Validate input parameters 
         if (id == null || requestParams == null) { 
             throw new IllegalArgumentException("Product ID and request parameters must not be null"); 
         } 
  
         // Validate business rules 
         if (requestParams.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new IllegalArgumentException("Product price must be a positive value"); 
         } 
  
         // Check if product name is unique 
         if (!domainService.isProductNameUnique(requestParams.getName(), id)) { 
             throw new IllegalArgumentException("Product name must be unique within the catalogue"); 
         } 
  
         // Delegate to domain service 
         return domainService.updateProduct(id, requestParams); 
     } 
 } 
 