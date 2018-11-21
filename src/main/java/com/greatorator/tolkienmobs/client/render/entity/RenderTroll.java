package com.greatorator.tolkienmobs.client.render.entity;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.ModelTroll;
import com.greatorator.tolkienmobs.entity.EntityTroll;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderTroll extends RenderLiving<EntityTroll> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[4];
    static {
        for (int i = 0; i < 4; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/troll/cave_troll" + i + ".png");
        }
    }

    public static final RenderTroll.Factory FACTORY = new RenderTroll.Factory();

    public RenderTroll(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelTroll(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTroll entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    public static class Factory implements IRenderFactory<EntityTroll> {

        @Override
        public Render<? super EntityTroll> createRenderFor(RenderManager manager) {
            return new RenderTroll(manager);
        }

    }
}
