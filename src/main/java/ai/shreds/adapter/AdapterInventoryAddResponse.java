package adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shared.AdapterInventoryItemDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterInventoryAddResponse {
    private String message;
    private AdapterInventoryItemDTO item;
}