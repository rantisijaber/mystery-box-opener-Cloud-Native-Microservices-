package com.jaberrantisi.mystery_box_service.service;

import com.jaberrantisi.mystery_box_service.model.Badge;
import com.jaberrantisi.mystery_box_service.repo.BadgeRepo;
import com.jaberrantisi.mystery_box_service.repo.UserBadgeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {
    private final BadgeRepo badgeRepo;
    public BadgeService(BadgeRepo badgeRepo) {
        this.badgeRepo = badgeRepo;
    }

    public List<Badge> getBadges() {
        return badgeRepo.findAll();
    }
}
