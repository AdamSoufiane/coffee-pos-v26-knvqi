package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedInventoryDTO; 
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import lombok.RequiredArgsConstructor; 
  
 /** 
  * Service class for managing inventory data. 
  */ 
 @Service 
 @RequiredArgsConstructor 
 public class DomainInventoryService implements DomainInventoryRepositoryPort { 
  
     private final DomainInventoryRepositoryPort inventoryRepository; 
  
     /** 
      * Saves the given inventory item to the repository. 
      *  
      * @param inventory the inventory item to save 
      */ 
     @Override 
     public void save(SharedInventoryDTO inventory) { 
         // Validate the inventory item 
         validateInventoryItem(inventory); 
         // Save the inventory item to the repository 
         inventoryRepository.save(inventory); 
     } 
  
     /** 
      * Retrieves an inventory item by its unique identifier. 
      *  
      * @param id the unique identifier of the inventory item 
      * @return the inventory item 
      * @throws InventoryNotFoundException if the inventory item is not found 
      */ 
     @Override 
     public SharedInventoryDTO findById(String id) { 
         // Validate the ID 
         if (id == null || id.isEmpty()) { 
             throw new IllegalArgumentException("ID cannot be null or empty"); 
         } 
         // Retrieve the inventory item from the repository 
         SharedInventoryDTO inventoryItem = inventoryRepository.findById(id); 
         if (inventoryItem == null) { 
             throw new InventoryNotFoundException("Inventory item not found for ID: " + id); 
         } 
         return inventoryItem; 
     } 
  
     private void validateInventoryItem(SharedInventoryDTO inventory) { 
         if (inventory.getId() == null || inventory.getId().isEmpty()) { 
             throw new IllegalArgumentException("Inventory ID cannot be null or empty"); 
         } 
         if (inventory.getName() == null || inventory.getName().isEmpty()) { 
             throw new IllegalArgumentException("Inventory name cannot be null or empty"); 
         } 
         if (inventory.getQuantity() < 0) { 
             throw new IllegalArgumentException("Inventory quantity cannot be negative"); 
         } 
         if (inventory.getThreshold() < 0) { 
             throw new IllegalArgumentException("Inventory threshold cannot be negative"); 
         } 
     } 
 } 
  
 /** 
  * Custom exception class for handling inventory not found scenarios. 
  */ 
 class InventoryNotFoundException extends RuntimeException { 
     public InventoryNotFoundException(String message) { 
         super(message); 
     } 
 }