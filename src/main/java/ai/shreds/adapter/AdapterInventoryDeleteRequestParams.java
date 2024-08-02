package adapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AdapterInventoryDeleteRequestParams {
    @NotNull
    private ObjectId id;
}