package com.greatorator.tolkienmobs.client.render.entity.special;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.special.ModelGollum;
import com.greatorator.tolkienmobs.entity.special.EntityTMGollum;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderGollum extends RenderLiving<EntityTMGollum> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/gollum.png");

    public static final Factory FACTORY = new Factory();

    public RenderGollum(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGollum(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMGollum entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTMGollum> {

        @Override
        public Render<? super EntityTMGollum> createRenderFor(RenderManager manager) {
            return new RenderGollum(manager);
        }

    }

}