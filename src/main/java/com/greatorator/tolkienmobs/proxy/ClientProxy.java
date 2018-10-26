package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TolkienMobsEntities;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        // Typically initialization of models and such goes here:
        TolkienMobsEntities.initModels();
    }
}
