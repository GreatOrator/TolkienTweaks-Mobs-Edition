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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderTroll extends RenderLiving<EntityTroll> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/troll/cave_troll1.png");
    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/troll/cave_troll2.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/troll/cave_troll3.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/troll/cave_troll4.png");

    public static final RenderTroll.Factory FACTORY = new RenderTroll.Factory();

    public float scale;

    public RenderTroll(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelTroll(), 0.5F * scale);
        this.scale = scale;
        this.addLayer(new LayerArmed(this,0.25F, 0.0F, -1.2F,1.5F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityTroll entity) {
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

    public static class Factory implements IRenderFactory<EntityTroll> {
        @Override
        public Render<? super EntityTroll> createRenderFor(RenderManager manager) {
            return new RenderTroll(manager, 1.8F);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityTroll entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}