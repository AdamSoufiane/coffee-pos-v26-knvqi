package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for transferring inventory data between different layers of the application.
 * This class includes fields for id, name, quantity, and threshold.
 * It uses Lombok annotations to generate boilerplate code like getters, setters, constructors, and toString method.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedInventoryDTO {
    private String id;
    private String name;
    private Integer quantity;
    private Integer threshold;
}