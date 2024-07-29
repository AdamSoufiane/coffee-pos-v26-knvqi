package ai.shreds.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object for Product Response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductResponseDTO {

    /**
     * Unique identifier for the product.
     */
    @JsonProperty("id")
    @NotNull
    private UUID id;

    /**
     * Name of the product.
     */
    @JsonProperty("name")
    @NotBlank
    private String name;

    /**
     * Detailed description of the product.
     */
    @JsonProperty("description")
    @NotBlank
    private String description;

    /**
     * Price of the product.
     */
    @JsonProperty("price")
    @NotNull
    @Positive
    private BigDecimal price;

    /**
     * Indicates whether the product is currently available.
     */
    @JsonProperty("availability")
    @NotNull
    private Boolean availability;
}