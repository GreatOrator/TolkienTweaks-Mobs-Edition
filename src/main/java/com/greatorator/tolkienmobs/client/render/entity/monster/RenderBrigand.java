package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelBrigand;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMBrigand;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderBrigand extends RenderLiving<EntityTMBrigand> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand0.png");
    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand1.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand2.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand3.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand4.png");
    private static final ResourceLocation PINK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand5.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand6.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand7.png");
    private static final ResourceLocation BROWN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand8.png");
    private static final ResourceLocation RED = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmbrigand/brigand9.png");

    public static final RenderBrigand.Factory FACTORY = new RenderBrigand.Factory();

    public float scale;

    public RenderBrigand(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBrigand(), 0.5F);
        this.addLayer(new LayerArmed(this, 0.0825F, 0.0825F, -0.625F, 1F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMBrigand entity) {
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

    public static class Factory implements IRenderFactory<EntityTMBrigand> {
        @Override
        public Render<? super EntityTMBrigand> createRenderFor(RenderManager manager) {
            return new RenderBrigand(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
}