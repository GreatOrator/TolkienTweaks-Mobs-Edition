package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.entity.merchant.villager.TTMVillagerTrades;
import com.greatorator.tolkienmobs.event.client.ClientEvents;
import com.greatorator.tolkienmobs.handler.*;
import com.greatorator.tolkienmobs.init.renders.*;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.item.tools.CoinPouchItem;
import com.greatorator.tolkienmobs.item.tools.KeyRingItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.greatorator.tolkienmobs.init.TolkienItems.*;

public class ClientProxy extends CommonProxy {

    @Override
    public void construct() {
        super.construct();
        MinecraftForge.EVENT_BUS.addListener(ClientEvents::livingUpdate);
        MinecraftForge.EVENT_BUS.addListener(ClientEvents::renderOverlayEvent);
        MinecraftForge.EVENT_BUS.addListener(TTMVillagerTrades::onVillagerTradesEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TTMColor::itemColourEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TTMSprites::initialize);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEvents::onModelBakeEvent);
    }

    @Override
    public void commonSetup(FMLCommonSetupEvent event) {
        super.commonSetup(event);

        if (!TTMHelper.isMantleInstalled) {
            MinecraftForge.EVENT_BUS.register(new TTMHearts());
        }
    }

    @Override
    public void clientSetup(FMLClientSetupEvent event) {
        super.clientSetup(event);
        setupRenderLayers();
        registerPropertyOverride();
        registerEntityRenderer();
        registerTileRenderers();
        registerWoodTypes(event);
    }

    private static void registerWoodTypes(FMLClientSetupEvent event) {
        //Add each of your custom wood types here. This is for textures.
        event.enqueueWork(() -> Sheets.addWoodType(TolkienWoodTypes.MALLORN));
        event.enqueueWork(() -> Sheets.addWoodType(TolkienWoodTypes.MIRKWOOD));
        event.enqueueWork(() -> Sheets.addWoodType(TolkienWoodTypes.CULUMALDA));
        event.enqueueWork(() -> Sheets.addWoodType(TolkienWoodTypes.LEBETHRON));
    }

    //#################################################################
    // Render Registry
    //#################################################################
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        TolkienEntityRenders.init(event);
    }

    public static void setupRenderLayers() {
        TolkienBlockRenders.init();
        TolkienGuiRenders.init();
        TolkienFluidRenders.init();

        BowItemModelProperties.makeBow(ELVEN_BOW.get());
        BowItemModelProperties.makeBow(URUK_BOW.get());

    }
    public static void registerPropertyOverride() {
        ItemProperties.register(COIN_POUCH.get(), new ResourceLocation("fullness"), CoinPouchItem::getFullnessPropertyOverride);
        ItemProperties.register(KEY_RING.get(), new ResourceLocation("fullness"), KeyRingItem::getFullnessPropertyOverride);
    }

    private void registerTileRenderers() {
        TolkienTileRenders.init();
    }

    @Override
    public void serverSetup(FMLDedicatedServerSetupEvent event) {
        super.serverSetup(event);
    }

    @Override
    public Player getPlayer() {
        return Minecraft.getInstance().player;
    }
}