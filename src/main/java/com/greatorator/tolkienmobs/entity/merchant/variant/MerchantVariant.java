package com.greatorator.tolkienmobs.entity.merchant.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum MerchantVariant {
    DEFAULT(0),
    RED(1),
    ORANGE(2),
    YELLOW(3),
    GREEN(4),
    BLUE(5),
    INDIGO(6),
    VIOLET(7),
    MAGENTA(8),
    PINK(9),
    GRAY(10),
    AQUA(11),
    BEIGE(12),
    BROWN(13),
    CORAL(14),
    CYAN(15),
    LAVENDER(16);

    private static final MerchantVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MerchantVariant::getId)).toArray(MerchantVariant[]::new);
    private final int id;

    MerchantVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MerchantVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}