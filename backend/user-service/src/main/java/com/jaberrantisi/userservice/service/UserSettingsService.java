package com.jaberrantisi.userservice.service;

import com.jaberrantisi.userservice.dto.UserSettingsDTO;
import com.jaberrantisi.userservice.exception.UserNotFoundException;
import com.jaberrantisi.userservice.model.UserSettings;
import com.jaberrantisi.userservice.repo.UserSettingsRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSettingsService {
    private final UserSettingsRepo userSettingsRepo;

    public UserSettingsService(UserSettingsRepo userSettingsRepo) {
        this.userSettingsRepo = userSettingsRepo;
    }
    public void createSettings(UserSettings userSettings) {
        userSettingsRepo.save(userSettings);
    }
    public Optional<UserSettings> getUserSettings(String sub) {
        return userSettingsRepo.findById(sub);
    }
    public void updateSettings(String sub, UserSettingsDTO userSettingsDto) {
        UserSettings userSettings = userSettingsRepo.findById(sub).orElse(null);
        if (userSettings == null) {
            throw new UserNotFoundException("User not found");
        }
        if (userSettingsDto.isDarkMode() != userSettings.isDarkMode()) {
            userSettingsDto.setDarkMode(userSettings.isDarkMode());
        }
        if (userSettingsDto.isEmailNotifications() != userSettings.isEmailNotifications()) {
            userSettingsDto.setEmailNotifications(userSettings.isEmailNotifications());
        }
    }


}
