package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationCreateProductInputPort; 
 import ai.shreds.application.ApplicationUpdateProductInputPort; 
 import ai.shreds.application.ApplicationDeleteProductInputPort; 
 import ai.shreds.application.ApplicationValidateCategoryInputPort; 
 import ai.shreds.shared.AdapterProductCreateRequest; 
 import ai.shreds.shared.AdapterProductCreateResponse; 
 import ai.shreds.shared.AdapterProductUpdateRequest; 
 import ai.shreds.shared.AdapterProductUpdateResponse; 
 import ai.shreds.shared.AdapterProductDeleteResponse; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.*; 
  
 import java.util.UUID; 
 import java.math.BigDecimal; 
  
 @RestController 
 @RequestMapping("/products") 
 @RequiredArgsConstructor 
 public class AdapterProductController { 
  
     private final ApplicationCreateProductInputPort createProductService; 
     private final ApplicationUpdateProductInputPort updateProductService; 
     private final ApplicationDeleteProductInputPort deleteProductService; 
     private final ApplicationValidateCategoryInputPort validateCategoryService; 
  
     @PostMapping 
     public ResponseEntity<AdapterProductCreateResponse> createProduct(@RequestBody AdapterProductCreateRequest request) { 
         try { 
             boolean isCategoryValid = validateCategoryService.validateCategoryExists(request.getCategoryId().getValue()); 
             if (!isCategoryValid) { 
                 return ResponseEntity.badRequest().body(new AdapterProductCreateResponse(null, null, null, null, null, null)); 
             } 
             if (request.getPrice().getValue().compareTo(BigDecimal.ZERO) <= 0) { 
                 return ResponseEntity.badRequest().body(new AdapterProductCreateResponse(null, null, null, null, null, null)); 
             } 
             var productDTO = new ApplicationSharedProductDTO( 
                     request.getName().getValue(), 
                     request.getDescription().getValue(), 
                     request.getPrice().getValue(), 
                     request.getAvailability().getValue(), 
                     request.getCategoryId().getValue() 
             ); 
             var createdProduct = createProductService.createProduct(productDTO); 
             // Call Catalogue Synchronization Service here 
             var response = new AdapterProductCreateResponse( 
                     new SharedUUIDValueObject(createdProduct.getId()), 
                     new SharedStringValueObject(createdProduct.getName()), 
                     new SharedStringValueObject(createdProduct.getDescription()), 
                     new SharedDecimalValueObject(createdProduct.getPrice()), 
                     new SharedBooleanValueObject(createdProduct.getAvailability()), 
                     new SharedUUIDValueObject(createdProduct.getCategoryId()) 
             ); 
             return ResponseEntity.ok(response); 
         } catch (Exception e) { 
             return ResponseEntity.status(500).body(new AdapterProductCreateResponse(null, null, null, null, null, null)); 
         } 
     } 
  
     @PutMapping("/{id}") 
     public ResponseEntity<AdapterProductUpdateResponse> updateProduct(@PathVariable UUID id, @RequestBody AdapterProductUpdateRequest request) { 
         try { 
             boolean isCategoryValid = validateCategoryService.validateCategoryExists(request.getCategoryId().getValue()); 
             if (!isCategoryValid) { 
                 return ResponseEntity.badRequest().body(new AdapterProductUpdateResponse("Invalid category", null)); 
             } 
             if (request.getPrice().getValue().compareTo(BigDecimal.ZERO) <= 0) { 
                 return ResponseEntity.badRequest().body(new AdapterProductUpdateResponse("Invalid price", null)); 
             } 
             var productDTO = new ApplicationSharedProductDTO( 
                     request.getName().getValue(), 
                     request.getDescription().getValue(), 
                     request.getPrice().getValue(), 
                     request.getAvailability().getValue(), 
                     request.getCategoryId().getValue() 
             ); 
             var updatedProduct = updateProductService.updateProduct(id, productDTO); 
             // Call Catalogue Synchronization Service here 
             var response = new AdapterProductUpdateResponse( 
                     "Operation successful", 
                     new AdapterProductCreateResponse( 
                             new SharedUUIDValueObject(updatedProduct.getId()), 
                             new SharedStringValueObject(updatedProduct.getName()), 
                             new SharedStringValueObject(updatedProduct.getDescription()), 
                             new SharedDecimalValueObject(updatedProduct.getPrice()), 
                             new SharedBooleanValueObject(updatedProduct.getAvailability()), 
                             new SharedUUIDValueObject(updatedProduct.getCategoryId()) 
                     ) 
             ); 
             return ResponseEntity.ok(response); 
         } catch (Exception e) { 
             return ResponseEntity.status(500).body(new AdapterProductUpdateResponse("Error occurred", null)); 
         } 
     } 
  
     @DeleteMapping("/{id}") 
     public ResponseEntity<AdapterProductDeleteResponse> deleteProduct(@PathVariable UUID id) { 
         try { 
             deleteProductService.deleteProduct(id); 
             // Call Catalogue Synchronization Service here 
             var response = new AdapterProductDeleteResponse("Operation successful"); 
             return ResponseEntity.ok(response); 
         } catch (Exception e) { 
             return ResponseEntity.status(500).body(new AdapterProductDeleteResponse("Error occurred")); 
         } 
     } 
 }