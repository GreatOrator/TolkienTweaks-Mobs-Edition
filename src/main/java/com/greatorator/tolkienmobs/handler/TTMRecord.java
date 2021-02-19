package com.greatorator.tolkienmobs.handler;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class TTMRecord extends MusicDiscItem {
    public TTMRecord(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder) {
        super(comparatorValue, soundSupplier, builder);
    }
}
