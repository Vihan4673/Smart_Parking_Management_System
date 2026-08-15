package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.UserHistoryDTO;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 💡 Register සහ Login එක AuthController එකට මාරු කළ නිසා මෙතැනින් අයින් කරන ලදී.

    // Get User by ID (ADMIN Only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update User Profile
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Get User Parking History (via Feign Client)
    @GetMapping("/{id}/history")
    public ResponseEntity<UserHistoryDTO> getUserHistory(@PathVariable Long id) {
        UserHistoryDTO history = userService.getUserHistory(id);
        return ResponseEntity.ok(history);
    }
}