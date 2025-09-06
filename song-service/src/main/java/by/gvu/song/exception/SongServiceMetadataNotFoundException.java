package by.gvu.song.exception;

import org.springframework.http.HttpStatus;

public class SongServiceMetadataNotFoundException extends SongServiceBaseException {
    public SongServiceMetadataNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
