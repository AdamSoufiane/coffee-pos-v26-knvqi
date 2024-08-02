import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterInventoryUpdateRequestParams {
    @NotNull(message = "ID cannot be null")
    private ObjectId id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Threshold cannot be null")
    private Integer threshold;
}