package com.saasveterinaria.auth;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorChallengeRepository extends JpaRepository<TwoFactorChallenge, UUID> {}
