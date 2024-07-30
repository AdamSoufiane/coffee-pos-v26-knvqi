package ai.shreds.adapter.controller;

import ai.shreds.shared.dto.SharedCreateProductRequestParams;
import ai.shreds.shared.dto.SharedUpdateProductRequestParams;
import ai.shreds.shared.dto.SharedProductResponseDTO;
import ai.shreds.shared.dto.SharedDeleteProductResponseDTO;
import ai.shreds.application.port.in.ApplicationCreateProductInputPort;
import ai.shreds.application.port.in.ApplicationUpdateProductInputPort;
import ai.shreds.application.port.in.ApplicationDeleteProductInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/products")
public class AdapterProductController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class);

    private final ApplicationCreateProductInputPort createProductService;
    private final ApplicationUpdateProductInputPort updateProductService;
    private final ApplicationDeleteProductInputPort deleteProductService;

    @Autowired
    public AdapterProductController(ApplicationCreateProductInputPort createProductService,
                                    ApplicationUpdateProductInputPort updateProductService,
                                    ApplicationDeleteProductInputPort deleteProductService) {
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }

    @PostMapping
    public ResponseEntity<SharedProductResponseDTO> createProduct(@Valid @RequestBody SharedCreateProductRequestParams requestBody) {
        logger.info("Received request to create product: {}", requestBody);
        try {
            SharedProductResponseDTO response = createProductService.createProduct(requestBody);
            logger.info("Successfully created product: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred while creating product: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SharedProductResponseDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody SharedUpdateProductRequestParams requestBody) {
        logger.info("Received request to update product with ID {}: {}", id, requestBody);
        try {
            SharedProductResponseDTO response = updateProductService.updateProduct(id, requestBody);
            logger.info("Successfully updated product: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred while updating product: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SharedDeleteProductResponseDTO> deleteProduct(@PathVariable UUID id) {
        logger.info("Received request to delete product with ID {}", id);
        try {
            SharedDeleteProductResponseDTO response = deleteProductService.deleteProduct(id);
            logger.info("Successfully deleted product with ID {}", id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred while deleting product: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}