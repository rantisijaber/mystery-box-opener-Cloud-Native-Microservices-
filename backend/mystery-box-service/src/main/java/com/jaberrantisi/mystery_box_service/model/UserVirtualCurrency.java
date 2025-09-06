package com.jaberrantisi.mystery_box_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "user_virtual_currency")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVirtualCurrency {
    @Id
    @Column(name = "user_sub", nullable = false)
    private String userSub;

    @ColumnDefault("0")
    @Column(name = "balance", nullable = false)
    private Integer balance;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

}
