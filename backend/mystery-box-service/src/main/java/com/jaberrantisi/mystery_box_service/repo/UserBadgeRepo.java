package com.jaberrantisi.mystery_box_service.repo;

import com.jaberrantisi.mystery_box_service.model.Badge;
import com.jaberrantisi.mystery_box_service.model.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserBadgeRepo extends JpaRepository<UserBadge, UUID> {
    UserBadge getBadgeByUserSubAndId(String sub, UUID id);
    List<UserBadge> getBadgeListByUserSub(String sub);
}
