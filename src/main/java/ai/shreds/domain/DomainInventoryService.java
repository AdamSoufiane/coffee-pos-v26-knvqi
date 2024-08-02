package ai.shreds.domain;

import ai.shreds.domain.DomainInventoryItemAlreadyExistsException;
import ai.shreds.domain.DomainInventoryItemQuantityException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainInventoryService {
    private static final Logger logger = LoggerFactory.getLogger(DomainInventoryService.class);
    private final DomainInventoryRepositoryPort repository;

    /**
     * Validate the inventory item details before performing any operation.
     * Ensure the name is unique, quantity is non-negative, and threshold is non-negative.
     * If validation fails, throw appropriate exceptions.
     *
     * @param item the inventory item to validate
     * @return true if the item is valid
     * @throws DomainInventoryItemAlreadyExistsException if an item with the same name already exists
     * @throws DomainInventoryItemQuantityException if quantity or threshold is negative
     */
    public boolean validateInventoryItem(DomainInventoryItemEntity item) throws DomainInventoryItemAlreadyExistsException, DomainInventoryItemQuantityException {
        validateNameUniqueness(item);
        validateNonNegativeValues(item);
        return true;
    }

    /**
     * Validate that the inventory item name is unique.
     *
     * @param item the inventory item to validate
     * @throws DomainInventoryItemAlreadyExistsException if an item with the same name already exists
     */
    private void validateNameUniqueness(DomainInventoryItemEntity item) throws DomainInventoryItemAlreadyExistsException {
        List<DomainInventoryItemEntity> allItems = repository.findAll();
        Optional<DomainInventoryItemEntity> existingItem = allItems.stream()
                .filter(i -> i.getName().equals(item.getName()) && (item.getId() == null || !i.getId().equals(item.getId())))
                .findFirst();
        if (existingItem.isPresent()) {
            logger.error("Inventory item with name {} already exists", item.getName());
            throw new DomainInventoryItemAlreadyExistsException(item.getName());
        }
    }

    /**
     * Validate that the inventory item quantity and threshold are non-negative.
     *
     * @param item the inventory item to validate
     * @throws DomainInventoryItemQuantityException if quantity or threshold is negative
     */
    private void validateNonNegativeValues(DomainInventoryItemEntity item) throws DomainInventoryItemQuantityException {
        if (item.getQuantity() < 0 || item.getThreshold() < 0) {
            logger.error("Inventory item quantity or threshold is negative for item: {}", item);
            throw new DomainInventoryItemQuantityException(item.getQuantity(), item.getThreshold());
        }
    }
}