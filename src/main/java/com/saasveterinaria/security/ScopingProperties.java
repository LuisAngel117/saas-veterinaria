package com.saasveterinaria.security;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.scoping.branch")
@Validated
public class ScopingProperties {
  @NotBlank
  private String headerName = "X-Branch-Id";

  public String getHeaderName() {
    return headerName;
  }

  public void setHeaderName(String headerName) {
    this.headerName = headerName;
  }
}
