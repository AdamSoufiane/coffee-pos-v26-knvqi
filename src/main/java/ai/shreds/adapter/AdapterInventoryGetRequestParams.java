import org.bson.types.ObjectId;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class AdapterInventoryGetRequestParams {
    @NotNull(message = "Id cannot be null")
    private ObjectId id;
}