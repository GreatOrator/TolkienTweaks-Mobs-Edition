package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.handler.functions.TrinketRandomlyFunction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienLootFunctions {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = DeferredRegister.create(Registry.LOOT_FUNCTION_REGISTRY, MODID);

    public static final RegistryObject<LootItemFunctionType> TRINKET_RANDOMLY = LOOT_FUNCTIONS.register("random_trinket", () -> new LootItemFunctionType(new TrinketRandomlyFunction.Serializer()));

    public String getName() {
        return NAME + " - Loot Functions";
    }
}
