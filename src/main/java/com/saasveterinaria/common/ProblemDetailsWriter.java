package com.saasveterinaria.common;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProblemDetailsWriter {
  private final ObjectMapper objectMapper;
  private final ProblemDetailsFactory factory;

  public ProblemDetailsWriter(ObjectMapper objectMapper, ProblemDetailsFactory factory) {
    this.objectMapper = objectMapper;
    this.factory = factory;
  }

  public void write(HttpServletResponse response, HttpStatus status, String code, String detail, String instance)
      throws IOException {
    ProblemDetail pd = factory.build(status, code, detail, instance, null);
    response.setStatus(status.value());
    response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
    response.getWriter().write(objectMapper.writeValueAsString(pd));
  }
}
