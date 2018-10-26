package com.greatorator.tolkienmobs.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class EntityTreeEntRender extends RenderLiving<EntityTreeEnt> {

    private ResourceLocation mobTexture = new ResourceLocation("tolkienmobs:textures/entity/treeent.png");

    public static final Factory FACTORY = new Factory();

    public EntityTreeEntRender(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTreeEnt entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTreeEnt> {

        @Override
        public Render<? super EntityTreeEnt> createRenderFor(RenderManager manager) {
            return new EntityTreeEntRender(manager);
        }

    }

}
