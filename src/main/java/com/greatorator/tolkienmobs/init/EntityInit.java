package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.boss.EntityTMBalrog;
import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
import com.greatorator.tolkienmobs.entity.special.EntityTMNazgul;
import com.greatorator.tolkienmobs.entity.boss.EntityTMWitchKing;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.entity.special.EntityTMGollum;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void init() {

        LogHelperTTM.info("Adding all the various fauna to see!");
        /* Every entity in our mod has an ID (local to this mod) */
        int id = 1;

        if (TTMConfig.enableMonster) {
            /* Monsters */
            if (TTMConfig.enableBarrowWights) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "barrowwight"), EntityTMBarrowWight.class, "barrow_wight", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x005e7f);
            }
            if (TTMConfig.enableCaveTrolls) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "cavetroll"), EntityTMTroll.class, "cave_troll", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x005e7f);
            }
            if (TTMConfig.enableCrebain) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "crebain"), EntityTMCrebain.class, "crebain", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x357bff);
            }
            if (TTMConfig.enableGoblins) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goblin"), EntityTMGoblin.class, "goblin", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x6b0087);
            }
            if (TTMConfig.enableHurons) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "huron"), EntityTMHuron.class, "huron", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x817300);
            }
            if (TTMConfig.enableMirkwoodSpiders) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mirkwoodspider"), EntityTMMirkwoodSpider.class, "mirkwood_spider", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x003d89);
            }
            if (TTMConfig.enableMordorOrcs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mordororc"), EntityTMMordorOrc.class, "mordor_orc", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x7f0020);
            }
            if (TTMConfig.enableTreeEnts) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "treeent"), EntityTMTreeEnt.class, "tree_ent", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x028200);
            }
            if (TTMConfig.enableUrukhai) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "urukhai"), EntityTMUrukHai.class, "uruk_hai", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x51feb3);
            }
            if (TTMConfig.enableWargs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "warg"), EntityTMWarg.class, "warg", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0xe45bff);
            }
            if (TTMConfig.enableOathbreaker) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "oathbreaker"), EntityTMOathbreaker.class, "oathbreaker", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0xeffbff);
            }
            if (TTMConfig.enableMidgeFlies) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "midgefly"), EntityTMMidgeFly.class, "midgefly", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x000000);
            }
        }

        if (TTMConfig.enableBoss) {
            /* Bosses */
            if (TTMConfig.enableBalrog) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "balrog"), EntityTMBalrog.class, "balrog", id++, TolkienMobs.instance, 64, 3, true, 0xff5050, 0x008d8d);
            }
            if (TTMConfig.enableFellBeast) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellbeast"), EntityTMFellBeast.class, "fellbeast", id++, TolkienMobs.instance, 64, 3, true, 0xff5050, 0xff8d8d);
            }
            if (TTMConfig.enableWitchKing) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "witchking"), EntityTMWitchKing.class, "witchking", id++, TolkienMobs.instance, 64, 3, true, 0xff5050, 0x00ff8d);
            }
        }

        if (TTMConfig.enableSpecial) {
            /* Special Mobs */
            if (TTMConfig.enableGollum) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "gollum"), EntityTMGollum.class, "gollum", id++, TolkienMobs.instance, 64, 3, true, 0xA266DF, 0x568d8d);
            }
            if (TTMConfig.enableNazgul) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "nazgul"), EntityTMNazgul.class, "nazgul", id++, TolkienMobs.instance, 64, 3, true, 0xA266DF, 0x00ff8d);
            }
        }

        if (TTMConfig.enablePassive) {
            /* Passive */
            if (TTMConfig.enableAuroch) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "auroch"), EntityTMAuroch.class, "auroch", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x00a041);
            }
            if (TTMConfig.enableDwarves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "dwarf"), EntityTMDwarf.class, "dwarf", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xffd312);
            }
            if (TTMConfig.enableElves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elves"), EntityTMElves.class, "elves", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x89cc37);
            }
            if (TTMConfig.enableGoats) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goat"), EntityTMGoat.class, "goat", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xe9ff3e);
            }
            if (TTMConfig.enableHobbits) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "hobbit"), EntityTMHobbit.class, "hobbit", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xfe519d);
            }
            if (TTMConfig.enableHumans) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "human"), EntityTMHuman.class, "human", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x1f7dff);
            }
            if (TTMConfig.enableMumakil) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mumakil"), EntityTMMumakil.class, "mumakil", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xff8707);
            }
            if (TTMConfig.enableFrogs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "toaddle"), EntityTMToad.class, "toaddle", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xaaccff);
            }
            if (TTMConfig.enableSquirrels) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "sosquirrel"), EntityTMSquirrel.class, "sosquirrel", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x4b3e2f);
            }
            if (TTMConfig.enableThrush) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "thrush"), EntityTMThrush.class, "thrush", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xD2691E);
            }
        }

        /* Non-mob Entities */
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "boulder"), EntityBoulder.class, "ammo_boulder", id++, TolkienMobs.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellbeast"), EntityFellBeastFireball.class, "fellbeast_fireball", id++, TolkienMobs.instance, 64, 3, true);

        /* If we want our mobs to spawn naturally. */
        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableMonster) {
                /* Monsters */
                if (TTMConfig.enableHurons) {
                    EntityRegistry.addSpawn(EntityTMHuron.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.FOREST, Biomes.FOREST_HILLS);
                }
                if (TTMConfig.enableTreeEnts) {
                    EntityRegistry.addSpawn(EntityTMTreeEnt.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableMirkwoodSpiders) {
                    EntityRegistry.addSpawn(EntityTMMirkwoodSpider.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableGoblins) {
                    EntityRegistry.addSpawn(EntityTMGoblin.class, 12, 2, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableMordorOrcs) {
                    EntityRegistry.addSpawn(EntityTMMordorOrc.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableUrukhai) {
                    EntityRegistry.addSpawn(EntityTMUrukHai.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableWargs) {
                    EntityRegistry.addSpawn(EntityTMWarg.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableCrebain) {
                    EntityRegistry.addSpawn(EntityTMCrebain.class, 12, 1, 1, EnumCreatureType.AMBIENT, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableCaveTrolls) {
                    EntityRegistry.addSpawn(EntityTMTroll.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableBarrowWights) {
                    EntityRegistry.addSpawn(EntityTMBarrowWight.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableOathbreaker) {
                    EntityRegistry.addSpawn(EntityTMOathbreaker.class, 12, 1, 1, EnumCreatureType.AMBIENT, Biomes.COLD_TAIGA);
                }
                if (TTMConfig.enableMidgeFlies) {
                    EntityRegistry.addSpawn(EntityTMMidgeFly.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
                }
            }

            if (TTMConfig.enablePassive) {
                /* Passive */
                if (TTMConfig.enableAuroch) {
                    EntityRegistry.addSpawn(EntityTMAuroch.class, 12, 2, 4, EnumCreatureType.CREATURE, Biomes.PLAINS);
                }
                if (TTMConfig.enableGoats) {
                    EntityRegistry.addSpawn(EntityTMGoat.class, 12, 2, 4, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableFrogs) {
                    EntityRegistry.addSpawn(EntityTMToad.class, 12, 1, 1, EnumCreatureType.WATER_CREATURE, Biomes.PLAINS);
                }
                if (TTMConfig.enableSquirrels) {
                    EntityRegistry.addSpawn(EntityTMSquirrel.class, 12, 1, 1, EnumCreatureType.CREATURE, Biomes.FOREST);
                }
                if (TTMConfig.enableMumakil) {
                    EntityRegistry.addSpawn(EntityTMMumakil.class, 12, 1, 1, EnumCreatureType.CREATURE, Biomes.DESERT);
                }
                if (TTMConfig.enableThrush) {
                    EntityRegistry.addSpawn(EntityTMThrush.class, 12, 1, 1, EnumCreatureType.CREATURE, Biomes.COLD_TAIGA);
                }
            }
        }

        LogHelperTTM.info("I chose you mobi-chu!");
    }
}
