import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public final class ApplicationCreateProductRequest {
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Size(min = 1, max = 500)
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Boolean availability;

    @NotNull
    private UUID category_id;

    public ApplicationCreateProductRequest(String name, String description, BigDecimal price, Boolean availability, UUID category_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.category_id = category_id;
    }
}