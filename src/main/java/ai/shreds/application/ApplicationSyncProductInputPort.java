package ai.shreds.application;

import ai.shreds.adapter.AdapterSyncProductRequest;
import ai.shreds.adapter.AdapterSyncProductResponse;

/**
 * ApplicationSyncProductInputPort defines the contract for synchronizing product data.
 */
public interface ApplicationSyncProductInputPort {

    /**
     * Synchronizes product data with the Catalogue Synchronization Service.
     *
     * @param request the product synchronization request
     * @return the response of the synchronization operation
     */
    AdapterSyncProductResponse syncProduct(AdapterSyncProductRequest request);
}