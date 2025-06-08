package com.example.car_repair_shop.exception;

import com.example.car_repair_shop.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class, InvalidTokenException.class,
            NotFoundException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception e) {
        log.warn(e.getMessage());
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleUnauthorizedExceptions(Exception e) {
        log.warn(e.getMessage());
        return buildResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleConflictExceptions(Exception e) {
        log.warn(e.getMessage());
        return buildResponse(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleInvalidArgument(MethodArgumentNotValidException e) {
        var message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return buildResponse(message, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String message, HttpStatus httpStatus) {
        var errorResponse = new ErrorResponse(message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}