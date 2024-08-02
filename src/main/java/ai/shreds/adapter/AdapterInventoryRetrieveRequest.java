package adapter;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class AdapterInventoryRetrieveRequest {
    private String id;

    public AdapterInventoryRetrieveRequest(String id) {
        this.id = id;
    }
}