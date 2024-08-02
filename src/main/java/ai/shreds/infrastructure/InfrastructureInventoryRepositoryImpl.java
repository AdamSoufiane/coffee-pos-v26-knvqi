package ai.shreds.infrastructure;

import ai.shreds.domain.DomainInventoryRepositoryPort;
import ai.shreds.shared.SharedInventoryDTO;
import ai.shreds.domain.DomainInventoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort {

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(SharedInventoryDTO inventory) {
        try {
            DomainInventoryEntity entity = new DomainInventoryEntity();
            entity.setId(inventory.getId());
            entity.setName(inventory.getName());
            entity.setQuantity(inventory.getQuantity());
            entity.setThreshold(inventory.getThreshold());
            mongoTemplate.save(entity);
            log.info("Inventory data saved successfully: {}", inventory);
        } catch (DataAccessException e) {
            log.error("Failed to save inventory data for inventory: {}", inventory, e);
            throw new InfrastructureInventoryRepositoryException("Failed to save inventory data for inventory: " + inventory, e);
        }
    }

    @Override
    public SharedInventoryDTO findById(String id) {
        try {
            Query query = new Query(Criteria.where("id").is(id));
            DomainInventoryEntity entity = mongoTemplate.findOne(query, DomainInventoryEntity.class);
            if (entity == null) {
                return null;
            }
            SharedInventoryDTO dto = new SharedInventoryDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setQuantity(entity.getQuantity());
            dto.setThreshold(entity.getThreshold());
            log.info("Inventory data retrieved successfully: {}", dto);
            return dto;
        } catch (DataAccessException e) {
            log.error("Failed to retrieve inventory data for id: {}", id, e);
            throw new InfrastructureInventoryRepositoryException("Failed to retrieve inventory data for id: " + id, e);
        }
    }
}

class InfrastructureInventoryRepositoryException extends RuntimeException {
    public InfrastructureInventoryRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}