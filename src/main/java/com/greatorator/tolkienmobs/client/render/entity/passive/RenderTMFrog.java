package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelTMFrog;
import com.greatorator.tolkienmobs.entity.passive.EntityToad;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderTMFrog extends RenderLiving<EntityToad> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmfrog.png");

    public static final Factory FACTORY = new Factory();

    public RenderTMFrog(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelTMFrog(), 0.3F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityToad entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityToad> {

        @Override
        public Render<? super EntityToad> createRenderFor(RenderManager manager) {
            return new RenderTMFrog(manager);
        }

    }
}