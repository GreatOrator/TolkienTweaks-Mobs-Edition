package com.greatorator.tolkienmobs.entity.ambient.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum AmbientVariant {
    DEFAULT(0),
    RED(1),
    ORANGE(2),
    YELLOW(3),
    GREEN(4),
    BLUE(5),
    INDIGO(6),
    VIOLET(7),
    MURDER(8);

    private static final AmbientVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(AmbientVariant::getId)).toArray(AmbientVariant[]::new);
    private final int id;

    AmbientVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static AmbientVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}