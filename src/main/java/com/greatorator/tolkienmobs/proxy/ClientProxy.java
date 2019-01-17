package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.ammo.RenderBoulder;
import com.greatorator.tolkienmobs.client.render.entity.boss.RenderBalrog;
import com.greatorator.tolkienmobs.client.render.entity.boss.RenderFellBeast;
import com.greatorator.tolkienmobs.client.render.entity.boss.RenderWitchKing;
import com.greatorator.tolkienmobs.client.render.entity.monster.*;
import com.greatorator.tolkienmobs.client.render.entity.passive.*;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelCrebain;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelMumakil;
import com.greatorator.tolkienmobs.entity.ammo.EntityAmmo;
import com.greatorator.tolkienmobs.entity.boss.EntityBalrog;
import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import com.greatorator.tolkienmobs.entity.boss.EntityWitchKing;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.handler.FogHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
        OBJLoader.INSTANCE.addDomain(TolkienMobs.MODID);
        /** Bosses */
        RenderingRegistry.registerEntityRenderingHandler(EntityBalrog.class, RenderBalrog.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFellBeast.class, RenderFellBeast::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityWitchKing.class, RenderWitchKing.FACTORY);

        /** Monsters */
        RenderingRegistry.registerEntityRenderingHandler(EntityHuron.class, RenderHuron.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTreeEnt.class, RenderTreeEnt.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMirkwoodSpider.class, RenderMirkwoodSpider.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, RenderGoblin.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMordorOrc.class, RenderMordorOrc.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityWarg.class, RenderWarg.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(
                EntityMumakil.class,
                RenderHerdAnimal.getRenderFactory(
                        new ModelMumakil(),
                        2.0F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mumakil/mumakil0.png")
                )
        );
        RenderingRegistry.registerEntityRenderingHandler(
                EntityCrebain.class,
                RenderBirds.getRenderFactory(
                        new ModelCrebain(),
                        new ModelCrebain(),
                        0.5F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/crebain.png"),
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/bird_legband.png")
                )
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityTroll.class, RenderTroll.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityUrukHai.class, RenderUrukHai.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBarrowWight.class, RenderBarrowWight.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGollum.class, RenderGollum.FACTORY);

        /** Passive */
        RenderingRegistry.registerEntityRenderingHandler(EntityAuroch.class, RenderAuroch.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHobbit.class, RenderHobbit.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDwarf.class, RenderDwarf.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, RenderHuman.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityElves.class, RenderElves.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGoat.class, RenderGoat.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMFrog.class, RenderTMFrog.FACTORY);

        /** Miscellaneous */
        RenderingRegistry.registerEntityRenderingHandler(EntityAmmo.class, RenderBoulder.FACTORY);
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
    }
}
