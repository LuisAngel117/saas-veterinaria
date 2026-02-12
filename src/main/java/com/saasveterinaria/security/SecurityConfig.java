package com.saasveterinaria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final BranchScopingFilter branchScopingFilter;
  private final RestAuthenticationEntryPoint authenticationEntryPoint;
  private final RestAccessDeniedHandler accessDeniedHandler;

  public SecurityConfig(
      JwtAuthenticationFilter jwtAuthenticationFilter,
      BranchScopingFilter branchScopingFilter,
      RestAuthenticationEntryPoint authenticationEntryPoint,
      RestAccessDeniedHandler accessDeniedHandler) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.branchScopingFilter = branchScopingFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable());
    http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.exceptionHandling(ex -> ex
        .authenticationEntryPoint(authenticationEntryPoint)
        .accessDeniedHandler(accessDeniedHandler));

    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/v1/auth/**").permitAll()
        .requestMatchers("/actuator/health").permitAll()
        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/v1/me").hasAuthority("PERM_BRANCH_VIEW")
        .anyRequest().authenticated());

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterAfter(branchScopingFilter, JwtAuthenticationFilter.class);
    http.httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
