package com.greatorator.tolkienmobs.event;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.utils.BaseTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class EventTriggers {
    public static final BaseTrigger SLEEP_IN_BAG = CriteriaTriggers.register(new BaseTrigger(prefix("sleep_in_bag")));

    private static ResourceLocation prefix(String name) {
        return new ResourceLocation(TolkienMobs.MODID, name);
    }
}
