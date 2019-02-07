package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelBarrowWight;
import com.greatorator.tolkienmobs.entity.monster.EntityBarrowWight;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderBarrowWight extends RenderLiving<EntityBarrowWight> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/barrowwight/barrowwight" + i + ".png");
        }
    }

    public static final RenderBarrowWight.Factory FACTORY = new RenderBarrowWight.Factory();

    public RenderBarrowWight(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBarrowWight(0, true), 0.5F);
        this.addLayer(new LayerArmed(this,16.0F, 0.02F, -(0.6F),0.0F,0.0F,0.0F, 0.05F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBarrowWight entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityBarrowWight> {
        @Override
        public Render<? super EntityBarrowWight> createRenderFor(RenderManager manager) {
            return new RenderBarrowWight(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.1875F, 0.0F);
    }
}