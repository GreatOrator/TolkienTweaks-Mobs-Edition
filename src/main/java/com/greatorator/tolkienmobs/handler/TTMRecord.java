package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class TTMRecord extends ItemRecord {

    public TTMRecord(String record, SoundEvent sound) {
        super(TolkienMobs.MODID + ":" + record, sound);
    }
}
