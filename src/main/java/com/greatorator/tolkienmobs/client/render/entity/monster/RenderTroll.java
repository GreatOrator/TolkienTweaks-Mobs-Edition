package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelTroll;
import com.greatorator.tolkienmobs.entity.monster.EntityTroll;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderTroll extends RenderLiving<EntityTroll> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/troll/cave_troll" + i + ".png");
        }
    }

    public static final RenderTroll.Factory FACTORY = new RenderTroll.Factory();

    public float scale;

    public RenderTroll(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelTroll(), 0.5F * scale);
        this.scale = scale;
        this.addLayer(new LayerArmed(this,0.25F, 0.0F, -1.2F,1.5F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTroll entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityTroll> {
        @Override
        public Render<? super EntityTroll> createRenderFor(RenderManager manager) {
            return new RenderTroll(manager, 1.8F);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    //If you need to apply a GL Scale to your model this is how you do it.
    @Override
    protected void preRenderCallback(EntityTroll entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}