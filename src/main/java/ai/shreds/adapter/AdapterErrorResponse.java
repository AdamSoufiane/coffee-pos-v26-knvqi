package shared;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdapterErrorResponse {
    @Size(min = 1, message = "The message must not be empty")
    private String message;

    @Size(min = 1, message = "The error description must not be empty")
    private String error;

    public AdapterErrorResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    @Override
    public String toString() {
        return "AdapterErrorResponse{\n" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}