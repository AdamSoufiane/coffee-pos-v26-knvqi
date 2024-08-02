package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterInventoryResponse {
    private String id;
    private String name;
    private Integer quantity;
    private Integer threshold;
}