package com.saasveterinaria.auth;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
  @EntityGraph(attributePaths = {"roles", "branches"})
  Optional<UserAccount> findByEmailIgnoreCase(String email);

  @EntityGraph(attributePaths = {"roles", "branches"})
  Optional<UserAccount> findWithRolesById(UUID id);
}
