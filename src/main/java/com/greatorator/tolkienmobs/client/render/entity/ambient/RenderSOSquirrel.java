package com.greatorator.tolkienmobs.client.render.entity.ambient;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.ambient.ModelSOSquirrel;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMSquirrel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderSOSquirrel extends RenderLiving<EntityTMSquirrel> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/sosquirrel/sosquirrel.png");
    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/sosquirrel/sosquirrel2.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/sosquirrel/sosquirrel3.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/sosquirrel/sosquirrel4.png");

    public static final RenderSOSquirrel.Factory FACTORY = new RenderSOSquirrel.Factory();

    public float scale;

    public RenderSOSquirrel(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelSOSquirrel(), 0.5F);
        this.scale = scale;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMSquirrel entity) {
        switch (entity.getSOSquirrelType()) {
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
            case 99:
                return WHITE;
        }
    }

    public static class Factory implements IRenderFactory<EntityTMSquirrel> {
        @Override
        public Render<? super EntityTMSquirrel> createRenderFor(RenderManager manager) {
            return new RenderSOSquirrel(manager, 0.25F);
        }
    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityTMSquirrel entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}