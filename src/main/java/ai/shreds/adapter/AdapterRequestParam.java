import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterRequestParam {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
}