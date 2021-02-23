package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.greatorator.tolkienmobs.init.TTMTags;
import com.greatorator.tolkienmobs.integration.TTMEquipMgr;
import com.greatorator.tolkienmobs.server.TTMServerEvents;
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
        TTMEquipMgr.initialize();
        TTMTags.init();
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::livingUpdate);

    }

    public void commonSetup(FMLCommonSetupEvent event) {
        PotionGenerator.addPotionRecipes();
        //EntityGenerator.registerSpawnEggs();
    }

    public void clientSetup(FMLClientSetupEvent event) {

    }

    public void serverSetup(FMLDedicatedServerSetupEvent event) {

    }


    public void registerEventListeners() {

    }

    public PlayerEntity getPlayer() {
        return null;
    }
}
