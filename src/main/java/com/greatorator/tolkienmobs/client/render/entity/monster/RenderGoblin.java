package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelGoblin;
import com.greatorator.tolkienmobs.entity.monster.EntityGoblin;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderGoblin extends RenderLiving<EntityGoblin> {
    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goblin/goblin1.png");
    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goblin/goblin2.png");
    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goblin/goblin3.png");
    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goblin/goblin4.png");

    public static final RenderGoblin.Factory FACTORY = new RenderGoblin.Factory();

    public RenderGoblin(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGoblin(), 0.5F);
        this.addLayer(new LayerArmed(this,0.0825F, 0.025F, -0.325F,1F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityGoblin entity) {
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

    public static class Factory implements IRenderFactory<EntityGoblin> {
        @Override
        public Render<? super EntityGoblin> createRenderFor(RenderManager manager) {
            return new RenderGoblin(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
}