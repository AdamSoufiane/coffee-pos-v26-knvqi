package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.domain.DomainProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataAccessException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.EntityNotFoundException;

@Repository
@RequiredArgsConstructor
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureProductRepositoryImpl.class);
    private final JpaProductRepository jpaProductRepository;
    private final DomainProductMapper productMapper;

    @Override
    public DomainProductEntity save(DomainProductEntity product) {
        try {
            logger.info("Saving product: {}", product);
            JpaProductEntity jpaProduct = productMapper.toJpaEntity(product);
            JpaProductEntity savedJpaProduct = jpaProductRepository.save(jpaProduct);
            return productMapper.toDomainEntity(savedJpaProduct);
        } catch (DataAccessException e) {
            logger.error("Error saving product", e);
            throw new RuntimeException("Error saving product", e);
        }
    }

    @Override
    public Optional<DomainProductEntity> findById(UUID id) {
        try {
            logger.info("Finding product by ID: {}", id);
            Optional<JpaProductEntity> jpaProduct = jpaProductRepository.findById(id);
            return jpaProduct.map(productMapper::toDomainEntity);
        } catch (DataAccessException e) {
            logger.error("Error finding product by ID", e);
            throw new RuntimeException("Error finding product by ID", e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            logger.info("Deleting product by ID: {}", id);
            jpaProductRepository.deleteById(id);
        } catch (DataAccessException e) {
            logger.error("Error deleting product by ID", e);
            throw new RuntimeException("Error deleting product by ID", e);
        }
    }
}

interface JpaProductRepository extends org.springframework.data.jpa.repository.JpaRepository<JpaProductEntity, UUID> {}

@Entity
@Table(name = "product")
class JpaProductEntity {
    @Id
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
    private UUID categoryId;

    // Getters and setters
}