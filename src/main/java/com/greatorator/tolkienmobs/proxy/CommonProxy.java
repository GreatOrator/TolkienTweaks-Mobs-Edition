package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.greatorator.tolkienmobs.server.TTMServerEvents;
import com.greatorator.tolkienmobs.utils.utils_old.TTMSpawnEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class CommonProxy {

    public void construct() {
        registerEventListeners();
        TTMConfig.load();
        TTMContent.init();
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::livingUpdate);

    }

    public void commonSetup(FMLCommonSetupEvent event) {
        PotionGenerator.addPotionRecipes();
    }

    public void clientSetup(FMLClientSetupEvent event) {

    }

    public void serverSetup(FMLDedicatedServerSetupEvent event) {

    }


    public void registerEventListeners() {
        MinecraftForge.EVENT_BUS.addListener(TTMSpawnEvent::onEntitySpawn);
    }

    public PlayerEntity getPlayer() {
        return null;
    }
}
