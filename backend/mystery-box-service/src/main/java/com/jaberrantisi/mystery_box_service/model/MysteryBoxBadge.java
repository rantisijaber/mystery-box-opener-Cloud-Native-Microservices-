package com.jaberrantisi.mystery_box_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Data
@Entity
@Table(name = "mystery_box_badge")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MysteryBoxBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mystery_box_id")
    private MysteryBox mysteryBox;

}