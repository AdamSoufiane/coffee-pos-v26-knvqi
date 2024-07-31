package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateProductInputPort;
import ai.shreds.application.ApplicationDeleteProductInputPort;
import ai.shreds.application.ApplicationSyncProductInputPort;
import ai.shreds.application.ApplicationUpdateProductInputPort;
import ai.shreds.shared.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.UUID;
lombok.*;

@RestController
@RequestMapping("/products")
public class AdapterProductController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class);

    private final ApplicationCreateProductInputPort createProductService;
    private final ApplicationUpdateProductInputPort updateProductService;
    private final ApplicationDeleteProductInputPort deleteProductService;
    private final ApplicationSyncProductInputPort syncProductService;

    @RequiredArgsConstructor
    public AdapterProductController(ApplicationCreateProductInputPort createProductService, 
                                   ApplicationUpdateProductInputPort updateProductService, 
                                   ApplicationDeleteProductInputPort deleteProductService, 
                                   ApplicationSyncProductInputPort syncProductService) {
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.syncProductService = syncProductService;
    }

    @PostMapping
    public ResponseEntity<AdapterProductResponse> createProduct(@Valid @RequestBody AdapterCreateProductRequest request) {
        try {
            logger.info("Creating product: {}", request);
            AdapterProductResponse response = createProductService.createProduct(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterProductResponse> updateProduct(@PathVariable @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") UUID id, @Valid @RequestBody AdapterUpdateProductRequest request) {
        try {
            logger.info("Updating product with ID: {}", id);
            AdapterProductResponse response = updateProductService.updateProduct(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdapterDeleteProductResponse> deleteProduct(@PathVariable @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") UUID id) {
        try {
            logger.info("Deleting product with ID: {}", id);
            AdapterDeleteProductResponse response = deleteProductService.deleteProduct(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PostMapping("/sync")
    public ResponseEntity<AdapterSyncProductResponse> syncProduct(@Valid @RequestBody AdapterSyncProductRequest request) {
        try {
            logger.info("Synchronizing product: {}", request);
            AdapterSyncProductResponse response = syncProductService.syncProduct(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterErrorResponse> handleException(Exception e) {
        AdapterErrorResponse errorResponse = new AdapterErrorResponse();
        errorResponse.setMessage("Error occurred");
        errorResponse.setError(e.getMessage());
        logger.error("Exception occurred: ", e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    public static class AdapterErrorResponse {
        private String message;
        private String error;
    }
}