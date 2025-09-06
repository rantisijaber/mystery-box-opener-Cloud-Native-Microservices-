package com.jaberrantisi.userservice.repo;

import com.jaberrantisi.userservice.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingsRepo extends JpaRepository<UserSettings, String> {

}
