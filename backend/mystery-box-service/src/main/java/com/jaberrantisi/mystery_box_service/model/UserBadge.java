package com.jaberrantisi.mystery_box_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_badges")
public class UserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "user_sub", nullable = false)
    private String userSub;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badgeType;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "awarded_at")
    private Instant awardedAt;

}
