package ai.shreds.adapter;

import ai.shreds.shared.AdapterRequestParam;
import ai.shreds.shared.AdapterResponseDTO;
import ai.shreds.application.ApplicationSyncServicePort;
import ai.shreds.shared.ProductNotFoundException;
import ai.shreds.shared.InvalidProductDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
@Slf4j
public class AdapterSyncController {

    private final ApplicationSyncServicePort applicationSyncServicePort;
    private final AdapterMapper adapterMapper;

    @Autowired
    public AdapterSyncController(ApplicationSyncServicePort applicationSyncServicePort, AdapterMapper adapterMapper) {
        this.applicationSyncServicePort = applicationSyncServicePort;
        this.adapterMapper = adapterMapper;
    }

    @PostMapping
    public ResponseEntity<AdapterResponseDTO> syncProductData(@RequestBody AdapterRequestParam params) {
        try {
            var domainEntity = adapterMapper.toDomainEntity(params);
            var response = applicationSyncServicePort.syncProductData(params);
            var adapterResponse = adapterMapper.toAdapterResponseDTO(response);
            return ResponseEntity.ok(adapterResponse);
        } catch (IllegalArgumentException e) {
            log.error("Invalid product data: {}", e.getMessage());
            return handleBadRequestException(e);
        } catch (ProductNotFoundException e) {
            log.error("Product not found: {}", e.getMessage());
            return handleNotFoundException(e);
        } catch (InvalidProductDataException e) {
            log.error("Invalid product data: {}", e.getMessage());
            return handleBadRequestException(e);
        } catch (Exception e) {
            log.error("Internal server error: {}", e.getMessage());
            return handleException(e);
        }
    }

    private ResponseEntity<AdapterResponseDTO> handleBadRequestException(Exception e) {
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("Synchronization failed");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<AdapterResponseDTO> handleNotFoundException(ProductNotFoundException e) {
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("Synchronization failed");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<AdapterResponseDTO> handleException(Exception e) {
        AdapterResponseDTO response = new AdapterResponseDTO();
        response.setMessage("Synchronization failed");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}