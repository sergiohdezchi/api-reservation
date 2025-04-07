package com.helier.api_reservations.exceotion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.helier.api_reservations.dto.ErrorDTO;
import com.helier.api_reservations.enums.APIError;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDTO> duplicateResource(ApiException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDTO(ex.getDescription(), ex.getReasons()));
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> reasons = new ArrayList<>();
        for (FieldError error : ex.getFieldErrors()) {
            reasons.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return ResponseEntity.status(APIError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }
}
