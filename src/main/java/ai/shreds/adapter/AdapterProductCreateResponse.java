import shared.SharedUUIDValueObject;
import shared.SharedStringValueObject;
import shared.SharedDecimalValueObject;
import shared.SharedBooleanValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterProductCreateResponse {
    private SharedUUIDValueObject id;
    private SharedStringValueObject name;
    private SharedStringValueObject description;
    private SharedDecimalValueObject price;
    private SharedBooleanValueObject availability;
    private SharedUUIDValueObject category_id;
}