package ai.shreds.adapter; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductSearchService; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Service; 
 import java.util.List; 
  
 @Service 
 @RequiredArgsConstructor 
 public class AdapterProductSearchService { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductSearchService.class); 
     private final DomainProductSearchService domainProductSearchService; 
  
     public List<DomainProductEntity> searchProductsByName(String name) { 
         if (name == null || name.trim().isEmpty()) { 
             logger.error("Invalid name parameter: {}", name); 
             throw new IllegalArgumentException("Name parameter cannot be null or empty"); 
         } 
         logger.info("Searching products by name: {}", name); 
         return domainProductSearchService.searchProductsByName(name); 
     } 
  
     public List<DomainProductEntity> searchProductsByDescription(String description) { 
         if (description == null || description.trim().isEmpty()) { 
             logger.error("Invalid description parameter: {}", description); 
             throw new IllegalArgumentException("Description parameter cannot be null or empty"); 
         } 
         logger.info("Searching products by description: {}", description); 
         return domainProductSearchService.searchProductsByDescription(description); 
     } 
  
     public List<DomainProductEntity> searchProductsByCategory(String category) { 
         if (category == null || category.trim().isEmpty()) { 
             logger.error("Invalid category parameter: {}", category); 
             throw new IllegalArgumentException("Category parameter cannot be null or empty"); 
         } 
         logger.info("Searching products by category: {}", category); 
         return domainProductSearchService.searchProductsByCategory(category); 
     } 
 } 
 