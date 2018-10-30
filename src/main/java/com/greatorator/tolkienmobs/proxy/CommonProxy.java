package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.util.handlers.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries();
    }

    public void init(FMLInitializationEvent event) {
        RegistryHandler.initRegistries();
    }

    public void postInit(FMLPostInitializationEvent event) {
        RegistryHandler.postInitRegistries();
    }


    public void registerItemRenderer(Item item, int meta, String variant) {
    }

    public void registerItemRenderer(Item item, int meta, String name, String variant) {
    }

    public void register(Object o) {
    }
}
