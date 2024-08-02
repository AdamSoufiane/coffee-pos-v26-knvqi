import lombok.Data;
import shared.AdapterInventoryItemDTO;

@Data
public class AdapterInventoryUpdateResponse {
    private String message;
    private AdapterInventoryItemDTO item;
}