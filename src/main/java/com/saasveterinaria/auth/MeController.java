package com.saasveterinaria.auth;

import com.saasveterinaria.security.JwtPrincipal;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MeController {

  @GetMapping("/me")
  public MeResponse me(Authentication authentication) {
    JwtPrincipal principal = (JwtPrincipal) authentication.getPrincipal();
    return new MeResponse(
        new MeResponse.UserInfo(principal.userId().toString(), principal.email(), principal.roles()),
        new MeResponse.BranchInfo(principal.branchId().toString())
    );
  }

  public record MeResponse(UserInfo user, BranchInfo branch) {
    public record UserInfo(String id, String email, List<String> roleCodes) {}
    public record BranchInfo(String id) {}
  }
}
