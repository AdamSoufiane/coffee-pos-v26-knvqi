package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SharedException extends RuntimeException {
    public SharedException(String message) {
        super(message);
    }

    public SharedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SharedException(Throwable cause) {
        super(cause);
    }
}