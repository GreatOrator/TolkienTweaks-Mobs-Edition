package com.greatorator.tolkienmobs.event;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.utils.TTMBaseTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class TTMEventTriggers {
    public static final TTMBaseTrigger SLEEP_IN_BAG = CriteriaTriggers.register(new TTMBaseTrigger(prefix("sleep_in_bag")));

    private static ResourceLocation prefix(String name) {
        return new ResourceLocation(TolkienMobs.MODID, name);
    }
}
