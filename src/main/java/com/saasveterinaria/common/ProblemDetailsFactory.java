package com.saasveterinaria.common;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProblemDetailsFactory {
  public ProblemDetail build(HttpStatus status, String code, String detail, String instance,
                             Map<String, Object> properties) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, detail);
    pd.setTitle(status.getReasonPhrase());
    pd.setInstance(java.net.URI.create(instance));
    pd.setProperty("code", code);
    if (properties != null) {
      properties.forEach(pd::setProperty);
    }
    return pd;
  }
}
