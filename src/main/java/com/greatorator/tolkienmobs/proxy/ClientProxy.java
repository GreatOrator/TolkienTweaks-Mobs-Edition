package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.event.client.ClientEvents;
import com.greatorator.tolkienmobs.handler.BowItemModelHandler;
import com.greatorator.tolkienmobs.handler.ColorHandler;
import com.greatorator.tolkienmobs.handler.HealthHandler;
import com.greatorator.tolkienmobs.handler.registers.SpritesRegister;
import com.greatorator.tolkienmobs.init.TolkienWoodTypes;
import com.greatorator.tolkienmobs.init.renders.TolkienGuiRenders;
import com.greatorator.tolkienmobs.init.renders.TolkienItemBlockRenders;
import com.greatorator.tolkienmobs.init.renders.TolkienTileRenders;
import com.greatorator.tolkienmobs.integration.IntegrationHelper;
import com.greatorator.tolkienmobs.item.container.CoinPouchItem;
import com.greatorator.tolkienmobs.item.container.KeyRingItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
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
//        MinecraftForge.EVENT_BUS.addListener(TTMVillagerTrades::onVillagerTradesEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ColorHandler::itemColourEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SpritesRegister::initialize);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEvents::onModelBakeEvent);
    }

    @Override
    public void commonSetup(FMLCommonSetupEvent event) {
        super.commonSetup(event);

        if (!IntegrationHelper.isMantleInstalled) {
            MinecraftForge.EVENT_BUS.register(new HealthHandler());
        }
    }

    @Override
    public void clientSetup(FMLClientSetupEvent event) {
        super.clientSetup(event);

        registerRenders();
        registerPropertyOverride();
        registerWoodTypes(event);
    }

    private void registerRenders() {
        TolkienGuiRenders.init();
        TolkienItemBlockRenders.init();
        TolkienTileRenders.init();
        BowItemModelHandler.makeBow(ELVEN_BOW.get());
        BowItemModelHandler.makeBow(URUK_BOW.get());
    }

    public void registerPropertyOverride() {
        ItemProperties.register(COIN_POUCH.get(), new ResourceLocation("fullness"), CoinPouchItem::getFullnessPropertyOverride);
        ItemProperties.register(KEY_RING.get(), new ResourceLocation("fullness"), KeyRingItem::getFullnessPropertyOverride);
    }

    private void registerWoodTypes(FMLClientSetupEvent event) {
        //Add each of your custom wood types here. This is for textures.
        event.enqueueWork(() -> {
                     Sheets.addWoodType(TolkienWoodTypes.MALLORN);
                     Sheets.addWoodType(TolkienWoodTypes.MIRKWOOD);
                     Sheets.addWoodType(TolkienWoodTypes.CULUMALDA);
                     Sheets.addWoodType(TolkienWoodTypes.LEBETHRON);
                     Sheets.addWoodType(TolkienWoodTypes.DEADWOOD);
                     Sheets.addWoodType(TolkienWoodTypes.FANGORNOAK);
        });
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