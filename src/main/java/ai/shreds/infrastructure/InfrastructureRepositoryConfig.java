package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
  
 /** 
  * Configuration class for setting up repository beans. 
  */ 
 @Configuration 
 public class InfrastructureRepositoryConfig { 
  
     /** 
      * Bean definition for product repository. 
      * 
      * @param productRepositoryImpl the implementation of the product repository 
      * @return the product repository port 
      */ 
     @Bean 
     public DomainProductRepositoryPort productRepository(InfrastructureProductRepositoryImpl productRepositoryImpl) { 
         return productRepositoryImpl; 
     } 
  
     /** 
      * Bean definition for category repository. 
      * 
      * @param categoryRepositoryImpl the implementation of the category repository 
      * @return the category repository port 
      */ 
     @Bean 
     public DomainCategoryRepositoryPort categoryRepository(InfrastructureCategoryRepositoryImpl categoryRepositoryImpl) { 
         return categoryRepositoryImpl; 
     } 
 } 
 