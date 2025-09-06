package com.jaberrantisi.mystery_box_service.repo;

import com.jaberrantisi.mystery_box_service.model.UserVirtualCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserVirtualCurrencyRepo extends JpaRepository<UserVirtualCurrency, UUID> {
    UserVirtualCurrency getUserVirtualCurrencyByUserSub(String userSub);
}
