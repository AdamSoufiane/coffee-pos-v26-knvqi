package ai.shreds.application;

import ai.shreds.adapter.AdapterProductSyncRequestParams;
import ai.shreds.adapter.AdapterProductSyncResponseDTO;

/**
 * Interface for synchronizing product data with the Menu Service.
 */
public interface ApplicationSyncServicePort {
    /**
     * Synchronizes product data with the Menu Service.
     *
     * @param params The product data to be synchronized.
     * @return The response from the Menu Service.
     * @throws IllegalArgumentException if the product data is invalid.
     * @throws Exception if an error occurs during synchronization.
     */
    AdapterProductSyncResponseDTO syncProductData(AdapterProductSyncRequestParams params) throws IllegalArgumentException, Exception;
}