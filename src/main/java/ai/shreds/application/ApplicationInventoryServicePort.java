package ai.shreds.application; 
  
 import ai.shreds.adapter.*; 
 import org.bson.types.ObjectId; 
 import lombok.Value; 
  
 /** 
  * Service interface for managing inventory items. 
  */ 
 @Value 
 public interface ApplicationInventoryServicePort { 
  
     /** 
      * Adds a new inventory item. 
      * @param params the request parameters for adding an inventory item 
      * @return the response containing the added inventory item 
      */ 
     AdapterInventoryAddResponse addInventoryItem(AdapterInventoryAddRequestParams params); 
  
     /** 
      * Updates an existing inventory item. 
      * @param id the unique identifier of the inventory item 
      * @param params the request parameters for updating an inventory item 
      * @return the response containing the updated inventory item 
      */ 
     AdapterInventoryUpdateResponse updateInventoryItem(ObjectId id, AdapterInventoryUpdateRequestParams params); 
  
     /** 
      * Deletes an inventory item. 
      * @param id the unique identifier of the inventory item 
      * @return the response confirming the deletion of the inventory item 
      */ 
     AdapterInventoryDeleteResponse deleteInventoryItem(ObjectId id); 
  
     /** 
      * Retrieves an inventory item by its unique identifier. 
      * @param id the unique identifier of the inventory item 
      * @return the response containing the inventory item details 
      */ 
     AdapterInventoryGetResponse getInventoryItem(ObjectId id); 
  
     /** 
      * Lists all inventory items. 
      * @return the response containing the list of all inventory items 
      */ 
     AdapterInventoryListResponse listInventoryItems(); 
 }