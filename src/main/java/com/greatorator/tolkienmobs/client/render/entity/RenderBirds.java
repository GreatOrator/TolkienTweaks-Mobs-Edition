package com.greatorator.tolkienmobs.client.render.entity;

import com.greatorator.tolkienmobs.client.render.entity.layers.LayerLegBandBirds;
import com.greatorator.tolkienmobs.entity.EntityBirds;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@SuppressWarnings("rawtypes")
public class RenderBirds extends RenderLiving {
    protected ResourceLocation birdOfPreyTexture;

    @SuppressWarnings("unchecked")
    public RenderBirds(
            RenderManager parRenderManager,
            ModelBase parModelBase1,
            ModelBase parModelBase2,
            float parShadowSize,
            ResourceLocation parNormalTexture,
            ResourceLocation parLegBandTexture
    )
    {
        super(parRenderManager, parModelBase1, parShadowSize);
        birdOfPreyTexture = parNormalTexture;
        addLayer(new LayerLegBandBirds(this, parLegBandTexture));
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return getEntityTexture((EntityBirds)par1Entity);
    }

    protected ResourceLocation getEntityTexture(EntityBirds parEntityBirdOfPrey)
    {
        return birdOfPreyTexture;
    }

    public static IRenderFactory getRenderFactory(
            ModelBase parModelBase1,
            ModelBase parModelBase2,
            float parShadowSize,
            ResourceLocation parNormalTexture,
            ResourceLocation parLegBandTexture
    )
    {
        return new RenderFactory(
                parModelBase1,
                parModelBase2,
                parShadowSize,
                parNormalTexture,
                parLegBandTexture
        );
    }

    private static class RenderFactory implements IRenderFactory
    {
        ModelBase model1;
        ModelBase model2;
        float shadowSize;
        ResourceLocation normalTexture;
        ResourceLocation legBandTexture;

        public RenderFactory(
                ModelBase parModelBase1,
                ModelBase parModelBase2,
                float parShadowSize,
                ResourceLocation parNormalTexture,
                ResourceLocation parLegBandTexture)
        {
            model1 = parModelBase1;
            model2 = parModelBase2;
            shadowSize = parShadowSize;
            normalTexture = parNormalTexture;
            legBandTexture = parLegBandTexture;
        }

        @Override
        public Render createRenderFor(RenderManager manager)
        {
            return new RenderBirds(
                    manager,
                    model1,
                    model2,
                    shadowSize,
                    normalTexture,
                    legBandTexture
            );
        }
    }
}