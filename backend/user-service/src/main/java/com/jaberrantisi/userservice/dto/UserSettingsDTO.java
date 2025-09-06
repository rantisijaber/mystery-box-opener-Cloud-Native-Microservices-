package com.jaberrantisi.userservice.dto;

import com.jaberrantisi.userservice.model.UserSettings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSettingsDTO {
    private boolean darkMode;
    private boolean emailNotifications;

    public UserSettingsDTO(UserSettings userSettings) {
        this.emailNotifications = userSettings.isEmailNotifications();
        this.darkMode = userSettings.isDarkMode();
    }
}
