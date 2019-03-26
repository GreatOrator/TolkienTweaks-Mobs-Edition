package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelBarrowWight;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMFellSpirit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderFellSpirit extends RenderLiving<EntityTMFellSpirit> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/fellspirit/fellspirit1.png");
    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/fellspirit/fellspirit2.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/fellspirit/fellspirit3.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/fellspirit/fellspirit4.png");

    public static final RenderFellSpirit.Factory FACTORY = new RenderFellSpirit.Factory();

    public RenderFellSpirit(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBarrowWight(true), 0.5F);
        this.addLayer(new LayerArmed(this, 0.0625f, 0.02F, -0.6F,1F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMFellSpirit entity) {
        switch (entity.getMobType()) {
            case 0:
            default:
                return GREEN;
            case 1:
                return GREEN;
            case 2:
                return BLACK;
            case 3:
                return BLUE;
            case 4:
                return WHITE;
            case 5:
                return WHITE;
        }
    }

    public static class Factory implements IRenderFactory<EntityTMFellSpirit> {
        @Override
        public Render<? super EntityTMFellSpirit> createRenderFor(RenderManager manager) {
            return new RenderFellSpirit(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.1875F, 0.0F);
    }
}