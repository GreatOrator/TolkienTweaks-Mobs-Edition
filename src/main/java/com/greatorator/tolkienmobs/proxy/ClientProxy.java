package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.client.TTMClientEvents;
import com.greatorator.tolkienmobs.handler.TTMExtraHearts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class ClientProxy extends CommonProxy {


    @Override
    public void construct() {
        super.construct();
        MinecraftForge.EVENT_BUS.addListener(TTMClientEvents::livingUpdate);
    }

    @Override
    public void commonSetup(FMLCommonSetupEvent event) {
        super.commonSetup(event);

        if (!ModList.get().isLoaded("mantle")) {
            MinecraftForge.EVENT_BUS.register(new TTMExtraHearts());
        }
    }

    @Override
    public void clientSetup(FMLClientSetupEvent event) {
        super.clientSetup(event);
        setupRenderLayers();
    }

    @Override
    public void serverSetup(FMLDedicatedServerSetupEvent event) {
        super.serverSetup(event);
    }

    private void setupRenderLayers() {
        // Plant & Flowers
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_DECAY_BLOOM.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_BLOOM_DECAY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SIMBELMYNE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_MIRKWOOD.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ALFIRIN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ATHELAS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_NIPHREDIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SWAMPMILKWEED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MALLORN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MIRKWOOD.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_CULUMALDA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_LEBETHRON.get(), RenderType.getCutout());
    }

    public PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }
}