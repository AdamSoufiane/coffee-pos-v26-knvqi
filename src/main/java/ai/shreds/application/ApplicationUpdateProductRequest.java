import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = {'id'})
public final class ApplicationUpdateProductRequest {
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Boolean availability;
    @NonNull
    private UUID category_id;
}