package ai.shreds.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) used to encapsulate the response message for the delete product operation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedDeleteProductResponseDTO {
    private String message;
}