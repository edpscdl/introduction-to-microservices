package by.gvu.resource.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ResourceServiceParseMetadataException extends ResourceServiceBaseException {
    @Serial
    private static final long serialVersionUID = 8344268709540369654L;

    public ResourceServiceParseMetadataException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
