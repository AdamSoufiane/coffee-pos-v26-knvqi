import lombok.Data;
import java.util.List;

@Data
public class AdapterInventoryListResponse {
    private List<AdapterInventoryItemDTO> items;

    public AdapterInventoryListResponse(List<AdapterInventoryItemDTO> items) {
        this.items = items;
    }
}