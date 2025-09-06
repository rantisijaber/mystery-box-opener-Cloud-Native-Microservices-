package com.jaberrantisi.mystery_box_service.controller;

import com.jaberrantisi.mystery_box_service.model.Badge;
import com.jaberrantisi.mystery_box_service.model.UserBadge;
import com.jaberrantisi.mystery_box_service.service.BadgeService;
import com.jaberrantisi.mystery_box_service.service.UserBadgeService;
import com.jaberrantisi.mystery_box_service.service.UserVirtualCurrencyService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/badge")
public class BadgeController {
    private final BadgeService badgeService;
    private final UserVirtualCurrencyService userVCService;
    private final UserBadgeService userBadgeService;

    public BadgeController(BadgeService badgeService, UserVirtualCurrencyService userVCService, UserBadgeService userBadgeService) {
        this.badgeService = badgeService;
        this.userVCService = userVCService;
        this.userBadgeService = userBadgeService;
    }
    @DeleteMapping("/sell-badge")
    public ResponseEntity<?> sellBadge(@AuthenticationPrincipal Jwt jwt, @RequestParam UUID myBadgeID) {
        String userSub = jwt.getSubject();
        userBadgeService.deleteUserBadge(myBadgeID);
        userVCService.sellBadgePayment(myBadgeID, userSub);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/my-badges")
    public ResponseEntity<List<UserBadge>> getMyBadges(@AuthenticationPrincipal Jwt jwt) {
        String userSub = jwt.getSubject();
        List<UserBadge> myBadges = userBadgeService.getUserBadges(userSub);
        return ResponseEntity.ok(myBadges);
    }
    @GetMapping("/all-badges")
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> allAvailableBadges = badgeService.getBadges();
        return ResponseEntity.ok(allAvailableBadges);
    }

}
