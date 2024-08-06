package ai.shreds.shared;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class AdapterRealTimeUpdateRequest {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
}