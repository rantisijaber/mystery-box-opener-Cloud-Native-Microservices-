package com.jaberrantisi.mystery_box_service.model;

import lombok.Getter;

@Getter
public enum Rarity {
    COMMON(250, "#A0A0A0"),
    UNCOMMON(500, "#00FF00"),
    RARE(1000, "#0000FF"),
    EPIC(5000, "#800080"),
    LEGENDARY(10000, "#FFD700");

    private final int resellValue;
    private final String color;

    Rarity(int resellValue, String color) {
        this.resellValue = resellValue;
        this.color = color;
    }

}

