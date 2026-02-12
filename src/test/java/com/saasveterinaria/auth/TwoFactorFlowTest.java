package com.saasveterinaria.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TwoFactorFlowTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TotpService totpService;

  @Test
  void enrollConfirmAndVerifyChallenge() throws Exception {
    LoginRequest login = new LoginRequest();
    login.setEmail("superadmin@demo.local");
    login.setPassword("Demo1234!");

    String loginResponse = mockMvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(login)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").exists())
        .andReturn()
        .getResponse()
        .getContentAsString();

    String accessToken = objectMapper.readTree(loginResponse).get("accessToken").asText();

    String enrollResponse = mockMvc.perform(post("/api/v1/auth/2fa/enroll")
            .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.secret").exists())
        .andExpect(jsonPath("$.otpauthUrl").exists())
        .andReturn()
        .getResponse()
        .getContentAsString();

    String secret = objectMapper.readTree(enrollResponse).get("secret").asText();

    TwoFactorConfirmRequest confirm = new TwoFactorConfirmRequest();
    confirm.setCode(totpService.generateCurrentCode(secret, Instant.now()));

    mockMvc.perform(post("/api/v1/auth/2fa/confirm")
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(confirm)))
        .andExpect(status().isNoContent());

    String challengeResponse = mockMvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(login)))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.code").value("AUTH_2FA_REQUIRED"))
        .andExpect(jsonPath("$.challengeId").exists())
        .andReturn()
        .getResponse()
        .getContentAsString();

    String challengeId = objectMapper.readTree(challengeResponse).get("challengeId").asText();

    TwoFactorVerifyRequest verify = new TwoFactorVerifyRequest();
    verify.setChallengeId(challengeId);
    verify.setCode(totpService.generateCurrentCode(secret, Instant.now()));

    mockMvc.perform(post("/api/v1/auth/2fa/verify")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(verify)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").exists())
        .andExpect(jsonPath("$.refreshToken").exists())
        .andExpect(jsonPath("$.tokenType").value("Bearer"));
  }
}
