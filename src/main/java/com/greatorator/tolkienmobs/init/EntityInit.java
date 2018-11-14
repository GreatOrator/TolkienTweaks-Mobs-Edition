package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.*;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void init() {

        System.out.println("Adding all the various fauna to see!");
        // Every entity in our mod has an ID (local to this mod)
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "treeent"), EntityTreeEnt.class, "tree_ent", id++, TolkienMobs.instance, 64, 3, true, 0x414D25, 0x996600);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mirkwoodspider"), EntityMirkwoodSpider.class, "mirkwood_spider", id++, TolkienMobs.instance, 64, 3, true, 0x414D25, 0xFC4220);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goblin"), EntityGoblin.class, "goblin", id++, TolkienMobs.instance, 64, 3, true, 0x414D25, 0x556B2F);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mordororc"), EntityMordorOrc.class, "mordor_orc", id++, TolkienMobs.instance, 64, 3, true, 0x414D25, 0x3d0099);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "warg"), EntityWarg.class, "warg", id++, TolkienMobs.instance, 64, 3, true, 0x414D25, 0xFFA000);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "hobbit"), EntityHobbit.class, "hobbit", id++, TolkienMobs.instance, 64, 3, true, 0x414D25, 0xFF32FF);

        // We want our mobs to spawn naturally.
        EntityRegistry.addSpawn(EntityTreeEnt.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.FOREST, Biomes.FOREST_HILLS);
        EntityRegistry.addSpawn(EntityMirkwoodSpider.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
        EntityRegistry.addSpawn(EntityGoblin.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
        EntityRegistry.addSpawn(EntityMordorOrc.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS);
        EntityRegistry.addSpawn(EntityWarg.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS);
        EntityRegistry.addSpawn(EntityHobbit.class, 100, 3, 5, EnumCreatureType.CREATURE, Biomes.PLAINS);

        // This is the loot table for our mobs
        LootTableList.register(EntityTreeEnt.LOOT);
        LootTableList.register(EntityMirkwoodSpider.LOOT);
        LootTableList.register(EntityGoblin.LOOT);
        LootTableList.register(EntityMordorOrc.LOOT);
        LootTableList.register(EntityWarg.LOOT);
        LootTableList.register(EntityHobbit.LOOT);
    }
}
