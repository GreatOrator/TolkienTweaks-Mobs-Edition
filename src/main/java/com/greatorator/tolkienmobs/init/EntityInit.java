package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ammo.EntityAmmo;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void init() {

        LogHelperTTM.info("Adding all the various fauna to see!");
        /** Every entity in our mod has an ID (local to this mod) */
        /** Monsters */
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "huron"), EntityHuron.class, "huron", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x817300);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "treeent"), EntityTreeEnt.class, "tree_ent", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x028200);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mirkwoodspider"), EntityMirkwoodSpider.class, "mirkwood_spider", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x003d89);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goblin"), EntityGoblin.class, "goblin", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x6b0087);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mordororc"), EntityMordorOrc.class, "mordor_orc", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x7f0020);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "urukhai"), EntityUrukHai.class, "uruk_hai", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x51feb3);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "warg"), EntityWarg.class, "warg", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0xe45bff);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mumakil"), EntityMumakil.class, "mumakil", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0xff8707);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "crebain"), EntityCrebain.class, "crebain", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x357bff);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "cavetroll"), EntityTroll.class, "cave_troll", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x005e7f);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "barrowwight"), EntityBarrowWight.class, "barrow_wight", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x005e7f);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "balrog"), EntityBalrog.class, "balrog", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x008d8d);

        /** Passive */
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "auroch"), EntityAuroch.class, "auroch", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x00a041);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "dwarf"), EntityDwarf.class, "dwarf", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xffd312);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "hobbit"), EntityHobbit.class, "hobbit", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xfe519d);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "human"), EntityHuman.class, "human", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x1f7dff);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elves"), EntityElves.class, "elves", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x890037);

        /** Non-mob Entities */
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "boulder"), EntityAmmo.class, "ammo_boulder", id++, TolkienMobs.instance, 64, 3, true);

        /** If we want our mobs to spawn naturally. */
        /** Monsters */
        EntityRegistry.addSpawn(EntityHuron.class, 100, 1, 2, EnumCreatureType.MONSTER, Biomes.FOREST, Biomes.FOREST_HILLS, BiomeInit.MIRKWOOD);
        EntityRegistry.addSpawn(EntityTreeEnt.class, 100, 1, 2, EnumCreatureType.MONSTER, BiomeInit.FANGORN);
        EntityRegistry.addSpawn(EntityMirkwoodSpider.class, 100, 1, 2, EnumCreatureType.MONSTER, BiomeInit.MIRKWOOD);
        EntityRegistry.addSpawn(EntityGoblin.class, 100, 2, 5, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS, BiomeInit.HITHAEGLIR);
        EntityRegistry.addSpawn(EntityMordorOrc.class, 100, 1, 3, EnumCreatureType.MONSTER, BiomeInit.DAGORLAD);
        EntityRegistry.addSpawn(EntityUrukHai.class, 100, 1, 3, EnumCreatureType.MONSTER, BiomeInit.DAGORLAD);
        EntityRegistry.addSpawn(EntityWarg.class, 100, 1, 3, EnumCreatureType.MONSTER, BiomeInit.DAGORLAD);
        EntityRegistry.addSpawn(EntityMumakil.class, 100, 1, 1, EnumCreatureType.MONSTER, BiomeInit.HARADWAITH);
        EntityRegistry.addSpawn(EntityCrebain.class, 100, 1, 3, EnumCreatureType.MONSTER, BiomeInit.HITHAEGLIR);
        EntityRegistry.addSpawn(EntityTroll.class, 100, 1, 2, EnumCreatureType.MONSTER, BiomeInit.HITHAEGLIR);
        EntityRegistry.addSpawn(EntityBarrowWight.class, 100, 1, 3, EnumCreatureType.MONSTER, BiomeInit.BARROW_DOWNS);

        /** Passive */
        EntityRegistry.addSpawn(EntityAuroch.class, 100, 2, 4, EnumCreatureType.CREATURE, BiomeInit.GLADDEN, BiomeInit.SHIRE);
        EntityRegistry.addSpawn(EntityDwarf.class, 100, 2, 4, EnumCreatureType.CREATURE, BiomeInit.IRON_HILLS);
        EntityRegistry.addSpawn(EntityHobbit.class, 100, 2, 4, EnumCreatureType.CREATURE, BiomeInit.SHIRE);
        EntityRegistry.addSpawn(EntityHuman.class, 100, 2, 4, EnumCreatureType.CREATURE, BiomeInit.GLADDEN);
        EntityRegistry.addSpawn(EntityElves.class, 100, 2, 4, EnumCreatureType.CREATURE, BiomeInit.FIRIEN);

        /** These are the loot table for our mobs */
        /** Monsters */
        LootTableList.register(EntityHuron.LOOT);
        LootTableList.register(EntityTreeEnt.LOOT);
        LootTableList.register(EntityMirkwoodSpider.LOOT);
        LootTableList.register(EntityGoblin.LOOT);
        LootTableList.register(EntityMordorOrc.LOOT);
        LootTableList.register(EntityUrukHai.LOOT);
        LootTableList.register(EntityWarg.LOOT);
        LootTableList.register(EntityMumakil.LOOT);
        LootTableList.register(EntityCrebain.LOOT);
        LootTableList.register(EntityTroll.LOOT);
        LootTableList.register(EntityBarrowWight.LOOT);
        LootTableList.register(EntityBalrog.LOOT);

        /** Passive */
        LootTableList.register(EntityAuroch.LOOT);
        LootTableList.register(EntityDwarf.LOOT);
        LootTableList.register(EntityHobbit.LOOT);
        LootTableList.register(EntityHuman.LOOT);
        LootTableList.register(EntityElves.LOOT);

        LogHelperTTM.info("I chose you mobi-chu!");
    }
}
