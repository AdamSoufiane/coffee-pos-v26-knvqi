package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import lombok.RequiredArgsConstructor; 
 import java.util.UUID; 
  
 /** 
  * Service class for handling category-related operations. 
  * This service validates if a category exists in the database. 
  * It interacts with the DomainCategoryRepositoryPort to perform the validation. 
  */ 
 @RequiredArgsConstructor 
 public class DomainCategoryService { 
     private final DomainCategoryRepositoryPort categoryRepositoryPort; 
  
     /** 
      * Validates if a category with the given ID exists in the database. 
      * 
      * @param id the UUID of the category to validate 
      * @return true if the category exists, false otherwise 
      */ 
     public boolean validateCategoryExists(UUID id) { 
         return categoryRepositoryPort.findById(id).isPresent(); 
     } 
 } 
 