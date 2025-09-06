package by.gvu.song.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class SongServiceBaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6323257541753419161L;
    private HttpStatus code;

    public SongServiceBaseException() {
        super();
    }

    public SongServiceBaseException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST;
    }

    public SongServiceBaseException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
