package ai.shreds.adapter;

import ai.shreds.application.ApplicationInventoryServicePort;
import ai.shreds.shared.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class AdapterInventoryController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryController.class);

    private final ApplicationInventoryServicePort inventoryService;

    @PostMapping
    public ResponseEntity<AdapterInventoryAddResponse> addInventoryItem(@RequestBody AdapterInventoryAddRequestParams requestParams) {
        try {
            AdapterInventoryAddResponse response = inventoryService.addInventoryItem(requestParams);
            return ResponseEntity.status(201).body(response);
        } catch (InvalidInputException e) {
            logger.error("Invalid input: ", e);
            return ResponseEntity.status(400).body(new AdapterInventoryAddResponse("Invalid input", null));
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryAddResponse("Internal server error", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterInventoryUpdateResponse> updateInventoryItem(@PathVariable("id") String id, @RequestBody AdapterInventoryUpdateRequestParams requestParams) {
        try {
            AdapterInventoryUpdateResponse response = inventoryService.updateInventoryItem(id, requestParams);
            return ResponseEntity.ok(response);
        } catch (ItemNotFoundException e) {
            logger.error("Item not found: ", e);
            return ResponseEntity.status(404).body(new AdapterInventoryUpdateResponse("Item not found", null));
        } catch (InvalidInputException e) {
            logger.error("Invalid input: ", e);
            return ResponseEntity.status(400).body(new AdapterInventoryUpdateResponse("Invalid input", null));
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryUpdateResponse("Internal server error", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdapterInventoryDeleteResponse> deleteInventoryItem(@PathVariable("id") String id) {
        try {
            AdapterInventoryDeleteResponse response = inventoryService.deleteInventoryItem(id);
            return ResponseEntity.ok(response);
        } catch (ItemNotFoundException e) {
            logger.error("Item not found: ", e);
            return ResponseEntity.status(404).body(new AdapterInventoryDeleteResponse("Item not found"));
        } catch (InvalidInputException e) {
            logger.error("Invalid input: ", e);
            return ResponseEntity.status(400).body(new AdapterInventoryDeleteResponse("Invalid input"));
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryDeleteResponse("Internal server error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdapterInventoryGetResponse> getInventoryItem(@PathVariable("id") String id) {
        try {
            AdapterInventoryGetResponse response = inventoryService.getInventoryItem(id);
            return ResponseEntity.ok(response);
        } catch (ItemNotFoundException e) {
            logger.error("Item not found: ", e);
            return ResponseEntity.status(404).body(new AdapterInventoryGetResponse(null));
        } catch (InvalidInputException e) {
            logger.error("Invalid input: ", e);
            return ResponseEntity.status(400).body(new AdapterInventoryGetResponse(null));
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryGetResponse(null));
        }
    }

    @GetMapping
    public ResponseEntity<AdapterInventoryListResponse> listInventoryItems() {
        try {
            AdapterInventoryListResponse response = inventoryService.listInventoryItems();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryListResponse(null));
        }
    }
}