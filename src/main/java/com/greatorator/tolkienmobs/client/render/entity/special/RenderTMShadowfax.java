package com.greatorator.tolkienmobs.client.render.entity.special;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.special.EntityTMShadowfax;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderTMShadowfax extends RenderLiving<EntityTMShadowfax>
{
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/horse_shadowfax.png");

    public static final RenderTMShadowfax.Factory FACTORY = new RenderTMShadowfax.Factory();

    public RenderTMShadowfax(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelHorse(), 0.75F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMShadowfax entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTMShadowfax> {
        @Override
        public Render<? super EntityTMShadowfax> createRenderFor(RenderManager manager) {
            return new RenderTMShadowfax(manager);
        }
    }
}