package ai.shreds.application;

import ai.shreds.domain.DomainProductServicePort;
import ai.shreds.shared.SharedCreateProductRequestParams;
import ai.shreds.shared.SharedProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

@Service
public class ApplicationCreateProductService implements ApplicationCreateProductInputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCreateProductService.class);

    private final DomainProductServicePort domainService;

    @Autowired
    public ApplicationCreateProductService(DomainProductServicePort domainService) {
        this.domainService = domainService;
    }

    @Override
    public SharedProductResponseDTO createProduct(SharedCreateProductRequestParams requestParams) {
        // Validate input parameters
        validateRequestParams(requestParams);

        // Log the start of the createProduct process
        logger.info("Starting to create product with name: {}", requestParams.getName());

        // Delegate the creation of the product to the domain service
        SharedProductResponseDTO response = domainService.createProduct(requestParams);

        // Log the successful creation of the product
        logger.info("Successfully created product with ID: {}", response.getId());

        return response;
    }

    private void validateRequestParams(SharedCreateProductRequestParams requestParams) {
        if (requestParams.getName() == null || requestParams.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty");
        }
        if (requestParams.getPrice() == null || requestParams.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }
        if (requestParams.getAvailability() == null) {
            throw new IllegalArgumentException("Product availability must not be null");
        }
    }
}