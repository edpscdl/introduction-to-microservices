package by.gvu.song.controller.advice;

import by.gvu.song.dto.ErrorResponse;
import by.gvu.song.exception.SongServiceBaseException;
import by.gvu.song.exception.SongServiceDuplicateSourceException;
import by.gvu.song.exception.SongServiceMetadataNotFoundException;
import by.gvu.song.exception.SongServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class SongServiceControllerAdvice {
    @ExceptionHandler(SongServiceDuplicateSourceException.class)
    public ResponseEntity<ErrorResponse> handleSongServiceDuplicateSourceException(SongServiceDuplicateSourceException exception) {
        return ResponseEntity
                .status(exception.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(exception.getCode().value())
                        .errorMessage(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(SongServiceMetadataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSongServiceMetadataNotFoundException(SongServiceMetadataNotFoundException exception) {
        return ResponseEntity
                .status(exception.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(exception.getCode().value())
                        .errorMessage(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(SongServiceValidationException.class)
    public ResponseEntity<ErrorResponse> handleSongServiceValidationException(SongServiceValidationException exception) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errorDetails = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            String field = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();

            errorDetails.computeIfAbsent(field, key -> new ArrayList<>()).add(errorMessage);
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .errorMessage("Validation error")
                        .details(errorDetails)
                        .build());
    }

    @ExceptionHandler(SongServiceBaseException.class)
    public ResponseEntity<ErrorResponse> handleResourceServiceBaseException(SongServiceBaseException exception) {
        return ResponseEntity
                .status(exception.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .errorCode(exception.getCode().value())
                        .errorMessage(exception.getMessage())
                        .build());
    }
}
