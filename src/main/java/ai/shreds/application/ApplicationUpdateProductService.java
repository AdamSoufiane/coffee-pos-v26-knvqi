package ai.shreds.application;

import ai.shreds.domain.port.DomainProductServicePort;
import ai.shreds.shared.dto.SharedProductResponseDTO;
import ai.shreds.shared.dto.SharedUpdateProductRequestParams;
import java.util.UUID;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationUpdateProductService implements ApplicationUpdateProductInputPort {

    private final DomainProductServicePort domainService;

    /**
     * Updates an existing product in the catalogue.
     *
     * @param id the UUID of the product to be updated
     * @param requestParams the updated product details
     * @return the updated product details encapsulated in a SharedProductResponseDTO object
     * @throws IllegalArgumentException if the input parameters are null, the price is not positive or the product name is not unique
     */
    @Override
    public SharedProductResponseDTO updateProduct(UUID id, SharedUpdateProductRequestParams requestParams) {
        // Validate input parameters
        if (id == null || requestParams == null || requestParams.getName() == null || requestParams.getDescription() == null || requestParams.getAvailability() == null) {
            throw new IllegalArgumentException("Product ID, request parameters and all request fields must not be null");
        }

        // Validate business rules
        if (requestParams.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }

        // Check if product name is unique
        if (!domainService.isProductNameUnique(requestParams.getName(), id)) {
            throw new IllegalArgumentException("Product name must be unique within the catalogue");
        }

        // Delegate to domain service
        return domainService.updateProduct(id, requestParams);
    }
}