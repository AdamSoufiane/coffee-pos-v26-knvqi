package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductDomainEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

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
    @Override
    public void save(DomainProductDomainEntity entity) {
        try {
            ProductEntity productEntity = new ProductEntity(
                    entity.getId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getPrice(),
                    entity.isAvailability(),
                    entity.getCreatedAt(),
                    entity.getUpdatedAt()
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
@Getter
@Setter
class ProductEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private boolean availability;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
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