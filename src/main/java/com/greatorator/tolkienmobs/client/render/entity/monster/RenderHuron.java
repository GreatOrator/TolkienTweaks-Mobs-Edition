package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMHuron;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelHuron;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderHuron extends RenderLiving<EntityTMHuron> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/huron.png");

    public static final Factory FACTORY = new Factory();

    public RenderHuron(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHuron(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMHuron entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTMHuron> {
        @Override
        public Render<? super EntityTMHuron> createRenderFor(RenderManager manager) {
            return new RenderHuron(manager);
        }
    }

}