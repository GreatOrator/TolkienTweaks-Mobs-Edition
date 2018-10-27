package com.greatorator.tolkienmobs;

import com.greatorator.tolkienmobs.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

    private static final String CATEGORY_GENERAL = "Mob Spawns Allowed";
    private static final String CATEGORY_BIOMES = "Biome Blacklist";

    // This values below you can access elsewhere in your mod:
    public static boolean CanMobSpawnTreeEnt = true;
    public static boolean BiomeName = true;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initBiomeConfig(cfg);
        } catch (Exception e1) {
            TolkienMobs.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "Spawning Configuration");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        CanMobSpawnTreeEnt = cfg.getBoolean("TreeEntSpawn", CATEGORY_GENERAL, CanMobSpawnTreeEnt, "Set to false if you don't want this to spawn naturally.");

    }

    private static void initBiomeConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_BIOMES, "Biome Blacklist");
        BiomeName = cfg.getBoolean("biomeName", CATEGORY_BIOMES, BiomeName, "Does mob spawn in biome?");
    }
}

