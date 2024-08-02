package adapter;

import lombok.Data;

@Data
public class AdapterInventoryStoreRequest {
    private String id;
    private String name;
    private Integer quantity;
    private Integer threshold;
}