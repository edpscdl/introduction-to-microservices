package by.gvu.resource.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ResourceServiceFileNotFountException extends ResourceServiceBaseException {
    @Serial
    private static final long serialVersionUID = -873332001553022324L;

    public ResourceServiceFileNotFountException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
