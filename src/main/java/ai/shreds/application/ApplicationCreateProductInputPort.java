package ai.shreds.application; 
  
 import ai.shreds.shared.ApplicationSharedProductDTO; 
  
 /** 
  * ApplicationCreateProductInputPort defines the contract for creating a product in the catalogue. 
  * This interface will be implemented by the ApplicationProductService class. 
  */ 
 public interface ApplicationCreateProductInputPort { 
     /** 
      * Creates a new product in the catalogue. 
      *  
      * @param params the product details encapsulated in ApplicationSharedProductDTO 
      * @return the created product details encapsulated in ApplicationSharedProductDTO 
      */ 
     ApplicationSharedProductDTO createProduct(ApplicationSharedProductDTO params); 
 }