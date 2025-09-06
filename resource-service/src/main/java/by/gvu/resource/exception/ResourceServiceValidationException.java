package by.gvu.resource.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceServiceValidationException extends ResourceServiceBaseException {
    @Serial
    private static final long serialVersionUID = 1972473784426056306L;
    private Map<String, String> errors = new HashMap<>();

    public ResourceServiceValidationException() {
        super("Validation error", HttpStatus.BAD_REQUEST);
    }

    public ResourceServiceValidationException(Map<String, String> errors) {
        super("Validation error", HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }
}
