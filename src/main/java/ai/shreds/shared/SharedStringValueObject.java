package ai.shreds.shared;

import lombok.Value;

@Value
public class SharedStringValueObject {
    String value;

    public SharedStringValueObject(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}