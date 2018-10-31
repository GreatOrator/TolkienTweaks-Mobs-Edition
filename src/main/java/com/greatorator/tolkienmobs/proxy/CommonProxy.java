package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.init.EntityInit;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomOres;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);

        BiomeInit.registerBiomes();
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}
