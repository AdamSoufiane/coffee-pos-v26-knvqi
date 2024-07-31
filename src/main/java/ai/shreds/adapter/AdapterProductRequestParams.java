import java.util.UUID;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdapterProductRequestParams {
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Size(min = 1, max = 255)
    private String description;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal price;

    @NotNull
    private Boolean availability;

    @NotNull
    private UUID category_id;
}