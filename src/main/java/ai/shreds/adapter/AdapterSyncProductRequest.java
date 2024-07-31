package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for synchronizing a product in the catalogue.
 * This class is used as a request body for the syncProduct endpoint in the AdapterProductController.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterSyncProductRequest {
    /**
     * Unique identifier for the product.
     */
    @NotNull
    private UUID id;

    /**
     * Name of the product.
     */
    @NotNull
    private String name;

    /**
     * Detailed description of the product.
     */
    @NotNull
    private String description;

    /**
     * Price of the product.
     */
    @NotNull
    private BigDecimal price;

    /**
     * Indicates whether the product is currently available.
     */
    @NotNull
    private Boolean availability;
}