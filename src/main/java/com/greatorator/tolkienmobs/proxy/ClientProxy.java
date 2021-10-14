package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.client.TTMClientEvents;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.ProfessionGenerator;
import com.greatorator.tolkienmobs.entity.merchant.villager.VillagerTTMTrades;
import com.greatorator.tolkienmobs.handler.TTMHearts;
import com.greatorator.tolkienmobs.init.TTMColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.greatorator.tolkienmobs.entity.merchant.villager.VillagerTTMUtility.fixPOITypeBlockStates;

public class ClientProxy extends CommonProxy {

    @Override
    public void construct() {
        super.construct();
        MinecraftForge.EVENT_BUS.addListener(TTMClientEvents::livingUpdate);
        MinecraftForge.EVENT_BUS.addListener(TTMClientEvents::renderOverlayEvent);
        MinecraftForge.EVENT_BUS.addListener(VillagerTTMTrades::onVillagerTradesEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TTMColor::itemColourEvent);
    }

    @Override
    public void commonSetup(FMLCommonSetupEvent event) {
        super.commonSetup(event);

        if (!ModList.get().isLoaded("mantle")) {
            MinecraftForge.EVENT_BUS.register(new TTMHearts());
        }

        fixPOITypeBlockStates(ProfessionGenerator.COIN_TRADER.get());
        fixPOITypeBlockStates(ProfessionGenerator.GROCERY_STORE.get());
        fixPOITypeBlockStates(ProfessionGenerator.PET_MERCHANT.get());
        fixPOITypeBlockStates(ProfessionGenerator.JUNK_TRADER.get());
    }

    @Override
    public void clientSetup(FMLClientSetupEvent event) {
        super.clientSetup(event);
        setupRenderLayers();
        EntityGenerator.registerEntityRenderer();
        EntityGenerator.addEntityAttributes();
    }

    public static void setupRenderLayers() {
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_DECAY_BLOOM.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_BLOOM_DECAY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SIMBELMYNE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_MIRKWOOD.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ALFIRIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ATHELAS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_NIPHREDIL.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SWAMPMILKWEED.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MALLORN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MIRKWOOD.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_CULUMALDA.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_LEBETHRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_DEADWOOD.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.MITHRIL_BARS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.MORGULIRON_BARS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.DOOR_MORGULIRON.get(), RenderType.cutout());
    }

    @Override
    public void serverSetup(FMLDedicatedServerSetupEvent event) {
        super.serverSetup(event);
    }

    public PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }
}