import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdapterProductUpdateRequest {
    private SharedStringValueObject name;
    private SharedStringValueObject description;
    private SharedDecimalValueObject price;
    private SharedBooleanValueObject availability;
    private SharedUUIDValueObject category_id;
}