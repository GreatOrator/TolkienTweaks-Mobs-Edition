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

    public RenderTroll(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelTroll(), 1.9F);
        this.addLayer(new LayerArmed(this,16.0F, 0.0F, -(1.70F),1.0F,2.5F,2.5F,0.1825F));
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
            return new RenderTroll(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
}