package ai.shreds.application;

import ai.shreds.shared.SharedInventoryDTO;

/**
 * ApplicationRetrieveInventoryOutputPort is an interface that defines the contract for retrieving inventory data.
 * This interface will be implemented by a service class that interacts with the domain layer to fetch the inventory details from the database.
 */
public interface ApplicationRetrieveInventoryOutputPort {
    /**
     * Retrieves the inventory data for the given ID.
     *
     * @param id The unique identifier for the inventory item.
     * @return The inventory data as a SharedInventoryDTO object.
     */
    SharedInventoryDTO retrieveInventoryData(String id);
}