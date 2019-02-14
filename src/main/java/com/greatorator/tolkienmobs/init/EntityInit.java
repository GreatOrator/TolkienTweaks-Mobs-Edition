package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.boss.EntityBalrog;
import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import com.greatorator.tolkienmobs.entity.special.EntityNazgul;
import com.greatorator.tolkienmobs.entity.boss.EntityWitchKing;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.entity.special.EntityGollum;
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
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "barrowwight"), EntityBarrowWight.class, "barrow_wight", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x005e7f);
            }
            if (TTMConfig.enableCaveTrolls) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "cavetroll"), EntityTroll.class, "cave_troll", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x005e7f);
            }
            if (TTMConfig.enableCrebain) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "crebain"), EntityCrebain.class, "crebain", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x357bff);
            }
            if (TTMConfig.enableGoblins) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goblin"), EntityGoblin.class, "goblin", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x6b0087);
            }
            if (TTMConfig.enableHurons) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "huron"), EntityHuron.class, "huron", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x817300);
            }
            if (TTMConfig.enableMirkwoodSpiders) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mirkwoodspider"), EntityMirkwoodSpider.class, "mirkwood_spider", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x003d89);
            }
            if (TTMConfig.enableMordorOrcs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mordororc"), EntityMordorOrc.class, "mordor_orc", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x7f0020);
            }
            if (TTMConfig.enableTreeEnts) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "treeent"), EntityTreeEnt.class, "tree_ent", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x028200);
            }
            if (TTMConfig.enableUrukhai) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "urukhai"), EntityUrukHai.class, "uruk_hai", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0x51feb3);
            }
            if (TTMConfig.enableWargs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "warg"), EntityWarg.class, "warg", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0xe45bff);
            }
            if (TTMConfig.enableOathbreaker) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "oathbreaker"), EntityOathbreaker.class, "oathbreaker", id++, TolkienMobs.instance, 64, 3, true, 0xF4A460, 0xeffbff);
            }
        }

        if (TTMConfig.enableBoss) {
            /* Bosses */
            if (TTMConfig.enableBalrog) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "balrog"), EntityBalrog.class, "balrog", id++, TolkienMobs.instance, 64, 3, true, 0xff5050, 0x008d8d);
            }
            if (TTMConfig.enableFellBeast) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellbeast"), EntityFellBeast.class, "fellbeast", id++, TolkienMobs.instance, 64, 3, true, 0xff5050, 0xff8d8d);
            }
            if (TTMConfig.enableWitchKing) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "witchking"), EntityWitchKing.class, "witchking", id++, TolkienMobs.instance, 64, 3, true, 0xff5050, 0x00ff8d);
            }
        }

        if (TTMConfig.enableSpecial) {
            /* Special Mobs */
            if (TTMConfig.enableGollum) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "gollum"), EntityGollum.class, "gollum", id++, TolkienMobs.instance, 64, 3, true, 0xA266DF, 0x568d8d);
            }
            if (TTMConfig.enableNazgul) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "nazgul"), EntityNazgul.class, "nazgul", id++, TolkienMobs.instance, 64, 3, true, 0xA266DF, 0x00ff8d);
            }
        }

        if (TTMConfig.enablePassive) {
            /* Passive */
            if (TTMConfig.enableAuroch) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "auroch"), EntityAuroch.class, "auroch", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x00a041);
            }
            if (TTMConfig.enableDwarves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "dwarf"), EntityDwarf.class, "dwarf", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xffd312);
            }
            if (TTMConfig.enableElves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elves"), EntityElves.class, "elves", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x89cc37);
            }
            if (TTMConfig.enableGoats) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goat"), EntityGoat.class, "goat", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xe9ff3e);
            }
            if (TTMConfig.enableHobbits) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "hobbit"), EntityHobbit.class, "hobbit", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xfe519d);
            }
            if (TTMConfig.enableHumans) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "human"), EntityHuman.class, "human", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0x1f7dff);
            }
            if (TTMConfig.enableMumakil) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mumakil"), EntityMumakil.class, "mumakil", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xff8707);
            }
            if (TTMConfig.enableFrogs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "toaddle"), EntityToaddle.class, "toaddle", id++, TolkienMobs.instance, 64, 3, true, 0x2F9A9F, 0xaaccff);
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
                    EntityRegistry.addSpawn(EntityHuron.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.FOREST, Biomes.FOREST_HILLS);
                }
                if (TTMConfig.enableTreeEnts) {
                    EntityRegistry.addSpawn(EntityTreeEnt.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableMirkwoodSpiders) {
                    EntityRegistry.addSpawn(EntityMirkwoodSpider.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableGoblins) {
                    EntityRegistry.addSpawn(EntityGoblin.class, 12, 2, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableMordorOrcs) {
                    EntityRegistry.addSpawn(EntityMordorOrc.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableUrukhai) {
                    EntityRegistry.addSpawn(EntityUrukHai.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableWargs) {
                    EntityRegistry.addSpawn(EntityWarg.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableCrebain) {
                    EntityRegistry.addSpawn(EntityCrebain.class, 12, 1, 1, EnumCreatureType.AMBIENT, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableCaveTrolls) {
                    EntityRegistry.addSpawn(EntityTroll.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableBarrowWights) {
                    EntityRegistry.addSpawn(EntityBarrowWight.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableOathbreaker) {
                    EntityRegistry.addSpawn(EntityOathbreaker.class, 12, 1, 1, EnumCreatureType.AMBIENT, Biomes.COLD_TAIGA);
                }
            }

            if (TTMConfig.enablePassive) {
                /* Passive */
                if (TTMConfig.enableAuroch) {
                    EntityRegistry.addSpawn(EntityAuroch.class, 12, 2, 4, EnumCreatureType.CREATURE, Biomes.PLAINS);
                }
                if (TTMConfig.enableGoats) {
                    EntityRegistry.addSpawn(EntityGoat.class, 12, 2, 4, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableFrogs) {
                    EntityRegistry.addSpawn(EntityToaddle.class, 12, 1, 1, EnumCreatureType.WATER_CREATURE, Biomes.PLAINS);
                }
                if (TTMConfig.enableMumakil) {
                    EntityRegistry.addSpawn(EntityMumakil.class, 12, 1, 1, EnumCreatureType.CREATURE, Biomes.DESERT);
                }
            }
        }

        LogHelperTTM.info("I chose you mobi-chu!");
    }
}