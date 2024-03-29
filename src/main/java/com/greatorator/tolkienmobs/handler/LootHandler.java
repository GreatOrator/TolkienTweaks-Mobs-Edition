package com.greatorator.tolkienmobs.handler;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class LootHandler {
    private static final List<String> BLACKLIST = ImmutableList.of("dispenser");
    public static final List<String> INJECT_LIST = ImmutableList.copyOf(getInjects(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.SKELETON, EntityType.WITHER_SKELETON,
            EntityType.SKELETON_HORSE, EntityType.CREEPER, EntityType.GHAST, EntityType.BLAZE, EntityType.HUSK, EntityType.MAGMA_CUBE, EntityType.ZOMBIFIED_PIGLIN,
            EntityType.ZOGLIN, EntityType.DROWNED, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN, EntityType.SLIME, EntityType.STRAY,
            EntityType.ENDERMAN, EntityType.SPIDER, EntityType.CAVE_SPIDER));

    private LootHandler() {}

    @SubscribeEvent
    public static void lootLoad(LootTableLoadEvent evt) {
        ResourceLocation name = evt.getName();
        ResourceLocation injectName = TolkienMobs.createRL("inject/" + name.getPath());

        if (INJECT_LIST.contains(name.getPath())) {
            evt.getTable().addPool(getInjectPool(injectName));
        } else if (name.toString().startsWith("minecraft:chests/") && BLACKLIST.stream().anyMatch(name.toString()::contains)) {
            evt.getTable().addPool(getInjectPool(TolkienMobs.createRL("chests/inject")));
        }
    }

    public static LootPool getInjectPool(ResourceLocation name) {
        return LootPool.lootPool().add(LootTableReference.lootTableReference(name).setWeight(1)).setBonusRolls(UniformGenerator.between(0, 1)).name("tolkienmobs_inject").build();
    }

    private static List<String> getInjects(EntityType<?>... types) {
        return Stream.of(types).map(type -> type.getDefaultLootTable().getPath()).collect(Collectors.toList());
    }
}