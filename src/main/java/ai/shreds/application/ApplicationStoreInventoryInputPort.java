package ai.shreds.application;

import ai.shreds.shared.SharedInventoryDTO;

/**
 * ApplicationStoreInventoryInputPort is responsible for defining the contract for storing inventory data.
 * The implementation of this interface will be done by the ApplicationInventoryService class.
 * This interface will have a single method, storeInventoryData, which takes a SharedInventoryDTO object as input.
 * The method's purpose is to store the given inventory data in MongoDB.
 * The logic for this operation includes validating the input data, interacting with the domain layer to save the data,
 * and handling any potential exceptions that may occur during the process.
 */
public interface ApplicationStoreInventoryInputPort {
    /**
     * Stores the given inventory data in MongoDB.
     * Validates the input data and handles any errors that may occur during the storage process.
     *
     * @param inventory The inventory data to be stored.
     */
    void storeInventoryData(SharedInventoryDTO inventory);
}