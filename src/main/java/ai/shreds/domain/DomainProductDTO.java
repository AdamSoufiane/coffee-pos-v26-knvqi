import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}