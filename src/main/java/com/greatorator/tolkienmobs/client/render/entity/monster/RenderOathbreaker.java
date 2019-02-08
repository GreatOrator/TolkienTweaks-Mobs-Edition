package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerGhost;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelBarrowWight;
import com.greatorator.tolkienmobs.entity.monster.EntityOathbreaker;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderOathbreaker extends RenderLiving<EntityOathbreaker> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/oathbreaker/oathbreaker" + i + ".png");
        }
    }

    public static final RenderOathbreaker.Factory FACTORY = new RenderOathbreaker.Factory();

    public RenderOathbreaker(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBarrowWight(64, true), 0.5F);
        this.addLayer(new LayerGhost(this, new ModelBarrowWight(0, true)));
        this.addLayer(new LayerArmed(this, 0.0625f, 0.02F, -0.6F,1F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityOathbreaker entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityOathbreaker> {
        @Override
        public Render<? super EntityOathbreaker> createRenderFor(RenderManager manager) {
            return new RenderOathbreaker(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.1875F, 0.0F);
    }
}