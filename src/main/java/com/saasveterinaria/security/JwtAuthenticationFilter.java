package com.saasveterinaria.security;

import com.saasveterinaria.common.ErrorCodes;
import com.saasveterinaria.common.ProblemDetailsWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
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
    try {
      JwtPrincipal principal = jwtService.parseAccessToken(token);
      List<SimpleGrantedAuthority> authorities = principal.roles().stream()
          .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
          .toList();
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(principal, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      problemDetailsWriter.write(response, HttpStatus.UNAUTHORIZED, ErrorCodes.AUTH_INVALID_TOKEN,
          "Invalid access token", request.getRequestURI());
    }
  }
}
