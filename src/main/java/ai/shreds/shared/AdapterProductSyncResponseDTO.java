package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterProductSyncResponseDTO {
    private String message;
    private AdapterProductDTO data;
}