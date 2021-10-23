package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.TTMClientEvents;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.ProfessionGenerator;
import com.greatorator.tolkienmobs.entity.ambient.model.ModelTTMSwarm;
import com.greatorator.tolkienmobs.entity.ambient.render.*;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderBoulder;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.boss.render.RenderTTMGoblinKing;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMDwarf;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMElves;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMHobbit;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMHuman;
import com.greatorator.tolkienmobs.entity.merchant.villager.VillagerTTMTrades;
import com.greatorator.tolkienmobs.entity.monster.render.RenderTTMBarrowWight;
import com.greatorator.tolkienmobs.entity.monster.render.RenderTTMBrigand;
import com.greatorator.tolkienmobs.entity.monster.render.RenderTTMGoblin;
import com.greatorator.tolkienmobs.entity.passive.render.RenderTTMAuroch;
import com.greatorator.tolkienmobs.entity.passive.render.RenderTTMGoat;
import com.greatorator.tolkienmobs.entity.passive.render.RenderTTMMumakil;
import com.greatorator.tolkienmobs.handler.TTMHearts;
import com.greatorator.tolkienmobs.init.TTMColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
        registerEntityRenderer();
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
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_MALLORN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_MIRKWOOD.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_CULUMALDA.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_LEBETHRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_MALLORN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_MIRKWOOD.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_CULUMALDA.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_LEBETHRON.get(), RenderType.cutout());

    }

    //#################################################################
    // Render Registry
    //#################################################################
    public static void registerEntityRenderer() {
        // Ambient
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_RAT.get(), RenderTTMRat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SQUIRREL.get(), RenderTTMSquirrel::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_FROG.get(), RenderTTMFrog::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SWARM.get(), m -> new RenderTTMSwarm<>(m, new ModelTTMSwarm(), 0.5F, TolkienMobs.MODID + ":textures/entity/midgeflies.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_THRUSH.get(), RenderTTMThrush::new);

        // Merchants
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HUMAN.get(), RenderTTMHuman::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DWARF.get(), RenderTTMDwarf::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_ELVES.get(), RenderTTMElves::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HOBBIT.get(), RenderTTMHobbit::new);

        // Monster
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOBLIN.get(), RenderTTMGoblin::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BARROW.get(), RenderTTMBarrowWight::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BRIGAND.get(), RenderTTMBrigand::new);

        // Boss
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOBLINKING.get(), RenderTTMGoblinKing::new);

        // Passive
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_AUROCH.get(), RenderTTMAuroch::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MUMAKIL.get(), RenderTTMMumakil::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOAT.get(), RenderTTMGoat::new);

        // Ammo
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_ARROW_GALADHRIM.get(), new RenderGaladhrimArrow.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), new RenderFellBeastFireball.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_BOULDER.get(), new RenderBoulder.RenderFactory());
    }

    @Override
    public void serverSetup(FMLDedicatedServerSetupEvent event) {
        super.serverSetup(event);
    }

    public PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }
}