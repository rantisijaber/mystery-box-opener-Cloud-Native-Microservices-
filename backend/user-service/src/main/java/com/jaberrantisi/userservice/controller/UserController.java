package com.jaberrantisi.userservice.controller;

import com.jaberrantisi.userservice.dto.UserDTO;
import com.jaberrantisi.userservice.dto.UserSettingsDTO;
import com.jaberrantisi.userservice.exception.UserNotFoundException;
import com.jaberrantisi.userservice.exception.UsernameAlreadyExistsException;
import com.jaberrantisi.userservice.model.User;
import com.jaberrantisi.userservice.model.UserSettings;
import com.jaberrantisi.userservice.repo.UserSettingsRepo;
import com.jaberrantisi.userservice.service.UserService;
import com.jaberrantisi.userservice.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserSettingsService userSettingsService;

    @Autowired
    public UserController(UserService userService, UserSettingsService userSettingsService) {
        this.userService = userService;
        this.userSettingsService = userSettingsService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@AuthenticationPrincipal Jwt jwt) {
        String sub = jwt.getSubject();
        String email = jwt.getClaim("email");
        String firstName = jwt.getClaim("given_name");
        String lastName = jwt.getClaim("family_name");
        userService.saveUser(sub, email, firstName, lastName);
        return ResponseEntity.ok("User created");
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal Jwt jwt) {
        String sub = jwt.getSubject();
        userService.deleteUser(sub);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserDTO> findUser(@RequestParam String username) {
        return ResponseEntity.ok(userService.findUser(username));
    }
    @PutMapping("/edit-user")
    public ResponseEntity<String> updateUser(@AuthenticationPrincipal Jwt jwt, @RequestBody UserDTO updatedFields) {
        String sub = jwt.getSubject();
        userService.updateUser(sub, updatedFields);
        return ResponseEntity.ok("User updated");
    }
    @GetMapping("/my-settings")
    public ResponseEntity<UserSettings> getMySettings(@AuthenticationPrincipal Jwt jwt) {
        String sub = jwt.getSubject();
        UserSettings mySettings = userSettingsService.getUserSettings(sub).orElse(null);
        if (mySettings == null) {
            throw new UserNotFoundException("User not found");
        }
        return ResponseEntity.ok(mySettings);
    }
    @PutMapping("/edit-settings")
    public ResponseEntity<String> updateSettings(@AuthenticationPrincipal Jwt jwt, @RequestBody UserSettingsDTO updatedFields) {
        String sub = jwt.getSubject();
        userSettingsService.updateSettings(sub, updatedFields);
        return ResponseEntity.ok("User updated");
    }

}
