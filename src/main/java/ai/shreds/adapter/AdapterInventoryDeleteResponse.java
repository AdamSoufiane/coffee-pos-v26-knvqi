import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdapterInventoryDeleteResponse {
    private String message;

    public AdapterInventoryDeleteResponse(String message) {
        this.message = message;
    }
}