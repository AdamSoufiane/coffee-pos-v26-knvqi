package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Represents the request parameters required for creating a product in the catalogue.
 * This class includes fields for the product's name, description, price, and availability status.
 * It uses Lombok annotations to generate getters, setters, and constructors automatically.
 * Validation annotations are used to ensure that the input data adheres to the business rules.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedCreateProductRequestParams {
    /**
     * The name of the product.
     * This field is mandatory and must not be blank.
     */
    @NotBlank(message = "Product name is mandatory")
    private String name;

    /**
     * The description of the product.
     * This field is mandatory and must not be blank.
     */
    @NotBlank(message = "Product description is mandatory")
    private String description;

    /**
     * The price of the product.
     * This field is mandatory and must be a positive value.
     */
    @NotNull(message = "Product price is mandatory")
    @Positive(message = "Product price must be a positive value")
    private BigDecimal price;

    /**
     * The availability status of the product.
     * This field is mandatory and must not be null.
     */
    @NotNull(message = "Product availability status is mandatory")
    private Boolean availability;
}