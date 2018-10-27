package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.*;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {


    // Config instance
    public static Configuration config;

        public void preInit (FMLPreInitializationEvent e){
            File directory = e.getModConfigurationDirectory();
            config = new Configuration(new File(directory.getPath(), "tolkienmobs.cfg"));
            Config.readConfig();

            // Initialization of blocks and items typically goes here:
            TolkienMobsEntities.init();
        }

        public void init(FMLInitializationEvent e) {
        }

        public void postInit (FMLPostInitializationEvent e){
            if (config.hasChanged()) {
                config.save();
            }
        }
    }