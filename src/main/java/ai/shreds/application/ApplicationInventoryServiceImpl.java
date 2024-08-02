package ai.shreds.application;

import shared.AdapterInventoryAddRequestParams;
import shared.AdapterInventoryAddResponse;
import shared.AdapterInventoryDeleteResponse;
import shared.AdapterInventoryGetResponse;
import shared.AdapterInventoryItemDTO;
import shared.AdapterInventoryListResponse;
import shared.AdapterInventoryUpdateResponse;
import ai.shreds.domain.*;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service implementation for managing inventory items.
 */
@Service
@AllArgsConstructor
public class ApplicationInventoryServiceImpl implements ApplicationInventoryServicePort {

    private final DomainInventoryService domainService;
    private final DomainInventoryRepositoryPort repositoryPort;

    /**
     * Adds a new inventory item.
     * @param params the request parameters for adding an inventory item
     * @return the response containing the added inventory item
     */
    @Override
    public AdapterInventoryAddResponse addInventoryItem(AdapterInventoryAddRequestParams params) {
        DomainInventoryItemEntity entity = new DomainInventoryItemEntity(null, params.getName(), params.getQuantity(), params.getThreshold());
        domainService.validateInventoryItem(entity);
        domainService.checkThreshold(entity);
        repositoryPort.save(entity);
        AdapterInventoryItemDTO itemDTO = new AdapterInventoryItemDTO(entity.getId(), entity.getName(), entity.getQuantity(), entity.getThreshold());
        return new AdapterInventoryAddResponse("Item created successfully", itemDTO);
    }

    /**
     * Updates an existing inventory item.
     * @param id the unique identifier of the inventory item
     * @param params the request parameters for updating an inventory item
     * @return the response containing the updated inventory item
     */
    @Override
    public AdapterInventoryUpdateResponse updateInventoryItem(UUID id, AdapterInventoryUpdateRequestParams params) {
        DomainInventoryItemEntity entity = repositoryPort.findById(id);
        if (entity == null) {
            return null;
        }
        entity.setName(params.getName());
        entity.setQuantity(params.getQuantity());
        entity.setThreshold(params.getThreshold());
        domainService.validateInventoryItem(entity);
        domainService.checkThreshold(entity);
        repositoryPort.save(entity);
        AdapterInventoryItemDTO itemDTO = new AdapterInventoryItemDTO(entity.getId(), entity.getName(), entity.getQuantity(), entity.getThreshold());
        return new AdapterInventoryUpdateResponse("Item updated successfully", itemDTO);
    }

    /**
     * Deletes an inventory item.
     * @param id the unique identifier of the inventory item
     * @return the response indicating the deletion status
     */
    @Override
    public AdapterInventoryDeleteResponse deleteInventoryItem(UUID id) {
        Optional<DomainInventoryItemEntity> entity = Optional.ofNullable(repositoryPort.findById(id));
        entity.ifPresent(repositoryPort::deleteById);
        return new AdapterInventoryDeleteResponse("Item deleted successfully");
    }

    /**
     * Retrieves an inventory item by its ID.
     * @param id the unique identifier of the inventory item
     * @return the response containing the inventory item details
     */
    @Override
    public AdapterInventoryGetResponse getInventoryItem(UUID id) {
        DomainInventoryItemEntity entity = repositoryPort.findById(id);
        if (entity == null) {
            return null;
        }
        AdapterInventoryItemDTO itemDTO = new AdapterInventoryItemDTO(entity.getId(), entity.getName(), entity.getQuantity(), entity.getThreshold());
        return new AdapterInventoryGetResponse(itemDTO);
    }

    /**
     * Lists all inventory items.
     * @return the response containing the list of inventory items
     */
    @Override
    public AdapterInventoryListResponse listInventoryItems() {
        List<DomainInventoryItemEntity> entities = repositoryPort.findAll();
        List<AdapterInventoryItemDTO> itemDTOs = entities.stream()
                .map(entity -> new AdapterInventoryItemDTO(entity.getId(), entity.getName(), entity.getQuantity(), entity.getThreshold()))
                .collect(Collectors.toList());
        return new AdapterInventoryListResponse(itemDTOs.isEmpty() ? List.of() : itemDTOs);
    }
}