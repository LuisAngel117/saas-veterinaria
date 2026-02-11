package com.saasveterinaria.common;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
  private final HttpStatus status;
  private final String code;
  private final Map<String, Object> properties = new HashMap<>();

  public ApiException(HttpStatus status, String code, String detail) {
    super(detail);
    this.status = status;
    this.code = code;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public ApiException withProperty(String key, Object value) {
    properties.put(key, value);
    return this;
  }
}
