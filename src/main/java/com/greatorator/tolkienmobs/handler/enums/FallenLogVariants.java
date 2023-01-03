package com.greatorator.tolkienmobs.handler.enums;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public class FallenLogVariants {
    public enum Horizontal implements StringRepresentable {
        EMPTY,
        MOSS,
        MOSS_AND_GRASS,
        SNOW,
        WATERLOGGED;

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    public enum Climbable implements StringRepresentable {
        VINE,
        LADDER,
        LADDER_WATERLOGGED;

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    private FallenLogVariants () {
    }
}
