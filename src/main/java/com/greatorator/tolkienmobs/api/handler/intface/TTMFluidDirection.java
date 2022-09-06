package com.greatorator.tolkienmobs.api.handler.intface;

import net.minecraftforge.fluids.FluidAttributes;

public enum TTMFluidDirection {
    UP(false),
    DOWN(false),
    RIGHT(false),
    LEFT(false),
    UP_GAS(true),
    DOWN_GAS(true),
    RIGHT_GAS(true),
    LEFT_GAS(true);

    private final boolean reversedGases;

    TTMFluidDirection(boolean reversedGases) {
        this.reversedGases = reversedGases;
    }

    private TTMFluidDirection resolve() {
        switch (this) {
            case UP:
            case DOWN:
            case RIGHT:
            case LEFT:
                return this;
            case UP_GAS:
                return UP;
            case DOWN_GAS:
                return DOWN;
            case RIGHT_GAS:
                return RIGHT;
            case LEFT_GAS:
                return LEFT;
            default:
                throw new IllegalStateException("Invalid Direction");
        }
    }

    private TTMFluidDirection reverse() {
        switch (this) {
            case UP:
            case UP_GAS:
                return DOWN;
            case DOWN:
            case DOWN_GAS:
                return UP;
            case RIGHT:
            case RIGHT_GAS:
                return LEFT;
            case LEFT:
            case LEFT_GAS:
                return RIGHT;
            default:
                return null;
        }
    }

    public TTMFluidDirection reverseIfGas(FluidAttributes attributes) {
        if (!reversedGases) {
            return this;
        }

        return attributes.isGaseous() ? this.reverse() : this.resolve();
    }
}