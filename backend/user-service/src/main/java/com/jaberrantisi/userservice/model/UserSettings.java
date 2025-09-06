package com.jaberrantisi.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_settings")
public class UserSettings {

    @Id
    private String id;

    @Column(name = "dark_mode")
    private boolean darkMode;

    @Column(name = "email_notifications")
    private boolean emailNotifications;


}
