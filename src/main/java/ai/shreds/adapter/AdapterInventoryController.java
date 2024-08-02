package ai.shreds.adapter;

import ai.shreds.application.ApplicationRetrieveInventoryOutputPort;
import ai.shreds.application.ApplicationStoreInventoryInputPort;
import ai.shreds.shared.AdapterInventoryRetrieveRequest;
import ai.shreds.shared.AdapterInventoryResponse;
import ai.shreds.shared.AdapterInventoryStoreRequest;
import ai.shreds.shared.SharedInventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/inventory")
public class AdapterInventoryController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryController.class);

    private final ApplicationStoreInventoryInputPort storeInventoryInputPort;
    private final ApplicationRetrieveInventoryOutputPort retrieveInventoryOutputPort;

    @Autowired
    public AdapterInventoryController(ApplicationStoreInventoryInputPort storeInventoryInputPort,
                                       ApplicationRetrieveInventoryOutputPort retrieveInventoryOutputPort) {
        this.storeInventoryInputPort = storeInventoryInputPort;
        this.retrieveInventoryOutputPort = retrieveInventoryOutputPort;
    }

    @PostMapping
    public ResponseEntity<?> storeInventoryData(@RequestBody AdapterInventoryStoreRequest request) {
        logger.info("Received request to store inventory data: {}", request);
        if (request.getId() == null || request.getName() == null || request.getQuantity() == null || request.getThreshold() == null) {
            logger.error("Invalid input data: {}", request);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Invalid input data.\", \"details\":\"One or more required fields are missing or incorrectly formatted.\"}");
        }
        SharedInventoryDTO inventoryDTO = new SharedInventoryDTO(request.getId(), request.getName(), request.getQuantity(), request.getThreshold());
        storeInventoryInputPort.storeInventoryData(inventoryDTO);
        logger.info("Successfully stored inventory data: {}", inventoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Inventory data stored successfully.\"}");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveInventoryData(@PathVariable String id) {
        logger.info("Received request to retrieve inventory data for ID: {}", id);
        SharedInventoryDTO inventoryDTO = retrieveInventoryOutputPort.retrieveInventoryData(id);
        if (inventoryDTO == null) {
            logger.error("Inventory item not found for ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Inventory item not found.\", \"details\":\"No inventory item found with the provided ID.\"}");
        }
        AdapterInventoryResponse response = new AdapterInventoryResponse(inventoryDTO.getId(), inventoryDTO.getName(), inventoryDTO.getQuantity(), inventoryDTO.getThreshold());
        logger.info("Successfully retrieved inventory data: {}", response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

@ControllerAdvice
class AdapterInventoryControllerException {
    private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryControllerException.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        logger.error("Handling IllegalArgumentException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Invalid input data.\", \"details\":\"" + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Handling Exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Failed to process request.\", \"details\":\"" + ex.getMessage() + "\"}");
    }
}