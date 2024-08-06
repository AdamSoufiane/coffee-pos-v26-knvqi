package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductDomainEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.domain.ProductNotFoundException;
import ai.shreds.domain.DatabaseOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Implementation of the DomainProductRepositoryPort interface.
 * Responsible for interacting with the PostgreSQL database to perform CRUD operations on product data.
 */
@Repository
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    /**
     * Retrieves a product from the database using its unique identifier (UUID).
     * @param id the unique identifier of the product
     * @return the product entity as DomainProductDomainEntity
     */
    @Override
    public DomainProductDomainEntity findById(UUID id) {
        Optional<ProductEntity> productEntityOptional = productJpaRepository.findById(id);
        if (productEntityOptional.isPresent()) {
            ProductEntity productEntity = productEntityOptional.get();
            return new DomainProductDomainEntity(
                    productEntity.getId(),
                    productEntity.getName(),
                    productEntity.getDescription(),
                    productEntity.getPrice(),
                    productEntity.isAvailability(),
                    productEntity.getCreatedAt(),
                    productEntity.getUpdatedAt()
            );
        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
    }

    /**
     * Saves or updates a product entity in the database.
     * @param entity the product entity to be saved or updated
     */
    @Transactional
    @Override
    public void save(DomainProductDomainEntity entity) {
        try {
            ProductEntity productEntity = new ProductEntity(
                    entity.getId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getPrice(),
                    entity.getAvailability(),
                    entity.getCreated_at(),
                    entity.getUpdated_at()
            );
            productJpaRepository.save(productEntity);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error saving product", e);
        }
    }

    /**
     * Deletes a product from the database using its unique identifier (UUID).
     * @param id the unique identifier of the product
     */
    @Transactional
    @Override
    public void deleteById(UUID id) {
        try {
            productJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error deleting product", e);
        }
    }
}

/**
 * Spring Data JPA repository for ProductEntity.
 */
interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
class ProductEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private boolean availability;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}

/**
 * Custom exception for product not found scenarios.
 */
class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

/**
 * Custom exception for database operation errors.
 */
class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}