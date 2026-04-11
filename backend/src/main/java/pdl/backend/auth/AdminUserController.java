package pdl.backend.auth;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

  private final UserRepository userRepository;
  private final AppSettingRepository appSettingRepository;

  public AdminUserController(
    UserRepository userRepository,
    AppSettingRepository appSettingRepository
  ) {
    this.userRepository = userRepository;
    this.appSettingRepository = appSettingRepository;
  }

  @GetMapping("/settings/auto-approve")
  public ResponseEntity<Map<String, Boolean>> getAutoApprove() {
    boolean val = appSettingRepository
      .findById("auto_approve_users")
      .map(s -> Boolean.parseBoolean(s.getSettingValue()))
      .orElse(false);
    return ResponseEntity.ok(Map.of("enabled", val));
  }

  @PutMapping("/settings/auto-approve")
  public ResponseEntity<?> setAutoApprove(@RequestParam boolean enabled) {
    AppSetting setting = appSettingRepository
      .findById("auto_approve_users")
      .orElse(new AppSetting("auto_approve_users", "false"));
    setting.setSettingValue(String.valueOf(enabled));
    appSettingRepository.save(setting);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/pending")
  public ResponseEntity<List<UserAccount>> getPendingUsers() {
    return ResponseEntity.ok(userRepository.findPendingApprovals());
  }

  @PutMapping("/{id}/approve")
  public ResponseEntity<?> approveUser(@PathVariable Long id) {
    return userRepository
      .findById(id)
      .map(user -> {
        if (user.isApproved()) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("error", "User is already approved.")
          );
        }
        user.setApproved(true);
        userRepository.save(user);
        return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> rejectUser(@PathVariable Long id) {
    return userRepository
      .findById(id)
      .map(user -> {
        if ("ROLE_ADMIN".equals(user.getRole())) {
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            Map.of("error", "Cannot delete an administrator account.")
          );
        }
        if (user.isApproved()) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of(
              "error",
              "Cannot reject an already active user. Use the ban/delete workflow instead."
            )
          );
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());
  }
}
