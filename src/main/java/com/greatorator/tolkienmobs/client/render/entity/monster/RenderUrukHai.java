package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelUrukHai;
import com.greatorator.tolkienmobs.entity.monster.EntityUrukHai;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderUrukHai extends RenderLiving<EntityUrukHai> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/urukhai/urukhai" + i + ".png");
        }
    }

    public static final RenderUrukHai.Factory FACTORY = new RenderUrukHai.Factory();

    public float scale;

    public RenderUrukHai(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelUrukHai(), 0.5F);
        this.scale = scale;
        this.addLayer(new LayerArmed(this, -0.03F, 0.105F, -0.545F, 1.2F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityUrukHai entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityUrukHai> {
        @Override
        public Render<? super EntityUrukHai> createRenderFor(RenderManager manager) {
            return new RenderUrukHai(manager, 1.2F);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityUrukHai entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}