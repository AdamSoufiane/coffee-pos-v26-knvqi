package shared;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AdapterInventoryAddRequestParams {
  @NotBlank @Size(max = 100) private final String name;
  @Min(0) private final Integer quantity;
  @Min(0) private final Integer threshold;
}