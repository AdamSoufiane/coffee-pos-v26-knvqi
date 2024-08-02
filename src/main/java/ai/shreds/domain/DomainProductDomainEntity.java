package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Represents a product in the domain layer.
 * This entity includes fields such as id, name, description, price, availability, created_at, and updated_at.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class DomainProductDomainEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 1)
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Boolean availability;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updated_at;

    /**
     * Validates the product data before synchronization or real-time update.
     *
     * @throws DomainProductValidationException if any validation rule is violated.
     */
    public void validateProductData() throws DomainProductValidationException {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainProductValidationException("Price must be a positive decimal value.");
        }
        if (name == null || name.isEmpty()) {
            throw new DomainProductValidationException("Name cannot be null or empty.");
        }
        if (description == null || description.isEmpty()) {
            throw new DomainProductValidationException("Description cannot be null or empty.");
        }
        if (availability == null) {
            throw new DomainProductValidationException("Availability cannot be null.");
        }
    }
}