package com.jaberrantisi.mystery_box_service.service;

import com.jaberrantisi.mystery_box_service.model.Badge;
import com.jaberrantisi.mystery_box_service.model.MysteryBox;
import com.jaberrantisi.mystery_box_service.model.UserBadge;
import com.jaberrantisi.mystery_box_service.model.UserVirtualCurrency;
import com.jaberrantisi.mystery_box_service.repo.BadgeRepo;
import com.jaberrantisi.mystery_box_service.repo.UserBadgeRepo;
import com.jaberrantisi.mystery_box_service.repo.UserVirtualCurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserVirtualCurrencyService {
    private final UserVirtualCurrencyRepo userVCRepo;
    private final UserBadgeService userBadgeService;

    @Autowired
    public UserVirtualCurrencyService(UserVirtualCurrencyRepo userVCRepo, UserBadgeService userBadgeService) {
        this.userVCRepo = userVCRepo;
        this.userBadgeService = userBadgeService;
    }

    public void initializeVirtualCurrency(String userSub) {
        UserVirtualCurrency userVirtualCurrency =
                UserVirtualCurrency.builder()
                        .userSub(userSub)
                        .balance(0)
                        .build();
        userVCRepo.save(userVirtualCurrency);


    }
    public void sellBadgePayment(UUID myBadgeId, String userSub) {
        UserVirtualCurrency userVC = userVCRepo.getUserVirtualCurrencyByUserSub(userSub);
        int currentBalance = userVC.getBalance();
        UserBadge myBadge = userBadgeService.getUserBadge(userSub, myBadgeId);

        int increaseAmount = myBadge
                .getBadgeType()
                .getRarity()
                .getResellValue();
        userVC.setBalance(currentBalance + increaseAmount);
        userVCRepo.save(userVC);
    }
    public void makePurchase(int price, String userSub) {
        UserVirtualCurrency userVC = userVCRepo.getUserVirtualCurrencyByUserSub(userSub);
        int currentBalance = userVC.getBalance();
        userVC.setBalance(currentBalance - price);
        userVCRepo.save(userVC);
    }
}
