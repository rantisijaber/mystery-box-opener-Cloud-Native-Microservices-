package com.jaberrantisi.mystery_box_service.repo;

import com.jaberrantisi.mystery_box_service.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BadgeRepo extends JpaRepository<Badge, UUID> {
}
