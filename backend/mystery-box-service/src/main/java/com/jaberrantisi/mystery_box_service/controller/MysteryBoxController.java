package com.jaberrantisi.mystery_box_service.controller;

import com.jaberrantisi.mystery_box_service.model.MysteryBox;
import com.jaberrantisi.mystery_box_service.service.BadgeService;
import com.jaberrantisi.mystery_box_service.service.MysteryBoxService;
import com.jaberrantisi.mystery_box_service.service.UserVirtualCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mystery-box")
public class MysteryBoxController {
    private final MysteryBoxService mysteryBoxService;
    private final BadgeService badgeService;
    private final UserVirtualCurrencyService userVCService;

    @Autowired
    public MysteryBoxController(MysteryBoxService mysteryBoxService,
                                BadgeService badgeService,
                                UserVirtualCurrencyService userVCService) {
        this.mysteryBoxService = mysteryBoxService;
        this.badgeService = badgeService;
        this.userVCService = userVCService;
    }

    @PostMapping("/buy-box")
    public ResponseEntity<String> buyBox(@AuthenticationPrincipal Jwt jwt, @RequestBody MysteryBox mysteryBox) {
        int price = mysteryBox.getPrice();
        String sub = jwt.getSubject();
        userVCService.makePurchase(price, sub);

        return ResponseEntity.ok("Box purchased");
    }

    @GetMapping("/all-boxes")
    public ResponseEntity<List<MysteryBox>> getAllMysteryBoxes() {
        List<MysteryBox> allMysteryBoxes = mysteryBoxService.getAllMysteryBoxes();
        return ResponseEntity.ok(allMysteryBoxes);
    }

    @GetMapping("/filtered-boxes")
    public ResponseEntity<List<MysteryBox>> getFilteredMysteryBoxes(@RequestParam int maxPrice) {
        List<MysteryBox> filteredMysteryBoxes = mysteryBoxService.getFilteredMysteryBoxes(maxPrice);
        return ResponseEntity.ok(filteredMysteryBoxes);
    }


}
