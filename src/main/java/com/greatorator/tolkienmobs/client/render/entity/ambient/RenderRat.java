package com.greatorator.tolkienmobs.client.render.entity.ambient;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.ambient.ModelRat;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMRat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderRat extends RenderLiving<EntityTMRat> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmrat/tmrat0.png");
    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmrat/tmrat1.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmrat/tmrat2.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmrat/tmrat3.png");

    public static final RenderRat.Factory FACTORY = new RenderRat.Factory();

    public float scale;

    public RenderRat(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelRat(), 0.3F);
        this.scale = scale;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMRat entity) {
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

    public static class Factory implements IRenderFactory<EntityTMRat> {
        @Override
        public Render<? super EntityTMRat> createRenderFor(RenderManager manager) {
            return new RenderRat(manager, 0.3F);
        }
    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityTMRat entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}
