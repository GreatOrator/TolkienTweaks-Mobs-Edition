package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelTMHaradrim;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMHaradrim;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderTMHaradrim extends RenderLiving<EntityTMHaradrim> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim0.png");
    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim1.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim2.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim3.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim4.png");
    private static final ResourceLocation PINK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim5.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim6.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim7.png");
    private static final ResourceLocation BROWN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim8.png");
    private static final ResourceLocation RED = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmharadrim/haradrim9.png");

    public static final RenderTMHaradrim.Factory FACTORY = new RenderTMHaradrim.Factory();

    public float scale;

    public RenderTMHaradrim(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelTMHaradrim(), 0.5F);
        this.addLayer(new LayerArmed(this, 0.0825F, 0.0825F, -0.625F, 1F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMHaradrim entity) {
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
                return YELLOW;
            case 6:
                return PINK;
            case 7:
                return PURPLE;
            case 8:
                return ORANGE;
            case 9:
                return BROWN;
            case 10:
                return RED;
            case 11:
                return RED;
        }
    }

    public static class Factory implements IRenderFactory<EntityTMHaradrim> {
        @Override
        public Render<? super EntityTMHaradrim> createRenderFor(RenderManager manager) {
            return new RenderTMHaradrim(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
}