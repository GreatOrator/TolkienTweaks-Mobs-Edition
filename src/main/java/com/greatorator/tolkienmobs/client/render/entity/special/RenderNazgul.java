package com.greatorator.tolkienmobs.client.render.entity.special;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.boss.ModelWitchKing;
import com.greatorator.tolkienmobs.entity.special.EntityNazgul;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderNazgul extends RenderLiving<EntityNazgul> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/nazgul.png");

    public static final Factory FACTORY = new Factory();

    public float scale;


    public RenderNazgul(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelWitchKing(), 1.0F);
        this.scale = scale;
        this.addLayer(new LayerArmed(this, 0.125F, 0.25F, -0.625F, 1.5F));
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityNazgul entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityNazgul> {

        @Override
        public Render<? super EntityNazgul> createRenderFor(RenderManager manager) {
            return new RenderNazgul(manager, 1.5F);
        }

    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityNazgul entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}