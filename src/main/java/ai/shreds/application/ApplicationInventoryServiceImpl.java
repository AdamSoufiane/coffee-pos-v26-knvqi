package ai.shreds.application;

import ai.shreds.adapter.*;
import ai.shreds.domain.*;
import ai.shreds.shared.AdapterInventoryItemDTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing inventory items.
 */
@Service
public class ApplicationInventoryServiceImpl implements ApplicationInventoryServicePort {

    @Autowired
    private final DomainInventoryService domainService;

    @Autowired
    private final DomainInventoryRepositoryPort repositoryPort;

    public ApplicationInventoryServiceImpl(DomainInventoryService domainService, DomainInventoryRepositoryPort repositoryPort) {
        this.domainService = domainService;
        this.repositoryPort = repositoryPort;
    }

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
    public AdapterInventoryUpdateResponse updateInventoryItem(ObjectId id, AdapterInventoryUpdateRequestParams params) {
        DomainInventoryItemEntity entity = repositoryPort.findById(id);
        if (entity == null) {
            throw new DomainInventoryItemNotFoundException("Item not found with id: " + id);
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
    public AdapterInventoryDeleteResponse deleteInventoryItem(ObjectId id) {
        DomainInventoryItemEntity entity = repositoryPort.findById(id);
        if (entity == null) {
            throw new DomainInventoryItemNotFoundException("Item not found with id: " + id);
        }
        repositoryPort.deleteById(id);
        return new AdapterInventoryDeleteResponse("Item deleted successfully");
    }

    /**
     * Retrieves an inventory item by its ID.
     * @param id the unique identifier of the inventory item
     * @return the response containing the inventory item details
     */
    @Override
    public AdapterInventoryGetResponse getInventoryItem(ObjectId id) {
        DomainInventoryItemEntity entity = repositoryPort.findById(id);
        if (entity == null) {
            throw new DomainInventoryItemNotFoundException("Item not found with id: " + id);
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
        return new AdapterInventoryListResponse(itemDTOs);
    }
}