package by.gvu.song.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class SongServiceDuplicateSourceException extends SongServiceBaseException {
    @Serial
    private static final long serialVersionUID = -6176702976641076718L;

    public SongServiceDuplicateSourceException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
