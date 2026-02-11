package com.saasveterinaria.common;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProblemDetailsAdvice {
  private final ProblemDetailsFactory factory;

  public ProblemDetailsAdvice(ProblemDetailsFactory factory) {
    this.factory = factory;
  }

  @ExceptionHandler(ApiException.class)
  public ProblemDetail handleApiException(ApiException ex, HttpServletRequest request) {
    return factory.build(ex.getStatus(), ex.getCode(), ex.getMessage(), request.getRequestURI(), ex.getProperties());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
    Map<String, Object> props = new HashMap<>();
    Map<String, String> errors = new HashMap<>();
    for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
      errors.put(fe.getField(), fe.getDefaultMessage());
    }
    props.put("errors", errors);
    return factory.build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Validation failed", request.getRequestURI(), props);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ProblemDetail handleMalformed(HttpMessageNotReadableException ex, HttpServletRequest request) {
    return factory.build(HttpStatus.BAD_REQUEST, "INVALID_JSON", "Malformed request body", request.getRequestURI(), null);
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleGeneric(Exception ex, HttpServletRequest request) {
    return factory.build(HttpStatus.INTERNAL_SERVER_ERROR, "UNEXPECTED_ERROR", "Unexpected error", request.getRequestURI(), null);
  }
}
