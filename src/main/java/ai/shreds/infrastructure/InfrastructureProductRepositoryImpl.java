package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public DomainProductEntity save(DomainProductEntity product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID());
            entityManager.persist(product);
            return product;
        } else {
            return entityManager.merge(product);
        }
    }

    @Override
    public Optional<DomainProductEntity> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(DomainProductEntity.class, id));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        DomainProductEntity product = entityManager.find(DomainProductEntity.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}