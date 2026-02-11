package com.saasveterinaria.seed;

import com.saasveterinaria.auth.Role;
import com.saasveterinaria.auth.RoleRepository;
import com.saasveterinaria.auth.UserAccount;
import com.saasveterinaria.auth.UserAccountRepository;
import com.saasveterinaria.branch.Branch;
import com.saasveterinaria.branch.BranchRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {
  private final BranchRepository branchRepository;
  private final RoleRepository roleRepository;
  private final UserAccountRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public DataSeeder(BranchRepository branchRepository,
                    RoleRepository roleRepository,
                    UserAccountRepository userRepository,
                    PasswordEncoder passwordEncoder) {
    this.branchRepository = branchRepository;
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void run(String... args) {
    Branch branch = ensureBranch("Matriz");
    Role superadmin = ensureRole("SUPERADMIN");
    Role admin = ensureRole("ADMIN");
    Role recepcion = ensureRole("RECEPCION");
    Role vet = ensureRole("VETERINARIO");

    ensureUser("superadmin@demo.local", "Demo1234!", superadmin, branch);
    ensureUser("admin@demo.local", "Demo1234!", admin, branch);
    ensureUser("recepcion@demo.local", "Demo1234!", recepcion, branch);
    ensureUser("vet@demo.local", "Demo1234!", vet, branch);
  }

  private Branch ensureBranch(String name) {
    return branchRepository.findAll().stream()
        .filter(b -> b.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElseGet(() -> {
          Branch branch = new Branch();
          branch.setId(UUID.randomUUID());
          branch.setName(name);
          branch.setCreatedAt(Instant.now());
          return branchRepository.save(branch);
        });
  }

  private Role ensureRole(String code) {
    return roleRepository.findByCode(code)
        .orElseGet(() -> {
          Role role = new Role();
          role.setId(UUID.randomUUID());
          role.setCode(code);
          return roleRepository.save(role);
        });
  }

  private void ensureUser(String email, String rawPassword, Role role, Branch branch) {
    UserAccount user = userRepository.findByEmailIgnoreCase(email)
        .orElseGet(() -> {
          UserAccount ua = new UserAccount();
          ua.setId(UUID.randomUUID());
          ua.setEmail(email);
          ua.setPasswordHash(passwordEncoder.encode(rawPassword));
          ua.setActive(true);
          ua.setCreatedAt(Instant.now());
          return ua;
        });

    user.getRoles().add(role);
    user.getBranches().add(branch);
    userRepository.save(user);
  }
}
