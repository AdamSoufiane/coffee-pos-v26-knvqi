package ai.shreds.adapter;

import ai.shreds.application.ApplicationInventoryServicePort;
import ai.shreds.adapter.AdapterInventoryAddRequestParams;
import ai.shreds.adapter.AdapterInventoryAddResponse;
import ai.shreds.adapter.AdapterInventoryUpdateRequestParams;
import ai.shreds.adapter.AdapterInventoryUpdateResponse;
import ai.shreds.adapter.AdapterInventoryDeleteRequestParams;
import ai.shreds.adapter.AdapterInventoryDeleteResponse;
import ai.shreds.adapter.AdapterInventoryGetRequestParams;
import ai.shreds.adapter.AdapterInventoryGetResponse;
import shared.AdapterInventoryListRequestParams;
import ai.shreds.adapter.AdapterInventoryListResponse;
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
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryAddResponse("Internal server error", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterInventoryUpdateResponse> updateInventoryItem(@PathVariable("id") String id, @RequestBody AdapterInventoryUpdateRequestParams requestParams) {
        try {
            AdapterInventoryUpdateResponse response = inventoryService.updateInventoryItem(UUID.fromString(id), requestParams);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryUpdateResponse("Internal server error", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdapterInventoryDeleteResponse> deleteInventoryItem(@PathVariable("id") String id) {
        try {
            AdapterInventoryDeleteResponse response = inventoryService.deleteInventoryItem(UUID.fromString(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body(new AdapterInventoryDeleteResponse("Internal server error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdapterInventoryGetResponse> getInventoryItem(@PathVariable("id") String id) {
        try {
            AdapterInventoryGetResponse response = inventoryService.getInventoryItem(UUID.fromString(id));
            return ResponseEntity.ok(response);
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