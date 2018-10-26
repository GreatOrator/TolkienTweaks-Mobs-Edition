package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.*;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {

        // Initialization of blocks and items typically goes here:
        TolkienMobsEntities.init();
    }
}
