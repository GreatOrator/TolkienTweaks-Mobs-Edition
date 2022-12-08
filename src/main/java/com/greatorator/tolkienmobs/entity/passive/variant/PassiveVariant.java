package com.greatorator.tolkienmobs.entity.passive.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum PassiveVariant {
    DEFAULT(0),
    RED(1),
    ORANGE(2),
    YELLOW(3),
    GREEN(4);

    private static final PassiveVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(PassiveVariant::getId)).toArray(PassiveVariant[]::new);
    private final int id;

    PassiveVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PassiveVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}