package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for delete product response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterDeleteProductResponse {
    /**
     * Message indicating the success of the deletion operation.
     */
    private String message;
}