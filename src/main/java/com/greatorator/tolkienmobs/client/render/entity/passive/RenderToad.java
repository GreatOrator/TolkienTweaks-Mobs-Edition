package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelToad;
import com.greatorator.tolkienmobs.entity.passive.EntityToad;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderToad extends RenderLiving<EntityToad> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toad/toad_green.png");
    private static final ResourceLocation RED = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toad/toad_red.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toad/toad_black.png");
    private static final ResourceLocation RAINBOW = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toad/toad_rainbow.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toad/toad_yellow.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toad/toad_white.png");
    private static final ResourceLocation MURDERFROG = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toad/murderfrog.png");

    public static final Factory FACTORY = new Factory();

    public float scale;

    public RenderToad(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelToad(), 0.2F);
        this.scale = scale;
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityToad entity) {
        switch (entity.getToadType())
        {
            case 0:
            default:
                return GREEN;
            case 1:
                return RED;
            case 2:
                return BLACK;
            case 3:
                return WHITE;
            case 4:
                return RAINBOW;
            case 5:
                return YELLOW;
            case 99:
                return MURDERFROG;
        }
    }

    public static class Factory implements IRenderFactory<EntityToad> {

        @Override
        public Render<? super EntityToad> createRenderFor(RenderManager manager) {
            return new RenderToad(manager, 0.5F);
        }

    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityToad entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}