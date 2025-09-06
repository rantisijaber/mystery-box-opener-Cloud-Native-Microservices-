package com.jaberrantisi.mystery_box_service.service;

import com.jaberrantisi.mystery_box_service.model.MysteryBox;
import com.jaberrantisi.mystery_box_service.repo.MysteryBoxRepo;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MysteryBoxService {
    private final MysteryBoxRepo mysteryBoxRepo;
    private final JPAStreamer jpaStreamer;

    public MysteryBoxService(MysteryBoxRepo mysteryBoxRepo, JPAStreamer jpaStreamer) {
        this.mysteryBoxRepo = mysteryBoxRepo;
        this.jpaStreamer = jpaStreamer;
    }

    public List<MysteryBox> getAllMysteryBoxes() {
        return mysteryBoxRepo.findAll();
    }

    public List<MysteryBox> getFilteredMysteryBoxes(int maxPrice) {
        return jpaStreamer.stream(MysteryBox.class)
                .filter(mysteryBox -> mysteryBox.getPrice() < maxPrice)
                .toList();
    }
    
}
