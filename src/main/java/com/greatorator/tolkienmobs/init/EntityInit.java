package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.boss.*;
import com.greatorator.tolkienmobs.entity.hostile.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.entity.special.EntityTMGollum;
import com.greatorator.tolkienmobs.entity.special.EntityTMGreatEagle;
import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
import com.greatorator.tolkienmobs.entity.special.EntityTMNazgul;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEnd;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.biome.BiomeVoid;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Collection;
import java.util.LinkedList;

public class EntityInit
{
    public static void init() {

        LogHelperTTM.info("Adding all the various fauna to see!");
        Biome[] spawnBiomes = getAllSpawnBiomes();

        /* Every entity in our mod has an ID (local to this mod) */
        int id = 1;

        if (TTMConfig.enableMonster) {
            /* Monsters */
            if (TTMConfig.enableBarrowWights) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "barrowwight"), EntityTMBarrowWight.class, "barrow_wight", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x9A6324);
            }
            if (TTMConfig.enableCaveTrolls) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "cavetroll"), EntityTMTroll.class, "cave_troll", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x808000);
            }
            if (TTMConfig.enableGoblins) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goblin"), EntityTMGoblin.class, "goblin", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x469990);
            }
            if (TTMConfig.enableHurons) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "huron"), EntityTMHuron.class, "huron", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x000075);
            }
            if (TTMConfig.enableMirkwoodSpiders) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mirkwoodspider"), EntityTMMirkwoodSpider.class, "mirkwood_spider", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x000000);
            }
            if (TTMConfig.enableMordorOrcs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mordororc"), EntityTMMordorOrc.class, "mordor_orc", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xF58231);
            }
            if (TTMConfig.enableTreeEnts) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "treeent"), EntityTMTreeEnt.class, "tree_ent", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xFFE119);
            }
            if (TTMConfig.enableUrukhai) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "urukhai"), EntityTMUrukHai.class, "uruk_hai", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xBFEF45);
            }
            if (TTMConfig.enableWargs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "warg"), EntityTMWarg.class, "warg", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x3CB44B);
            }
            if (TTMConfig.enableOathbreaker) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "oathbreaker"), EntityTMOathbreaker.class, "oathbreaker", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x42D4F4);
            }
            if (TTMConfig.enableMimic) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mimicchest"), EntityTMMimicChest.class, "mimicchest", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x4363D8);
            }
            if (TTMConfig.enableMinotaur) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "minotaur"), EntityTMMinotaur.class, "minotaur", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x911EB4);
            }
            if (TTMConfig.enableBrigand) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmbrigand"), EntityTMBrigand.class, "tmbrigand", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xF032E6);
            }
            if (TTMConfig.enableFellSpirit) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellspirit"), EntityTMFellSpirit.class, "fellspirit", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xFFD8B1);
            }
            if (TTMConfig.enableElementalGolem) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elementalgolem"), EntityTMElementalGolem.class, "elementalgolem", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xAAFFC3);
            }
            if (TTMConfig.enableSwampHag) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "swamp_hag"), EntityTMSwampHag.class, "swamp_hag", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xE6BEFF);
            }
            if (TTMConfig.enableTMDuergar) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmduergar"), EntityTMDuergar.class, "tmduergar", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x988F33);
            }
            if (TTMConfig.enableHaradrim) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmharadrim"), EntityTMHaradrim.class, "tmharadrim", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x9A8FAB);
            }
            if (TTMConfig.enableDeepClaw) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmdeepclaw"), EntityTMDeepClaw.class, "tmdeepclaw", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0x75F543);
            }
            if (TTMConfig.enableRomieWalker) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "romiewalker"), EntityRomieWalker.class, "romiewalker", id++, TolkienMobs.instance, 64, 3, true, 0xFA6272, 0xF5FF43);
            }
        }

        if (TTMConfig.enableBoss) {
            /* Bosses */
            if (TTMConfig.enableBalrog) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "balrog"), EntityTMBalrog.class, "balrog", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0x9A6324);
            }
            if (TTMConfig.enableFellBeast) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellbeast"), EntityTMFellBeast.class, "fellbeast", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0x808000);
            }
            if (TTMConfig.enableWitchKing) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "witchking"), EntityTMWitchKing.class, "witchking", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0x469990);
            }
            if (TTMConfig.enableGwaihir) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "gwaihir"), EntityTMGwaihir.class, "gwaihir", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0x000075);
            }
            if (TTMConfig.enableMorgulGolem) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elementalgolem7"), EntityTMMorgulGolem.class, "elementalgolem7", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0x000000);
            }
            if (TTMConfig.enableWatcher) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmwatcher"), EntityTMWatcher.class, "tmwatcher", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0xF58231);
            }
            if (TTMConfig.enableGoblinKing) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmgoblinking"), EntityTMGoblinKing.class, "tmgoblinking", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0xBFEF45);
            }
            if (TTMConfig.enableTMShelob) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmshelob"), EntityTMShelob.class, "tmshelob", id++, TolkienMobs.instance, 64, 3, true, 0xB71B13, 0xFFE119);
            }
        }

        if (TTMConfig.enableSpecial) {
            /* Special Mobs */
            if (TTMConfig.enableGollum) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "gollum"), EntityTMGollum.class, "gollum", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0x9A6324);
            }
            if (TTMConfig.enableNazgul) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "nazgul"), EntityTMNazgul.class, "nazgul", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0x808000);
            }
            if (TTMConfig.enableGreatEagle) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "greateagle"), EntityTMGreatEagle.class, "greateagle", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0x469990);
            }
            if (TTMConfig.enableMithrilGolem) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elementalgolem6"), EntityTMMithrilGolem.class, "elementalgolem6", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0x000075);
            }
        }

        if (TTMConfig.enablePassive) {
            /* Passive */
            if (TTMConfig.enableAuroch) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "auroch"), EntityTMAuroch.class, "auroch", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x9A6324);
            }
            if (TTMConfig.enableDwarves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "dwarf"), EntityTMDwarf.class, "dwarf", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x808000);
            }
            if (TTMConfig.enableElves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elves"), EntityTMElves.class, "elves", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x469990);
            }
            if (TTMConfig.enableGoats) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goat"), EntityTMGoat.class, "goat", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x000075);
            }
            if (TTMConfig.enableHobbits) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "hobbit"), EntityTMHobbit.class, "hobbit", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x000000);
            }
            if (TTMConfig.enableHumans) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "human"), EntityTMHuman.class, "human", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0xF58231);
            }
            if (TTMConfig.enableMumakil) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mumakil"), EntityTMMumakil.class, "mumakil", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0xFFE119);
            }
        }

        if (TTMConfig.enableAmbient) {
            /* Ambient Mobs */
            if (TTMConfig.enableFrogs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "toaddle"), EntityTMToad.class, "toaddle", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x9A6324);
            }
            if (TTMConfig.enableSquirrels) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "sosquirrel"), EntityTMSquirrel.class, "sosquirrel", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x808000);
            }
            if (TTMConfig.enableThrush) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "thrush"), EntityTMThrush.class, "thrush", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x469990);
            }
            if (TTMConfig.enableMidgeFlies) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "midgefly"), EntityTMMidgeFly.class, "midgefly", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x000075);
            }
            if (TTMConfig.enableCrebain) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "crebain"), EntityTMCrebain.class, "crebain", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x000000);
            }
            if (TTMConfig.enableRats) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmrat"), EntityTMRat.class, "tmrat", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0xF58231);
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
                    EntityRegistry.addSpawn(EntityTMGoblin.class, 12, 1, 2, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableGoblinKing) {
                    EntityRegistry.addSpawn(EntityTMGoblinKing.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
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
                if (TTMConfig.enableCaveTrolls) {
                    EntityRegistry.addSpawn(EntityTMTroll.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableBarrowWights) {
                    EntityRegistry.addSpawn(EntityTMBarrowWight.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableFellSpirit) {
                    EntityRegistry.addSpawn(EntityTMFellSpirit.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
                }
                if (TTMConfig.enableOathbreaker) {
                    EntityRegistry.addSpawn(EntityTMOathbreaker.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.COLD_TAIGA);
                }
                if (TTMConfig.enableMinotaur) {
                    EntityRegistry.addSpawn(EntityTMMinotaur.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableMimic) {
                    EntityRegistry.addSpawn(EntityTMMimicChest.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.DESERT_HILLS);
                }
                if (TTMConfig.enableBrigand) {
                    EntityRegistry.addSpawn(EntityTMBrigand.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.FOREST);
                }
                if (TTMConfig.enableElementalGolem) {
                    EntityRegistry.addSpawn(EntityTMElementalGolem.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.TAIGA,Biomes.DESERT,Biomes.EXTREME_HILLS,Biomes.BEACH,Biomes.PLAINS);
                }
                if (TTMConfig.enableSwampHag) {
                    EntityRegistry.addSpawn(EntityTMSwampHag.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
                }
                if (TTMConfig.enableTMDuergar) {
                    EntityRegistry.addSpawn(EntityTMDuergar.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableHaradrim) {
                    EntityRegistry.addSpawn(EntityTMHaradrim.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.DESERT_HILLS,Biomes.DESERT);
                }
                if (TTMConfig.enableDeepClaw) {
                    EntityRegistry.addSpawn(EntityTMDeepClaw.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableRomieWalker) {
                    EntityRegistry.addSpawn(EntityRomieWalker.class, 12, 1, 1, EnumCreatureType.MONSTER, spawnBiomes);
                }
            }

            if (TTMConfig.enablePassive) {
                /* Passive */
                if (TTMConfig.enableAuroch) {
                    EntityRegistry.addSpawn(EntityTMAuroch.class, 12, 2, 4, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableGoats) {
                    EntityRegistry.addSpawn(EntityTMGoat.class, 12, 2, 4, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableMumakil) {
                    EntityRegistry.addSpawn(EntityTMMumakil.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.DESERT);
                }
            }

            if (TTMConfig.enableAmbient) {
                /* Ambient */
                if (TTMConfig.enableThrush) {
                    EntityRegistry.addSpawn(EntityTMThrush.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.COLD_TAIGA);
                }
                if (TTMConfig.enableSquirrels) {
                    EntityRegistry.addSpawn(EntityTMSquirrel.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.FOREST);
                }
                if (TTMConfig.enableMidgeFlies) {
                    EntityRegistry.addSpawn(EntityTMMidgeFly.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
                }
                if (TTMConfig.enableCrebain) {
                    EntityRegistry.addSpawn(EntityTMCrebain.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableFrogs) {
                    EntityRegistry.addSpawn(EntityTMToad.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.SWAMPLAND);
                }
                if (TTMConfig.enableRats) {
                    EntityRegistry.addSpawn(EntityTMRat.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
            }
        }

        LogHelperTTM.info("I chose you mobi-chu!");
    }

    private static Biome[] getAllSpawnBiomes() {
        LinkedList<Biome> list = new LinkedList<>();
        Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();
        for (Biome bgb : biomes) {
            if (bgb instanceof BiomeVoid) {
                continue;
            }
            if (bgb instanceof BiomeEnd && !TTMConfig.getSpawnInEnd) {
                continue;
            }
            if (bgb instanceof BiomeHell && !TTMConfig.getSpawnInNether) {
                continue;
            }
            if (!list.contains(bgb)) {
                list.add(bgb);
                if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                    LogHelperTTM.info("  >>> getAllSpawnBiomes: " + bgb.getBiomeName());
                }
            }
        }
        return list.toArray(new Biome[0]);
    }
}