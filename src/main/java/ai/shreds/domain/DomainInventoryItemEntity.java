package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

/**
 * Represents an item in the inventory.
 */
@Entity
@Table(name = "inventory_items", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainInventoryItemEntity {

    /**
     * Unique identifier for the inventory item.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /**
     * Name of the inventory item.
     */
    @NotBlank(message = "Name is mandatory")
    @Column(unique = true)
    private String name;

    /**
     * Current stock level of the inventory item.
     */
    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be a non-negative integer")
    private Integer quantity;

    /**
     * Minimum stock level at which alerts should be generated.
     */
    @NotNull(message = "Threshold is mandatory")
    @Min(value = 0, message = "Threshold must be a non-negative integer")
    private Integer threshold;
}