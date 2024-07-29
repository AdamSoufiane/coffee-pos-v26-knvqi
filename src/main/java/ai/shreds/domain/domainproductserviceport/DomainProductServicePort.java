package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedCreateProductRequestParams; 
 import ai.shreds.shared.SharedUpdateProductRequestParams; 
 import ai.shreds.shared.SharedProductResponseDTO; 
 import ai.shreds.shared.SharedDeleteProductResponseDTO; 
 import java.util.UUID; 
  
 /** 
  * Interface for product-related operations in the domain layer. 
  */ 
 public interface DomainProductServicePort { 
     /** 
      * Creates a new product in the catalogue. 
      *  
      * @param requestParams the product creation parameters 
      * @return the created product's response DTO 
      */ 
     SharedProductResponseDTO createProduct(SharedCreateProductRequestParams requestParams); 
  
     /** 
      * Updates an existing product in the catalogue. 
      *  
      * @param id the UUID of the product to update 
      * @param requestParams the product update parameters 
      * @return the updated product's response DTO 
      */ 
     SharedProductResponseDTO updateProduct(UUID id, SharedUpdateProductRequestParams requestParams); 
  
     /** 
      * Deletes a product from the catalogue. 
      *  
      * @param id the UUID of the product to delete 
      * @return the delete product's response DTO 
      */ 
     SharedDeleteProductResponseDTO deleteProduct(UUID id); 
 } 
  
 // Note: Use Lombok annotations for getter and setter methods in related classes. 
 // Note: Ensure that the interface methods throw appropriate exceptions for error handling.