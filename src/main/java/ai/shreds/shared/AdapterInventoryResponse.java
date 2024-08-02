package ai.shreds.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdapterInventoryResponse {
    private String id;
    private String name;
    private Integer quantity;
    private Integer threshold;
}