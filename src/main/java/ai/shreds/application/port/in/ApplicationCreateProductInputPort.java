package ai.shreds.application.port.in;

import ai.shreds.shared.dto.SharedCreateProductRequestParams;
import ai.shreds.shared.dto.SharedProductResponseDTO;
import javax.validation.Valid;

/**
 * ApplicationCreateProductInputPort defines the contract for creating a product in the application layer.
 */
public interface ApplicationCreateProductInputPort {
    /**
     * Creates a new product in the catalogue.
     *
     * @param requestParams the product creation request parameters
     * @return the created product details
     */
    SharedProductResponseDTO createProduct(@Valid SharedCreateProductRequestParams requestParams);
}