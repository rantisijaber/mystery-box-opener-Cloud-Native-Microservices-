package com.jaberrantisi.mystery_box_service.service;

import com.jaberrantisi.mystery_box_service.model.Badge;
import com.jaberrantisi.mystery_box_service.model.Rarity;
import com.jaberrantisi.mystery_box_service.model.UserBadge;
import com.jaberrantisi.mystery_box_service.repo.UserBadgeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserBadgeService {
    private final UserBadgeRepo userBadgeRepo;

    @Autowired
    public UserBadgeService(UserBadgeRepo userBadgeRepo) {
        this.userBadgeRepo = userBadgeRepo;
    }

    public void deleteUserBadge(UUID myBadgeID) {
        userBadgeRepo.deleteById(myBadgeID);
    }

    public UserBadge getUserBadge(String userSub, UUID userBadgeId) {
        return userBadgeRepo.getBadgeByUserSubAndId(userSub, userBadgeId);
    }
    public List<UserBadge> getUserBadges(String userSub) {
        return userBadgeRepo.getBadgeListByUserSub(userSub);
    }
    public UserBadge newUserBadge(String userSub, Badge badge) {
        Rarity rarity = badge.getRarity();
        UserBadge userBadge = UserBadge.builder()
                .userSub(userSub)
                .badgeType(badge)
                .build();
        return userBadgeRepo.save(userBadge);
    }
}
