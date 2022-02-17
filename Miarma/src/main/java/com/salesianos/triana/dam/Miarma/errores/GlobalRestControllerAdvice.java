package com.salesianos.triana.dam.Miarma.errores;

import com.salesianos.triana.dam.Miarma.errores.modelo.ApiError;
import com.salesianos.triana.dam.Miarma.errores.modelo.ApiSubError;
import com.salesianos.triana.dam.Miarma.errores.modelo.ApiValidationSubError;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({javax.persistence.EntityNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return buildApiError404(ex, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstrintViolationException(ConstraintViolationException ex, WebRequest request) {
        return buildApiErrorWithSubError(HttpStatus.BAD_REQUEST,
                "Errores varios en la validación",
                request,
                ex.getConstraintViolations()
                        .stream()
                        .map(cv -> ApiValidationSubError.builder()
                                .objeto(cv.getRootBeanClass().getSimpleName())
                                .campo(((PathImpl)cv.getPropertyPath()).getLeafNode().asString())
                                .valorRechazado(cv.getInvalidValue())
                                .mensaje(cv.getMessage())
                                .build())
                        .collect(Collectors.toList())
        );

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiErrorWithSubError(HttpStatus.BAD_REQUEST, "Errores varios en la validación",
                request,
                ex.getFieldErrors()
                        .stream().map(error -> ApiValidationSubError.builder()
                                .objeto(error.getObjectName())
                                .campo(error.getField())
                                .valorRechazado(error.getRejectedValue())
                                .mensaje(error.getDefaultMessage())
                                .build())
                        .collect(Collectors.toList())
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiErrorStatus(status, ex, request);
    }


    private ResponseEntity<Object> buildApiError400(Exception ex, WebRequest request) {
        return buildApiErrorStatus(HttpStatus.BAD_REQUEST, ex, request);
    }


    private ResponseEntity<Object> buildApiError404(Exception ex, WebRequest request) {
        return buildApiErrorStatus(HttpStatus.NOT_FOUND, ex, request);
    }

    private ResponseEntity<Object> buildApiErrorStatus(HttpStatus status, Exception ex, WebRequest request) {
        return ResponseEntity
                .status(status)
                .body(new ApiError(status, ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI()));
    }

    private ResponseEntity<Object> buildApiErrorWithSubError(HttpStatus estado, String mensaje, WebRequest request, List<ApiSubError> subErrores) {
        return ResponseEntity
                .status(estado)
                .body(new ApiError(estado, mensaje, ((ServletWebRequest) request).getRequest().getRequestURI(), subErrores));

    }
}