package com.greatorator.tolkienmobs.client.render.entity.boss;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.boss.ModelWitchKing;
import com.greatorator.tolkienmobs.entity.boss.EntityWitchKing;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderWitchKing extends RenderLiving<EntityWitchKing> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/witchking.png");

    public static final Factory FACTORY = new Factory();


    public RenderWitchKing(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelWitchKing(), 1.0F);
        this.addLayer(new LayerArmed(this, 0.125f, 0.25F, 0.05F,1.0F));
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityWitchKing entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityWitchKing> {
        @Override
        public Render<? super EntityWitchKing> createRenderFor(RenderManager manager) {
            return new RenderWitchKing(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.18F, 0.0F);
    }
}