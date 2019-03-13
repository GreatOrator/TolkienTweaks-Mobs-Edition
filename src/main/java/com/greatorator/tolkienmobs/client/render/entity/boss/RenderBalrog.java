package com.greatorator.tolkienmobs.client.render.entity.boss;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.boss.ModelBalrog;
import com.greatorator.tolkienmobs.entity.boss.EntityTMBalrog;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderBalrog extends RenderLiving<EntityTMBalrog> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/balrog.png");

    public static final RenderBalrog.Factory FACTORY = new RenderBalrog.Factory();

    public float scale;

    public RenderBalrog(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelBalrog(), 2.5F);
        this.scale = scale;
        this.addLayer(new LayerArmed(this, 0.23F, 0.2F, -1.35F, 1.4F));
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMBalrog entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTMBalrog> {
        @Override
        public Render<? super EntityTMBalrog> createRenderFor(RenderManager manager) {
            return new RenderBalrog(manager, 2.0F);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityTMBalrog entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}