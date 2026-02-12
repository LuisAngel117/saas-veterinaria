package com.saasveterinaria.security;

import java.util.List;
import java.util.UUID;

public record JwtPrincipal(UUID userId, String email, List<String> roles, List<String> permissions, UUID branchId) {}
