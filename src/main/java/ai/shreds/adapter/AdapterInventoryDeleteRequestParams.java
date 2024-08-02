package ai.shreds.adapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AdapterInventoryDeleteRequestParams {
    @NotNull(message = "Id cannot be null")
    private UUID id;
}