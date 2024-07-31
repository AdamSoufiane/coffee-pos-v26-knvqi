package ai.shreds.shared;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public final class SharedBooleanValueObject {
    private final Boolean value;

    public SharedBooleanValueObject(Boolean value) {
        if (value == null) {
            throw new IllegalArgumentException("Boolean value cannot be null");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedBooleanValueObject that = (SharedBooleanValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "SharedBooleanValueObject{" +
                "value=" + value +
                '}';
    }
}