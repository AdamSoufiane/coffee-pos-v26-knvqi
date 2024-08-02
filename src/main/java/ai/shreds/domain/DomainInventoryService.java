package ai.shreds.domain;

import ai.shreds.shared.SharedInventoryDTO;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

/**
 * Service class for managing inventory data.
 */
@Service
@RequiredArgsConstructor
public class DomainInventoryService implements DomainInventoryRepositoryPort {

    private final DomainInventoryRepositoryPort inventoryRepository;

    /**
     * Saves the given inventory item to the repository.
     *
     * @param inventory the inventory item to save
     */
    @Override
    public void save(SharedInventoryDTO inventory) {
        // Validate the inventory item
        validateInventoryItem(inventory);
        // Save the inventory item to the repository
        inventoryRepository.save(inventory);
    }

    /**
     * Retrieves an inventory item by its unique identifier.
     *
     * @param id the unique identifier of the inventory item
     * @return the inventory item
     * @throws InventoryNotFoundException if the inventory item is not found
     */
    @Override
    public Optional<SharedInventoryDTO> findById(String id) {
        // Validate the ID
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID must not be null or empty");
        }
        return inventoryRepository.findById(id);
    }

    /**
     * Validates the given inventory item.
     *
     * @param inventory the inventory item to validate
     */
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