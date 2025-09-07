package by.gvu.resource.controller.advice;

import by.gvu.resource.exception.ResourceServiceBaseException;
import by.gvu.resource.exception.ResourceServiceFileNotFountException;
import by.gvu.resource.exception.ResourceServiceValidationException;
import by.gvu.resource.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceServiceControllerAdvice {

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .errorMessage("Content-Type '" + exception.getContentType() + "' is not supported")
                        .build());
    }

    @ExceptionHandler(ResourceServiceFileNotFountException.class)
    public ResponseEntity<ErrorResponse> handleResourceServiceFileNotFountException(ResourceServiceFileNotFountException exception) {
        return ResponseEntity
                .status(exception.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(exception.getCode().value())
                        .errorMessage(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ResourceServiceValidationException.class)
    public ResponseEntity<ErrorResponse> handleResourceServiceValidationException(ResourceServiceValidationException exception) {
        Map<String, List<String>> errorDetails = exception.getErrors().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> List.of(entry.getValue())
                ));

        return ResponseEntity
                .status(exception.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .errorMessage(exception.getMessage())
                        .details(errorDetails)
                        .build());
    }

    @ExceptionHandler(ResourceServiceBaseException.class)
    public ResponseEntity<ErrorResponse> handleResourceServiceBaseException(ResourceServiceBaseException exception) {
        return ResponseEntity
                .status(exception.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(exception.getCode().value())
                        .errorMessage(exception.getMessage())
                        .build());
    }
}
