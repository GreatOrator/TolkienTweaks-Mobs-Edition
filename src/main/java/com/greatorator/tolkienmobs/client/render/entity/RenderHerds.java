package com.greatorator.tolkienmobs.client.render.entity;

import com.greatorator.tolkienmobs.entity.EntityHerds;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/** Borrowed from Jabelar https://github.com/jabelar */
@SuppressWarnings("rawtypes")
public class RenderHerds extends RenderLiving {
    protected ResourceLocation herdAnimalTexture;

    public RenderHerds(
            RenderManager parRenderManager,
            ModelBase par1ModelBase,
            float parShadowSize,
            ResourceLocation parNormalTexture
    )
    {
        super(parRenderManager, par1ModelBase, parShadowSize);
        herdAnimalTexture = parNormalTexture;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        preRenderCallbackHerdAnimal((EntityHerds) entity, f);
    }

    protected void preRenderCallbackHerdAnimal(EntityHerds entity, float f)
    {
    }

    protected ResourceLocation getEntityTexture(EntityHerds par1EntityHerdAnimal)
    {
        return herdAnimalTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityHerds)par1Entity);
    }

    public static IRenderFactory getRenderFactory(
            ModelBase parModelBase1,
            float parShadowSize,
            ResourceLocation parNormalTexture
    )
    {
        return new RenderFactory(
                parModelBase1,
                parShadowSize,
                parNormalTexture
        );
    }

    private static class RenderFactory implements IRenderFactory
    {
        ModelBase model1;
        float shadowSize;
        ResourceLocation normalTexture;

        public RenderFactory(
                ModelBase parModelBase1,
                float parShadowSize,
                ResourceLocation parNormalTexture)
        {
            model1 = parModelBase1;
            shadowSize = parShadowSize;
            normalTexture = parNormalTexture;
        }

        @Override
        public Render createRenderFor(RenderManager manager)
        {
            return new RenderHerds(
                    manager,
                    model1,
                    shadowSize,
                    normalTexture
            );
        }
    }
}