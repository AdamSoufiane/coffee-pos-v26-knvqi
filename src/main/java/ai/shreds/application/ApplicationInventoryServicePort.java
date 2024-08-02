package ai.shreds.application;

import shared.AdapterInventoryAddRequestParams;
import shared.AdapterInventoryAddResponse;
import shared.AdapterInventoryDeleteResponse;
import shared.AdapterInventoryGetResponse;
import shared.AdapterInventoryListResponse;
import shared.AdapterInventoryUpdateRequestParams;
import shared.AdapterInventoryUpdateResponse;
import java.util.UUID;

/**
 * Service interface for managing inventory items.
 */
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
    AdapterInventoryUpdateResponse updateInventoryItem(UUID id, AdapterInventoryUpdateRequestParams params);

    /**
     * Deletes an inventory item.
     * @param id the unique identifier of the inventory item
     * @return the response confirming the deletion of the inventory item
     */
    AdapterInventoryDeleteResponse deleteInventoryItem(UUID id);

    /**
     * Retrieves an inventory item by its unique identifier.
     * @param id the unique identifier of the inventory item
     * @return the response containing the inventory item details
     */
    AdapterInventoryGetResponse getInventoryItem(UUID id);

    /**
     * Lists all inventory items.
     * @return the response containing the list of all inventory items
     */
    AdapterInventoryListResponse listInventoryItems();
}