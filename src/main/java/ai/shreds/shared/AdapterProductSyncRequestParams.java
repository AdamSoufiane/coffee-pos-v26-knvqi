package ai.shreds.shared;

import java.util.UUID;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterProductSyncRequestParams {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
}