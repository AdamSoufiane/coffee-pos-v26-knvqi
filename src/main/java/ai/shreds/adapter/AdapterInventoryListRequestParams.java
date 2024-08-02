package shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterInventoryListRequestParams {
    private UUID id;
    private String name;
    private Integer quantity;
    private Integer threshold;
}