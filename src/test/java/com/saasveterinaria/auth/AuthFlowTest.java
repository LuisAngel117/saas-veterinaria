package com.saasveterinaria.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class AuthFlowTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void loginAndMeRequireBranchHeader() throws Exception {
    LoginRequest login = new LoginRequest();
    login.setEmail("recepcion@demo.local");
    login.setPassword("Demo1234!");

    String response = mockMvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(login)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").exists())
        .andExpect(jsonPath("$.branchId").exists())
        .andReturn()
        .getResponse()
        .getContentAsString();

    String accessToken = objectMapper.readTree(response).get("accessToken").asText();
    String branchId = objectMapper.readTree(response).get("branchId").asText();

    mockMvc.perform(get("/api/v1/me")
            .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("BRANCH_HEADER_MISSING"));

    mockMvc.perform(get("/api/v1/me")
            .header("Authorization", "Bearer " + accessToken)
            .header("X-Branch-Id", branchId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.user.email").value("recepcion@demo.local"));
  }

  @Test
  void refreshAndLogoutFlow() throws Exception {
    LoginRequest login = new LoginRequest();
    login.setEmail("admin@demo.local");
    login.setPassword("Demo1234!");

    String response = mockMvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(login)))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    String refreshToken = objectMapper.readTree(response).get("refreshToken").asText();

    RefreshRequest refresh = new RefreshRequest();
    refresh.setRefreshToken(refreshToken);

    String refreshResponse = mockMvc.perform(post("/api/v1/auth/refresh")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(refresh)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").exists())
        .andExpect(jsonPath("$.refreshToken").exists())
        .andReturn()
        .getResponse()
        .getContentAsString();

    String newRefreshToken = objectMapper.readTree(refreshResponse).get("refreshToken").asText();

    LogoutRequest logout = new LogoutRequest();
    logout.setRefreshToken(newRefreshToken);

    mockMvc.perform(post("/api/v1/auth/logout")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(logout)))
        .andExpect(status().isNoContent());
  }
}
