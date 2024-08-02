package shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterInventoryItemDTO {
    private ObjectId id;
    private String name;
    private Integer quantity;
    private Integer threshold;
}