package com.greatorator.tolkienmobs.entity.monster.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum MonsterVariant {
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

    private static final MonsterVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MonsterVariant::getId)).toArray(MonsterVariant[]::new);
    private final int id;

    MonsterVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MonsterVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}