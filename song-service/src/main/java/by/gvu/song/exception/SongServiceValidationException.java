package by.gvu.song.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class SongServiceValidationException extends SongServiceBaseException {
    @Serial
    private static final long serialVersionUID = 7276872940926132357L;
    private Map<String, String> errors = new HashMap<>();

    public SongServiceValidationException() {
        super("Validation error", HttpStatus.BAD_REQUEST);
    }

    public SongServiceValidationException(Map<String, String> errors) {
        super("Validation error", HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }
}
