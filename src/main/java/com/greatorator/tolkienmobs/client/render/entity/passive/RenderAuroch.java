package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelAuroch;
import com.greatorator.tolkienmobs.entity.passive.EntityAuroch;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderAuroch extends RenderLiving<EntityAuroch> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/auroch.png");

    public static final Factory FACTORY = new Factory();

    public RenderAuroch(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAuroch(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityAuroch entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityAuroch> {

        @Override
        public Render<? super EntityAuroch> createRenderFor(RenderManager manager) {
            return new RenderAuroch(manager);
        }

    }

}