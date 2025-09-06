package by.gvu.resource.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceServiceBaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5082200674188063782L;
    private HttpStatus code;

    public ResourceServiceBaseException() {
        super();
    }

    public ResourceServiceBaseException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST;
    }

    public ResourceServiceBaseException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
