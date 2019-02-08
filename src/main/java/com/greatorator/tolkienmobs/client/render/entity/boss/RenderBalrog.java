package com.greatorator.tolkienmobs.client.render.entity.boss;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.boss.ModelBalrog;
import com.greatorator.tolkienmobs.entity.boss.EntityBalrog;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderBalrog extends RenderLiving<EntityBalrog> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/balrog.png");

    public static final RenderBalrog.Factory FACTORY = new RenderBalrog.Factory();

    public RenderBalrog(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBalrog(), 2.5F);
        this.addLayer(new LayerArmed(this, 0.125f, 1.2F, 0.75F,1.5F));
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityBalrog entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityBalrog> {
        @Override
        public Render<? super EntityBalrog> createRenderFor(RenderManager manager) {
            return new RenderBalrog(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.1875F, 0.5F);
    }
}