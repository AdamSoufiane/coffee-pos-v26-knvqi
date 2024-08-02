package shared;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterInventoryItemDTO implements Serializable {
    private UUID id;
    private String name;
    private Integer quantity;
    private Integer threshold;
}