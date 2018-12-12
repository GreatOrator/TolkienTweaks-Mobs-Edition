package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootInit {


    // structures
    public static final ResourceLocation BARROW_CHEST = register("chests/barrow_chest");
    public static final ResourceLocation BARROW_GRAVE = register("chests/barrow_grave");

    private static ResourceLocation register(String id)
    {
        return LootTableList.register(new ResourceLocation(TolkienMobs.MODID, id));
    }
}
