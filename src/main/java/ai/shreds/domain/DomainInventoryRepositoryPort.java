package ai.shreds.domain;

import ai.shreds.shared.SharedInventoryDTO;

/**
 * Interface for the repository layer responsible for interacting with the MongoDB database
 * to perform CRUD operations on inventory data.
 */
public interface DomainInventoryRepositoryPort {
    /**
     * Saves the given InventoryItem object to MongoDB.
     * 
     * @param inventory the inventory item to be saved
     */
    void save(SharedInventoryDTO inventory);

    /**
     * Finds an InventoryItem by its unique identifier.
     * 
     * @param id the unique identifier of the inventory item
     * @return the inventory item with the given id
     */
    SharedInventoryDTO findById(String id);
}