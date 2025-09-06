package com.jaberrantisi.mystery_box_service.repo;

import com.jaberrantisi.mystery_box_service.model.MysteryBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MysteryBoxRepo extends JpaRepository<MysteryBox, UUID> {
}
