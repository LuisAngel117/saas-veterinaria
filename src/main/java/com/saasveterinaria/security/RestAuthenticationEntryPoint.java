package com.saasveterinaria.security;

import com.saasveterinaria.common.ProblemDetailsWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
  private final ProblemDetailsWriter writer;

  public RestAuthenticationEntryPoint(ProblemDetailsWriter writer) {
    this.writer = writer;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException {
    writer.write(response, HttpStatus.UNAUTHORIZED, "AUTH_REQUIRED", "Authentication required", request.getRequestURI());
  }
}
