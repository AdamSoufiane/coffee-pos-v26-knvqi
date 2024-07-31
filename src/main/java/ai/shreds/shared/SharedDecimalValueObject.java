package ai.shreds.shared;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
public class SharedDecimalValueObject {
    private final BigDecimal value;

    public SharedDecimalValueObject(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value must be a positive decimal.");
        }
        this.value = value;
    }
}