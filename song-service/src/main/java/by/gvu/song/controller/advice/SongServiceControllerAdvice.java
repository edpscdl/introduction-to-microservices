package by.gvu.song.controller.advice;

import by.gvu.song.exception.SongServiceBaseException;
import by.gvu.song.exception.SongServiceDuplicateSourceException;
import by.gvu.song.exception.SongServiceMetadataNotFoundException;
import by.gvu.song.model.dto.ErrorResponse;
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
