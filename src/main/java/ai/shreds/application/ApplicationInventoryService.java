package ai.shreds.application;

import ai.shreds.domain.DomainInventoryRepositoryPort;
import ai.shreds.shared.SharedInventoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationInventoryService implements ApplicationStoreInventoryInputPort, ApplicationRetrieveInventoryOutputPort {

    private final DomainInventoryRepositoryPort domainInventoryRepositoryPort;

    @Override
    public void storeInventoryData(SharedInventoryDTO inventory) {
        try {
            // Validate the inventory data
            validateInventoryItem(inventory);
            // Save the inventory data to the repository
            domainInventoryRepositoryPort.save(inventory);
            log.info("Inventory data stored successfully for ID: {}", inventory.getId());
        } catch (Exception e) {
            log.error("Failed to store inventory data for ID: {}", inventory.getId(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public SharedInventoryDTO retrieveInventoryData(String id) {
        try {
            // Validate the ID
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("Invalid ID");
            }
            // Retrieve the inventory data from the repository
            SharedInventoryDTO inventory = domainInventoryRepositoryPort.findById(id);
            log.info("Inventory data retrieved successfully for ID: {}", id);
            return inventory;
        } catch (Exception e) {
            log.error("Failed to retrieve inventory data for ID: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    private void validateInventoryItem(SharedInventoryDTO inventory) {
        if (inventory.getId() == null || inventory.getId().isEmpty()) {
            throw new IllegalArgumentException("Inventory item must have a unique identifier");
        }
        if (inventory.getName() == null || inventory.getName().isEmpty()) {
            throw new IllegalArgumentException("The name of the inventory item must not be empty");
        }
        if (inventory.getQuantity() < 0) {
            throw new IllegalArgumentException("The quantity of the inventory item must be a non-negative integer");
        }
        if (inventory.getThreshold() < 0) {
            throw new IllegalArgumentException("The threshold must be a non-negative integer");
        }
    }
}