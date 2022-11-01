package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.datagen.loot.functions.PotionRandomlyFunction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.eventbus.api.IEventBus;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienLootFunctions {
    public static final LootItemFunctionType POTION_RANDOMLY = new LootItemFunctionType(new PotionRandomlyFunction.Serializer());

    public static void registerLootFunction(IEventBus event) {
        Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(MODID, "potion_function"), POTION_RANDOMLY);
    }
}
