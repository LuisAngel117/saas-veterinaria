package com.saasveterinaria.security;

import com.saasveterinaria.common.ErrorCodes;
import com.saasveterinaria.common.ProblemDetailsWriter;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  private final JwtService jwtService;
  private final ProblemDetailsWriter problemDetailsWriter;

  public JwtAuthenticationFilter(JwtService jwtService, ProblemDetailsWriter problemDetailsWriter) {
    this.jwtService = jwtService;
    this.problemDetailsWriter = problemDetailsWriter;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = header.substring("Bearer ".length());
    JwtPrincipal principal;
    try {
      principal = jwtService.parseAccessToken(token);
    } catch (JwtException | IllegalArgumentException ex) {
      log.warn("Invalid access token", ex);
      problemDetailsWriter.write(response, HttpStatus.UNAUTHORIZED, ErrorCodes.AUTH_INVALID_TOKEN,
          "Invalid access token", request.getRequestURI());
      return;
    }

    List<SimpleGrantedAuthority> authorities = principal.roles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .toList();
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(principal, null, authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
