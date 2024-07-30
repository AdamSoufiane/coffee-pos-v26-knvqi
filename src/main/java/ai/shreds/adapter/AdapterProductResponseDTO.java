package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
}