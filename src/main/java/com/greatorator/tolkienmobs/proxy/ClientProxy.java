package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.container.gui.*;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.ProfessionGenerator;
import com.greatorator.tolkienmobs.entity.ambient.model.SwarmModel;
import com.greatorator.tolkienmobs.entity.ambient.render.*;
import com.greatorator.tolkienmobs.entity.boss.model.WitchKingModel;
import com.greatorator.tolkienmobs.entity.boss.render.*;
import com.greatorator.tolkienmobs.entity.item.BoulderEntity;
import com.greatorator.tolkienmobs.entity.item.FellBeastFireballEntity;
import com.greatorator.tolkienmobs.entity.item.render.GaladhrimArrowRender;
import com.greatorator.tolkienmobs.entity.item.render.TolkienBoatRender;
import com.greatorator.tolkienmobs.entity.item.render.UtumnoArrowRender;
import com.greatorator.tolkienmobs.entity.merchant.model.DwarfModel;
import com.greatorator.tolkienmobs.entity.merchant.model.HumanModel;
import com.greatorator.tolkienmobs.entity.merchant.render.*;
import com.greatorator.tolkienmobs.entity.merchant.villager.TTMVillagerTrades;
import com.greatorator.tolkienmobs.entity.monster.model.*;
import com.greatorator.tolkienmobs.entity.monster.render.*;
import com.greatorator.tolkienmobs.entity.passive.render.AurochRender;
import com.greatorator.tolkienmobs.entity.passive.render.GoatRender;
import com.greatorator.tolkienmobs.entity.passive.render.MumakilRender;
import com.greatorator.tolkienmobs.entity.special.render.*;
import com.greatorator.tolkienmobs.entity.tile.render.*;
import com.greatorator.tolkienmobs.event.client.ClientEvents;
import com.greatorator.tolkienmobs.handler.*;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.item.tools.CoinPouchItem;
import com.greatorator.tolkienmobs.item.tools.KeyRingItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.greatorator.tolkienmobs.TTMContent.*;
import static com.greatorator.tolkienmobs.entity.merchant.villager.TTMVillagerUtility.fixPOITypeBlockStates;

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

        fixPOITypeBlockStates(ProfessionGenerator.COIN_TRADER.get());
        fixPOITypeBlockStates(ProfessionGenerator.GROCERY_STORE.get());
        fixPOITypeBlockStates(ProfessionGenerator.PET_MERCHANT.get());
        fixPOITypeBlockStates(ProfessionGenerator.JUNK_TRADER.get());
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
        event.enqueueWork(() -> Atlases.addWoodType(TTMWoodTypes.MALLORN));
        event.enqueueWork(() -> Atlases.addWoodType(TTMWoodTypes.MIRKWOOD));
        event.enqueueWork(() -> Atlases.addWoodType(TTMWoodTypes.CULUMALDA));
        event.enqueueWork(() -> Atlases.addWoodType(TTMWoodTypes.LEBETHRON));
    }

    public static void setupRenderLayers() {
        RenderType cutout = RenderType.cutout();
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType translucent = RenderType.translucent();

        // Blocks
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_DECAY_BLOOM.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_BLOOM_DECAY.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SIMBELMYNE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ALFIRIN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ATHELAS.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_NIPHREDIL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SWAMPMILKWEED.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_DEADWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_FANGORNOAK.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.MITHRIL_BARS.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.MORGULIRON_BARS.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.DOOR_MORGULIRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MITHRIL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MORGULIRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MITHRIL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MORGULIRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_MALLORN.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_MIRKWOOD.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_CULUMALDA.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_LEBETHRON.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_FANGORNOAK.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.TTMFIREPLACE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.PIGGYBANK.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.PIPEWEED.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PLACARD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.CHAMELEON_BLOCK.get(), cutout);
        RenderTypeLookup.setRenderLayer(KEY_STONE_BLOCK.get(), cutout);
        RenderTypeLookup.setRenderLayer(CAMO_GLOWSTONE_BLOCK.get(), cutout);
        RenderTypeLookup.setRenderLayer(CAMO_SMOKER_BLOCK.get(), cutout);
        RenderTypeLookup.setRenderLayer(CAMO_FLUID_BLOCK.get(), cutout);
        RenderTypeLookup.setRenderLayer(CAMO_SPAWNER_BLOCK.get(), cutout);
        RenderTypeLookup.setRenderLayer(ROCKPILE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(MILESTONE_BLOCK.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(BLOCK_AMMOLITE.get(), translucent);

        // Fluids
        RenderTypeLookup.setRenderLayer(MITHRIL_FLUID.get(), translucent);
        RenderTypeLookup.setRenderLayer(MITHRIL_FLUID_BLOCK.get(), translucent);
        RenderTypeLookup.setRenderLayer(MITHRIL_FLOWING.get(), translucent);
        RenderTypeLookup.setRenderLayer(MORGULIRON_FLUID.get(), translucent);
        RenderTypeLookup.setRenderLayer(MORGULIRON_FLUID_BLOCK.get(), translucent);
        RenderTypeLookup.setRenderLayer(MORGULIRON_FLOWING.get(), translucent);

        // Bow Rendering
        BowItemModelProperties.makeBow(TTMContent.ELVEN_BOW.get());
        BowItemModelProperties.makeBow(URUK_BOW.get());

        // GUI Rendering
        ScreenManager.register(TTMContent.TMFIREPLACE_CONTAINER, FireplaceScreen::new);
        ScreenManager.register(TTMContent.PIGGYBANK_CONTAINER, PiggyBankScreen::new);
        ScreenManager.register(TTMContent.BARREL_MITHRIL_CONTAINER, MithrilBarrelScreen::new);
        ScreenManager.register(TTMContent.BARREL_MORGULIRON_CONTAINER, MorgulironBarrelScreen::new);
        ScreenManager.register(TTMContent.BACKPACK_CONTAINER, BackpackScreen::new);
        ScreenManager.register(TTMContent.COIN_POUCH_CONTAINER, CoinPouchScreen::new);
        ScreenManager.register(TTMContent.KEY_RING_CONTAINER, KeyRingScreen::new);
        ScreenManager.register(TTMContent.MILESTONE_CONTAINER, MilestoneScreen::new);
        ScreenManager.register(TTMContent.KEY_STONE_CONTAINER, CamoKeyStoneScreen::new);
        ScreenManager.register(TTMContent.CAMO_SPAWNER_CONTAINER, CamoSpawnerScreen::new);
        ScreenManager.register(TTMContent.CAMO_CHEST_CONTAINER, CamoChestScreen::new);
        ScreenManager.register(TTMContent.CAMO_FLUID_CONTAINER, CamoFluidScreen::new);
        ScreenManager.register(TTMContent.LOCKABLE_CHEST_CONTAINER, LockableChestScreen::new);
        ScreenManager.register(TTMContent.LOCKABLE_TREASURE_CHEST_CONTAINER, LockableTreasureChestScreen::new);
        ScreenManager.register(TTMContent.LOCKABLE_DOUBLE_CHEST_CONTAINER, LockableDoubleChestScreen::new);
        ScreenManager.register(TTMContent.LOCKABLE_DOUBLE_TREASURE_CHEST_CONTAINER, LockableDoubleTreasureChestScreen::new);
    }

    //#################################################################
    // Render Registry
    //#################################################################
    public static void registerEntityRenderer() {
        // Ambient
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_RAT.get(), RatRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SQUIRREL.get(), SquirrelRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_FROG.get(), FrogRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SWARM.get(), m -> new SwarmRender<>(m, new SwarmModel(), 0.5F, TolkienMobs.MODID + ":textures/entity/midgeflies.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_THRUSH.get(), ThrushRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_CREBAIN.get(), CrebainRender::new);

        // Merchants
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HUMAN.get(), m -> new HumanRender(m, new HumanModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DWARF.get(), m -> new DwarfRender(m, new DwarfModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_ELVES.get(), ElvesRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HOBBIT.get(), RenderTTMHobbit::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DESERTDWELLER.get(), m -> new DesertDwellerRender(m, new HumanModel<>(1.0F), 0.5F));

        // Monster
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOBLIN.get(), GoblinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BARROW.get(), m -> new BarrowWightRender(m, new BarrowWightModel<>(1.0F, false), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BRIGAND.get(), m -> new BrigandRender(m, new BrigandModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DEEPCLAW.get(), DeepClawRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_TREEENT.get(), m -> new TreeEntRender(m, new TreeEntModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DUERGAR.get(), m -> new DuergarRender(m, new DuergarModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_FELLSPIRIT.get(), m -> new FellSpiritRender(m, new BarrowWightModel<>(1.0F, false), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SWAMPHAG.get(), SwampHagRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MIRKWOODSPIDER.get(), MirkwoodSpiderRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HARADRIM.get(), m -> new HaradrimRender(m, new HaradrimModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_TROLL.get(), m -> new TrollRender(m, new TrollModel<>(1.0F), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_WARG.get(), WargRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MORDORORC.get(), MordorOrcRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HURON.get(), HuronRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_OATHBREAKER.get(), m -> new OathbreakerRender(m, new BarrowWightModel<>(1.0F, false), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_ROMIEWALKER.get(), m -> new RomieWalkerRender(m, new BrigandModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_URUKHAI.get(), UrukHaiRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_ELEMENTALGOLEM.get(), ElementalGolemRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MINOTAUR.get(), MinotaurRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MIMICCHEST.get(), MimicChestRender::new);

        // Boss
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOBLINKING.get(), GoblinKingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MITHRILGOLEM.get(), MithrilGolemRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MORGULIRONGOLEM.get(), MorgulIronGolemRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_WITCHKING.get(), m -> new WitchKingRender(m, new WitchKingModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SHELOB.get(), ShelobRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BALROG.get(), BalrogRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_WATCHER.get(), WatcherRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GWAHIR.get(), GwahirRender::new);

        // Passive
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_AUROCH.get(), AurochRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MUMAKIL.get(), MumakilRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOAT.get(), GoatRender::new);

        // Special
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SHADOWFAX.get(), ShadowfaxRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOLLUM.get(), GollumRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_NAZGUL.get(), NazgulRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_NAZGULSTEED.get(), NazgulSteedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GREAT_EAGLE.get(), GreatEagleRender::new);

        // Ammo
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_ARROW_GALADHRIM.get(), GaladhrimArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_ARROW_UTUMNO.get(), UtumnoArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), new fellBeastFireballRenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_BOULDER.get(), new boulderRenderFactory());

        // Boats
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.MALLORN_BOAT.get(), TolkienBoatRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.MIRKWOOD_BOAT.get(), TolkienBoatRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.CULUMALDA_BOAT.get(), TolkienBoatRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.LEBETHRON_BOAT.get(), TolkienBoatRender::new);

    }

    private static class boulderRenderFactory implements IRenderFactory<BoulderEntity> {
        @Override
        public EntityRenderer<? super BoulderEntity> createRenderFor(EntityRendererManager manager) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            return new SpriteRenderer<>(manager, itemRenderer);
        }
    }

    private static class fellBeastFireballRenderFactory implements IRenderFactory<FellBeastFireballEntity> {
        @Override
        public EntityRenderer<? super FellBeastFireballEntity> createRenderFor(EntityRendererManager manager) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            return new SpriteRenderer<>(manager, itemRenderer);
        }
    }

    public static void registerPropertyOverride() {
        ItemModelsProperties.register(COIN_POUCH.get(), new ResourceLocation("fullness"), CoinPouchItem::getFullnessPropertyOverride);
        ItemModelsProperties.register(KEY_RING.get(), new ResourceLocation("fullness"), KeyRingItem::getFullnessPropertyOverride);
    }

    private void registerTileRenderers() {
        ClientRegistry.bindTileEntityRenderer(TTMContent.TMFIREPLACE_TILE.get(), RenderFireplaceTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.PIGGYBANK_TILE.get(), RenderPiggyBankTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.BACKPACK_TILE.get(), RenderBackpackTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.PLACARD_TILE.get(), RenderPlacardTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.MALLORN_SIGN_TILE.get(), RenderMallornSignTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.MIRKWOOD_SIGN_TILE.get(), RenderMirkwoodSignTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.CULUMALDA_SIGN_TILE.get(), RenderCulumaldaSignTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.LEBETHRON_SIGN_TILE.get(), RenderLebethronSignTile::new);
        ClientRegistry.bindTileEntityRenderer(MILESTONE_TILE.get(), RenderMilestoneTile::new);
        ClientRegistry.bindTileEntityRenderer(KEY_STONE_TILE.get(), RenderCamoKeyStoneTile::new);
        ClientRegistry.bindTileEntityRenderer(CAMO_SPAWNER_TILE.get(), RenderCamoSpawnerTile::new);
        ClientRegistry.bindTileEntityRenderer(CAMO_CHEST_TILE.get(), RenderCamoChestTile::new);
        ClientRegistry.bindTileEntityRenderer(LOCKABLE_CHEST_TILE.get(), RenderLockableChestTile::new);
        ClientRegistry.bindTileEntityRenderer(LOCKABLE_TREASURE_CHEST_TILE.get(), RenderLockableTreasureChestTile::new);
        ClientRegistry.bindTileEntityRenderer(LOCKABLE_DOUBLE_CHEST_TILE.get(), RenderLockableDoubleChestTile::new);
        ClientRegistry.bindTileEntityRenderer(LOCKABLE_DOUBLE_TREASURE_CHEST_TILE.get(), RenderLockableDoubleTreasureChestTile::new);
    }

    @Override
    public void serverSetup(FMLDedicatedServerSetupEvent event) {
        super.serverSetup(event);
    }

    @Override
    public PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }
}