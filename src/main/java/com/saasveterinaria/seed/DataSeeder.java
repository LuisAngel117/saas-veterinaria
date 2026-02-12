package com.saasveterinaria.seed;

import com.saasveterinaria.auth.Role;
import com.saasveterinaria.auth.RoleRepository;
import com.saasveterinaria.auth.Permission;
import com.saasveterinaria.auth.PermissionCodes;
import com.saasveterinaria.auth.PermissionRepository;
import com.saasveterinaria.auth.UserAccount;
import com.saasveterinaria.auth.UserAccountRepository;
import com.saasveterinaria.branch.Branch;
import com.saasveterinaria.branch.BranchRepository;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {
  private final BranchRepository branchRepository;
  private final RoleRepository roleRepository;
  private final PermissionRepository permissionRepository;
  private final UserAccountRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public DataSeeder(BranchRepository branchRepository,
                    RoleRepository roleRepository,
                    PermissionRepository permissionRepository,
                    UserAccountRepository userRepository,
                    PasswordEncoder passwordEncoder) {
    this.branchRepository = branchRepository;
    this.roleRepository = roleRepository;
    this.permissionRepository = permissionRepository;
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

    Map<String, Permission> permissions = ensurePermissions();
    assignPermissions(superadmin, permissions, new java.util.HashSet<>(PermissionCodes.ALL));

    Set<String> adminCodes = new java.util.HashSet<>(PermissionCodes.ALL);
    adminCodes.remove(PermissionCodes.SETTINGS_TAX_UPDATE);
    adminCodes.remove(PermissionCodes.FEATURE_FLAG_UPDATE);
    assignPermissions(admin, permissions, adminCodes);

    Set<String> recepcionCodes = new java.util.HashSet<>(List.of(
        PermissionCodes.AUTH_LOGIN,
        PermissionCodes.AUTH_REFRESH,
        PermissionCodes.AUTH_LOGOUT,
        PermissionCodes.BRANCH_SELECT,
        PermissionCodes.BRANCH_VIEW,
        PermissionCodes.AGENDA_VIEW,
        PermissionCodes.AGENDA_CREATE,
        PermissionCodes.AGENDA_EDIT,
        PermissionCodes.AGENDA_CONFIRM,
        PermissionCodes.AGENDA_CANCEL,
        PermissionCodes.AGENDA_CHECKIN,
        PermissionCodes.CLIENT_VIEW,
        PermissionCodes.CLIENT_CREATE,
        PermissionCodes.CLIENT_EDIT,
        PermissionCodes.PET_VIEW,
        PermissionCodes.PET_CREATE,
        PermissionCodes.PET_EDIT,
        PermissionCodes.INVOICE_VIEW,
        PermissionCodes.INVOICE_CREATE,
        PermissionCodes.INVOICE_EDIT,
        PermissionCodes.PAYMENT_CREATE,
        PermissionCodes.REPORT_VIEW,
        PermissionCodes.REPORT_EXPORT
    ));
    assignPermissions(recepcion, permissions, recepcionCodes);

    Set<String> vetCodes = new java.util.HashSet<>(List.of(
        PermissionCodes.AUTH_LOGIN,
        PermissionCodes.AUTH_REFRESH,
        PermissionCodes.AUTH_LOGOUT,
        PermissionCodes.BRANCH_SELECT,
        PermissionCodes.BRANCH_VIEW,
        PermissionCodes.AGENDA_VIEW,
        PermissionCodes.AGENDA_START_SERVICE,
        PermissionCodes.AGENDA_CLOSE,
        PermissionCodes.CLIENT_VIEW,
        PermissionCodes.CLIENT_EDIT,
        PermissionCodes.PET_VIEW,
        PermissionCodes.PET_EDIT,
        PermissionCodes.ENCOUNTER_VIEW,
        PermissionCodes.ENCOUNTER_CREATE,
        PermissionCodes.ENCOUNTER_EDIT,
        PermissionCodes.ENCOUNTER_CLOSE,
        PermissionCodes.ATTACHMENT_UPLOAD,
        PermissionCodes.ATTACHMENT_VIEW,
        PermissionCodes.REPORT_VIEW,
        PermissionCodes.REPORT_EXPORT
    ));
    assignPermissions(vet, permissions, vetCodes);

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

  private Map<String, Permission> ensurePermissions() {
    Map<String, Permission> map = new HashMap<>();
    for (String code : PermissionCodes.ALL) {
      map.put(code, ensurePermission(code));
    }
    return map;
  }

  private Permission ensurePermission(String code) {
    return permissionRepository.findByCode(code)
        .orElseGet(() -> {
          Permission permission = new Permission();
          permission.setId(UUID.randomUUID());
          permission.setCode(code);
          return permissionRepository.save(permission);
        });
  }

  private void assignPermissions(Role role, Map<String, Permission> permissions, Set<String> codes) {
    for (String code : codes) {
      Permission permission = permissions.get(code);
      if (permission != null) {
        role.getPermissions().add(permission);
      }
    }
    roleRepository.save(role);
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
