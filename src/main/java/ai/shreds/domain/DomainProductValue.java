package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductValue {
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean availability;
    private UUID categoryId;

    // Business rule: Product price must be a positive value.
    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }
        this.price = price;
    }

    // Validation: Ensure name is not null or empty
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    // Validation: Ensure description is not null or empty
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainProductValue that = (DomainProductValue) o;

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        if (!price.equals(that.price)) return false;
        if (!availability.equals(that.availability)) return false;
        return categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + availability.hashCode();
        result = 31 * result + categoryId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DomainProductValue{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", categoryId=" + categoryId +
                '}';
    }
}