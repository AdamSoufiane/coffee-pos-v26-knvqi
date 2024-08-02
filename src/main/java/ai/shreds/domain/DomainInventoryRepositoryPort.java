package ai.shreds.domain;

import java.util.List;
import java.util.UUID;

public interface DomainInventoryRepositoryPort {
    void save(DomainInventoryItemEntity item);
    DomainInventoryItemEntity findById(UUID id);
    List<DomainInventoryItemEntity> findAll();
    void deleteById(UUID id);
}