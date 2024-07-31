package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import ai.shreds.domain.DomainProductMapper; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.transaction.annotation.Transactional; 
 import java.util.Optional; 
 import java.util.UUID; 
  
 @Slf4j 
 @RequiredArgsConstructor 
 public class DomainProductService { 
  
     private final DomainProductRepositoryPort productRepository; 
     private final DomainCategoryRepositoryPort categoryRepository; 
     private final DomainProductMapper productMapper; 
  
     /** 
      * Saves a given product entity to the database after validation. 
      * 
      * @param product The product entity to be saved. 
      * @return The saved product entity. 
      */ 
     @Transactional 
     public DomainProductEntity saveProduct(DomainProductEntity product) { 
         validateProductData(product); 
         try { 
             DomainProductEntity savedProduct = productRepository.save(product); 
             log.info("Product saved successfully: {}", savedProduct); 
             return savedProduct; 
         } catch (Exception e) { 
             log.error("Error saving product: {}", product, e); 
             throw new RuntimeException("Failed to save product.", e); 
         } 
     } 
  
     /** 
      * Retrieves a product entity by its ID. 
      * 
      * @param id The UUID of the product. 
      * @return An Optional containing the product entity if found. 
      */ 
     @Transactional(readOnly = true) 
     public Optional<DomainProductEntity> findProductById(UUID id) { 
         try { 
             return productRepository.findById(id); 
         } catch (Exception e) { 
             log.error("Error finding product with ID: {}", id, e); 
             throw new RuntimeException("Failed to find product.", e); 
         } 
     } 
  
     /** 
      * Deletes a product entity by its ID. 
      * 
      * @param id The UUID of the product to be deleted. 
      */ 
     @Transactional 
     public void deleteProductById(UUID id) { 
         try { 
             productRepository.deleteById(id); 
             log.info("Product deleted successfully with ID: {}", id); 
         } catch (Exception e) { 
             log.error("Error deleting product with ID: {}", id, e); 
             throw new RuntimeException("Failed to delete product.", e); 
         } 
     } 
  
     /** 
      * Validates the product data before creation or update. 
      * Ensures that all required fields are present and adhere to the business rules. 
      * 
      * @param product The product entity to be validated. 
      */ 
     private void validateProductData(DomainProductEntity product) { 
         // Validate product name uniqueness 
         Optional<DomainProductEntity> existingProduct = productRepository.findById(product.getId()); 
         if (existingProduct.isPresent() && !existingProduct.get().getId().equals(product.getId())) { 
             throw new IllegalArgumentException("Product name must be unique within the catalogue."); 
         } 
  
         // Validate product price 
         if (product.getPrice().signum() <= 0) { 
             throw new IllegalArgumentException("Product price must be a positive value."); 
         } 
  
         // Validate product availability 
         if (product.getAvailability() == null) { 
             throw new IllegalArgumentException("Product availability must be either true or false."); 
         } 
  
         // Validate category ID 
         if (!categoryRepository.findById(product.getCategoryId()).isPresent()) { 
             throw new IllegalArgumentException("Category ID must exist in the database before adding or updating a product."); 
         } 
     } 
 } 
 