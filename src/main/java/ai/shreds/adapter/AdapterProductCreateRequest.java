package adapter;

import shared.SharedUUIDValueObject;
import shared.SharedStringValueObject;
import shared.SharedDecimalValueObject;
import shared.SharedBooleanValueObject;
import lombok.Data;

@Data
public class AdapterProductCreateRequest {
    private SharedStringValueObject name;
    private SharedStringValueObject description;
    private SharedDecimalValueObject price;
    private SharedBooleanValueObject availability;
    private SharedUUIDValueObject category_id;
}