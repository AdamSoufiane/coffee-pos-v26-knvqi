package ai.shreds.domain; 
  
 import ai.shreds.adapter.AdapterCreateProductRequest; 
 import ai.shreds.adapter.AdapterUpdateProductRequest; 
 import ai.shreds.domain.entity.DomainProductEntity; 
 import ai.shreds.domain.port.DomainProductRepositoryPort; 
 import ai.shreds.domain.service.DomainCategoryService; 
 import ai.shreds.shared.AdapterErrorResponse; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.client.RestTemplate; 
  
 import java.math.BigDecimal; 
 import java.util.Optional; 
 import java.util.UUID; 
  
 @Service 
 public class DomainProductService { 
  
     private static final Logger logger = LoggerFactory.getLogger(DomainProductService.class); 
  
     @Autowired 
     private DomainProductRepositoryPort productRepository; 
  
     @Autowired 
     private DomainCategoryService categoryService; 
  
     /** 
      * Creates a new product in the catalogue. 
      * @param request the product creation request 
      * @return the created product entity 
      */ 
     public DomainProductEntity createProduct(AdapterCreateProductRequest request) { 
         try { 
             validateCreateProductData(request); 
             if (!categoryService.validateCategoryExists(request.getCategoryId())) { 
                 throw new IllegalArgumentException("Invalid category ID"); 
             } 
             DomainProductEntity product = new DomainProductEntity( 
                     UUID.randomUUID(), 
                     request.getName(), 
                     request.getDescription(), 
                     request.getPrice(), 
                     request.getAvailability(), 
                     request.getCategoryId() 
             ); 
             return productRepository.save(product); 
         } catch (Exception e) { 
             logger.error("Error creating product", e); 
             throw e; 
         } 
     } 
  
     /** 
      * Updates an existing product in the catalogue. 
      * @param id the product ID 
      * @param request the product update request 
      * @return the updated product entity 
      */ 
     public DomainProductEntity updateProduct(UUID id, AdapterUpdateProductRequest request) { 
         try { 
             validateUpdateProductData(request); 
             if (!categoryService.validateCategoryExists(request.getCategoryId())) { 
                 throw new IllegalArgumentException("Invalid category ID"); 
             } 
             Optional<DomainProductEntity> existingProductOpt = productRepository.findById(id); 
             if (existingProductOpt.isEmpty()) { 
                 throw new IllegalArgumentException("Product not found"); 
             } 
             DomainProductEntity existingProduct = existingProductOpt.get(); 
             existingProduct.setName(request.getName()); 
             existingProduct.setDescription(request.getDescription()); 
             existingProduct.setPrice(request.getPrice()); 
             existingProduct.setAvailability(request.getAvailability()); 
             existingProduct.setCategoryId(request.getCategoryId()); 
             return productRepository.save(existingProduct); 
         } catch (Exception e) { 
             logger.error("Error updating product", e); 
             throw e; 
         } 
     } 
  
     /** 
      * Deletes a product from the catalogue. 
      * @param id the product ID 
      * @return a success message 
      */ 
     public String deleteProduct(UUID id) { 
         try { 
             Optional<DomainProductEntity> existingProductOpt = productRepository.findById(id); 
             if (existingProductOpt.isEmpty()) { 
                 throw new IllegalArgumentException("Product not found"); 
             } 
             productRepository.deleteById(id); 
             return "Product deleted successfully"; 
         } catch (Exception e) { 
             logger.error("Error deleting product", e); 
             throw e; 
         } 
     } 
  
     /** 
      * Validates the product data for creation. 
      * @param request the product creation request 
      */ 
     private void validateCreateProductData(AdapterCreateProductRequest request) { 
         if (request.getName() == null || request.getName().isEmpty()) { 
             throw new IllegalArgumentException("Product name is required"); 
         } 
         if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new IllegalArgumentException("Product price must be positive"); 
         } 
         if (request.getAvailability() == null) { 
             throw new IllegalArgumentException("Product availability is required"); 
         } 
         if (productRepository.existsByName(request.getName())) { 
             throw new IllegalArgumentException("Product name must be unique"); 
         } 
     } 
  
     /** 
      * Validates the product data for update. 
      * @param request the product update request 
      */ 
     private void validateUpdateProductData(AdapterUpdateProductRequest request) { 
         if (request.getName() == null || request.getName().isEmpty()) { 
             throw new IllegalArgumentException("Product name is required"); 
         } 
         if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new IllegalArgumentException("Product price must be positive"); 
         } 
         if (request.getAvailability() == null) { 
             throw new IllegalArgumentException("Product availability is required"); 
         } 
         Optional<DomainProductEntity> existingProductOpt = productRepository.findById(request.getId()); 
         if (existingProductOpt.isPresent() && !existingProductOpt.get().getName().equals(request.getName()) && productRepository.existsByName(request.getName())) { 
             throw new IllegalArgumentException("Product name must be unique"); 
         } 
     } 
  
     /** 
      * Synchronizes product data with the Catalogue Synchronization Service. 
      * @param product the product entity 
      */ 
     public void syncProduct(DomainProductEntity product) { 
         RestTemplate restTemplate = new RestTemplate(); 
         String url = "http://catalogue-sync-service/sync"; 
         try { 
             restTemplate.postForObject(url, product, Void.class); 
         } catch (Exception e) { 
             logger.error("Synchronization failed", e); 
             // Implement retry logic or other failure handling 
         } 
     } 
 } 
  
 @Data 
 @AllArgsConstructor 
 class DomainProductEntity { 
     private UUID id; 
     private String name; 
     private String description; 
     private BigDecimal price; 
     private Boolean availability; 
     private UUID categoryId; 
 } 
  
 /** 
  * Custom exception for validation errors. 
  */ 
 class ValidationException extends RuntimeException { 
     public ValidationException(String message) { 
         super(message); 
     } 
 }