package com.greatorator.tolkienmobs.handler.interfaces;

public interface TrapsTarget {
    void setTargetTrapped(boolean trapped, boolean notifyOthers);

    boolean isTargetTrapped();
}
