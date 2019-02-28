package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelToaddle;
import com.greatorator.tolkienmobs.entity.passive.EntityTMToad;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderToaddle extends RenderLiving<EntityTMToad> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toaddle/toaddle_green.png");
    private static final ResourceLocation RED = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toaddle/toaddle_red.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toaddle/toaddle_black.png");
    private static final ResourceLocation RAINBOW = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toaddle/toaddle_rainbow.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toaddle/toaddle_yellow.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toaddle/toaddle_white.png");
    private static final ResourceLocation MURDERFROG = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/toaddle/murderfrog.png");

    public static final Factory FACTORY = new Factory();

    public float scale;

    public RenderToaddle(RenderManager rendermanagerIn, float scale) {
        super(rendermanagerIn, new ModelToaddle(), 0.2F);
        this.scale = scale;
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMToad entity) {
        switch (entity.getToaddleType())
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

    public static class Factory implements IRenderFactory<EntityTMToad> {

        @Override
        public Render<? super EntityTMToad> createRenderFor(RenderManager manager) {
            return new RenderToaddle(manager, 0.5F);
        }

    }

    /** If you need to apply a GL Scale to your model this is how you do it. */
    @Override
    protected void preRenderCallback(EntityTMToad entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}