import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSharedProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
    private UUID category_id;
}