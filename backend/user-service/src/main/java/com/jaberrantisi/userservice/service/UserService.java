package com.jaberrantisi.userservice.service;

import com.jaberrantisi.userservice.dto.UserDTO;
import com.jaberrantisi.userservice.exception.UserNotFoundException;
import com.jaberrantisi.userservice.exception.UsernameAlreadyExistsException;
import com.jaberrantisi.userservice.model.User;
import com.jaberrantisi.userservice.model.UserSettings;
import com.jaberrantisi.userservice.repo.UserRepo;
import com.jaberrantisi.userservice.repo.UserSettingsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Value("${AWS_SES_MAILER}")
    private String AWS_SES_MAILER;

    private final UserRepo userRepo;
    private final UserSettingsService userSettingsService;
    private final SESService sesService;

    public UserService(UserRepo userRepo, UserSettingsService userSettingsService, SESService sesService) {
        this.userRepo = userRepo;
        this.userSettingsService = userSettingsService;
        this.sesService = sesService;
    }
    public void saveUser(String email, String sub, String firstName, String lastName) {
        User user = User.builder()
                .cognitoSub(sub)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .username(email.split("@")[0])
                .build();
        user.setCreatedAt(Instant.from(LocalDateTime.now()));
        userRepo.save(user);

        UserSettings userSettings = new UserSettings();
        userSettings.setId(user.getCognitoSub());
        userSettingsService.createSettings(userSettings);

        sesService.sendEmail(AWS_SES_MAILER, email, "Welcome to Pandora",
                "Welcome to Pandora! We're thrilled to have you on board. " +
                "Get ready to explore and enjoy an amazing experience tailored just for you!");

    }
    public void deleteUser(String sub) {
        userRepo.deleteUserByCognitoSub(sub);
    }
    public UserDTO findUser(String username) {
        if (userRepo.findUserByUsername(username) == null) {
            throw new UserNotFoundException("User: " + username + " not found");
        }
        return new UserDTO(userRepo.findUserByUsername(username));
    }
    public void updateUser(String sub, UserDTO updatedFields) {
        User user = userRepo.findById(sub).orElseThrow();

        if (updatedFields.getUsername() != null && userRepo.findUserByUsername(updatedFields.getUsername()) == null) {
            user.setUsername(updatedFields.getUsername());
        } else if (updatedFields.getUsername() != null) {
            throw new UsernameAlreadyExistsException("User: " + updatedFields.getUsername() + " already exists");
        }

        if (updatedFields.getFirstName() != null) {
            user.setFirstName(updatedFields.getFirstName());
        }

        if (updatedFields.getLastName() != null) {
            user.setLastName(updatedFields.getLastName());
        }

        userRepo.save(user);
    }

}
