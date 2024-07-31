package ai.shreds.application;

import ai.shreds.adapter.AdapterCreateProductRequest;
import ai.shreds.adapter.AdapterProductResponse;

/**
 * Interface for creating a product in the catalogue.
 */
public interface ApplicationCreateProductInputPort {
    /**
     * Creates a new product in the catalogue.
     *
     * @param request the request object containing product details
     * @return the response object containing created product details
     */
    AdapterProductResponse createProduct(AdapterCreateProductRequest request);
}