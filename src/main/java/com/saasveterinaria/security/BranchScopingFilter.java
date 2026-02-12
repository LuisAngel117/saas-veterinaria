package com.saasveterinaria.security;

import com.saasveterinaria.common.ErrorCodes;
import com.saasveterinaria.common.ProblemDetailsWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class BranchScopingFilter extends OncePerRequestFilter {
  private final ScopingProperties scopingProperties;
  private final ProblemDetailsWriter problemDetailsWriter;
  private final AntPathRequestMatcher matcher = new AntPathRequestMatcher("/api/v1/me", "GET");

  public BranchScopingFilter(ScopingProperties scopingProperties, ProblemDetailsWriter problemDetailsWriter) {
    this.scopingProperties = scopingProperties;
    this.problemDetailsWriter = problemDetailsWriter;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (!matcher.matches(request)) {
      filterChain.doFilter(request, response);
      return;
    }

    String headerName = scopingProperties.getHeaderName();
    String headerValue = request.getHeader(headerName);
    if (headerValue == null || headerValue.isBlank()) {
      problemDetailsWriter.write(response, HttpStatus.BAD_REQUEST, ErrorCodes.BRANCH_HEADER_MISSING,
          "Missing required header: " + headerName, request.getRequestURI());
      return;
    }

    UUID headerBranchId;
    try {
      headerBranchId = UUID.fromString(headerValue);
    } catch (IllegalArgumentException ex) {
      problemDetailsWriter.write(response, HttpStatus.BAD_REQUEST, ErrorCodes.BRANCH_HEADER_INVALID,
          "Invalid branch header value", request.getRequestURI());
      return;
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !(authentication.getPrincipal() instanceof JwtPrincipal principal)) {
      filterChain.doFilter(request, response);
      return;
    }

    if (!headerBranchId.equals(principal.branchId())) {
      problemDetailsWriter.write(response, HttpStatus.FORBIDDEN, ErrorCodes.BRANCH_SCOPE_MISMATCH,
          "Branch scope mismatch", request.getRequestURI());
      return;
    }

    filterChain.doFilter(request, response);
  }
}
