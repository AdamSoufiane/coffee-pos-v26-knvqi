package ai.shreds.shared;

import java.util.UUID;
import lombok.Data;

@Data
public class AdapterRealTimeUpdateResponse {
    private String message;
    private AdapterProductDTO data;

    public AdapterRealTimeUpdateResponse() {}

    public AdapterRealTimeUpdateResponse(String message, AdapterProductDTO data) {
        this.message = message;
        this.data = data;
    }
}