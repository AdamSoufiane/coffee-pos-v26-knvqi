package ai.shreds.domain; 
  
 import java.util.List; 
 import lombok.Data; 
  
 /** 
  * DomainProductRepositoryPort defines the contract for the product repository in the domain layer. 
  * It provides methods to search for products based on different criteria such as name, description, and category. 
  */ 
 public interface DomainProductRepositoryPort { 
  
     /** 
      * Finds products whose names contain the specified string, ignoring case. 
      * @param name the name to search for 
      * @return a list of matching products 
      */ 
     List<DomainProductEntity> findByNameContainingIgnoreCase(String name); 
  
     /** 
      * Finds products whose descriptions contain the specified string, ignoring case. 
      * @param description the description to search for 
      * @return a list of matching products 
      */ 
     List<DomainProductEntity> findByDescriptionContainingIgnoreCase(String description); 
  
     /** 
      * Finds products belonging to categories with the specified name, ignoring case. 
      * @param category the category name to search for 
      * @return a list of matching products 
      */ 
     List<DomainProductEntity> findByCategoryNameIgnoreCase(String category); 
 }