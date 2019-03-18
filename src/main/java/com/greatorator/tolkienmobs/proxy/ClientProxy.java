package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.TTMClientEvents;
import com.greatorator.tolkienmobs.client.render.entity.RenderTMBirds;
import com.greatorator.tolkienmobs.client.render.entity.RenderTMGeneric;
import com.greatorator.tolkienmobs.client.render.entity.RenderTMHerds;
import com.greatorator.tolkienmobs.client.render.entity.ambient.RenderRat;
import com.greatorator.tolkienmobs.client.render.entity.ambient.RenderSOSquirrel;
import com.greatorator.tolkienmobs.client.render.entity.ambient.RenderToaddle;
import com.greatorator.tolkienmobs.client.render.entity.ammo.RenderBoulder;
import com.greatorator.tolkienmobs.client.render.entity.ammo.RenderFellBeastFireball;
import com.greatorator.tolkienmobs.client.render.entity.boss.RenderBalrog;
import com.greatorator.tolkienmobs.client.render.entity.boss.RenderFellBeast;
import com.greatorator.tolkienmobs.client.render.entity.boss.RenderWitchKing;
import com.greatorator.tolkienmobs.client.render.entity.monster.*;
import com.greatorator.tolkienmobs.client.render.entity.passive.*;
import com.greatorator.tolkienmobs.client.render.entity.special.RenderGollum;
import com.greatorator.tolkienmobs.client.render.entity.special.RenderNazgul;
import com.greatorator.tolkienmobs.client.render.model.ambient.ModelCrebain;
import com.greatorator.tolkienmobs.client.render.model.ambient.ModelTMMidgeFly;
import com.greatorator.tolkienmobs.client.render.model.ambient.ModelThrush;
import com.greatorator.tolkienmobs.client.render.model.boss.ModelTMGwaihir;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelAuroch;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelMumakil;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.boss.EntityTMBalrog;
import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
import com.greatorator.tolkienmobs.entity.boss.EntityTMGwaihir;
import com.greatorator.tolkienmobs.entity.boss.EntityTMWitchKing;
import com.greatorator.tolkienmobs.entity.hostile.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.entity.special.EntityTMGollum;
import com.greatorator.tolkienmobs.entity.special.EntityTMGreatEagle;
import com.greatorator.tolkienmobs.entity.special.EntityTMNazgul;
import com.greatorator.tolkienmobs.handler.FogHandler;
import com.greatorator.tolkienmobs.handler.TTMExtraHearts;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    public void registerModel(Item item, int metadata)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new TTMClientEvents());

        OBJLoader.INSTANCE.addDomain(TolkienMobs.MODID);
        /* Bosses */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMBalrog.class, RenderBalrog.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMFellBeast.class, RenderFellBeast::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMWitchKing.class, RenderWitchKing.FACTORY);

        /* Monsters */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMHuron.class, RenderHuron.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMOathbreaker.class, RenderOathbreaker.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMTreeEnt.class, RenderTreeEnt.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMirkwoodSpider.class, RenderMirkwoodSpider.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMGoblin.class, RenderGoblin.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMordorOrc.class, RenderMordorOrc.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMWarg.class, RenderWarg.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMTroll.class, RenderTroll.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMUrukHai.class, RenderUrukHai.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMBarrowWight.class, RenderBarrowWight.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMimicChest.class, RenderMimicChest.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMinotaur.class, RenderMinotaur.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMBrigand.class, RenderBrigand.FACTORY);

        /* Special */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMGollum.class, RenderGollum.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMNazgul.class, RenderNazgul.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMGreatEagle.class,
                RenderTMBirds.getRenderFactory(
                        new ModelTMGwaihir(),
                        new ModelTMGwaihir(),
                        0.5F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/greateagle.png"),
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/greateagle.png")
                )
        );

        /* Passive */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMHobbit.class, RenderHobbit.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMDwarf.class, RenderDwarf.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMHuman.class, RenderHuman.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMElves.class, RenderElves.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMGoat.class, RenderGoat.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMMumakil.class,
                RenderTMHerds.getRenderFactory(
                        new ModelMumakil(),
                        2.0F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mumakil.png")
                )
        );
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMAuroch.class,
                RenderTMHerds.getRenderFactory(
                        new ModelAuroch(),
                        0.5F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/auroch.png")
                )
        );
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMGwaihir.class,
                RenderTMBirds.getRenderFactory(
                        new ModelTMGwaihir(),
                        new ModelTMGwaihir(),
                        0.5F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/greateagle.png"),
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/greateagle.png")
                )
        );

        /* Ambient */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMSquirrel.class, RenderSOSquirrel.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMToad.class, RenderToaddle.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMRat.class, RenderRat.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMidgeFly.class, m -> new RenderTMGeneric<>(m, new ModelTMMidgeFly(), 0.0F, "midgeflies.png"));
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMCrebain.class,
                RenderTMBirds.getRenderFactory(
                        new ModelCrebain(),
                        new ModelCrebain(),
                        0.5F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/crebain.png"),
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/bird_legband.png")
                )
        );
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMThrush.class,
                RenderTMBirds.getRenderFactory(
                        new ModelThrush(),
                        new ModelThrush(),
                        0.5F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/thrush.png"),
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/bird_legband.png")
                )
        );

        /* Miscellaneous */
        RenderingRegistry.registerEntityRenderingHandler(EntityBoulder.class, RenderBoulder.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFellBeastFireball.class, RenderFellBeastFireball.FACTORY);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(new FogHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        if (!Loader.isModLoaded("mantle")) {
            MinecraftForge.EVENT_BUS.register(new TTMExtraHearts());
        }
    }
}