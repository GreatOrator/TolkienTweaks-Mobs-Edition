package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelGoat;
import com.greatorator.tolkienmobs.entity.passive.EntityTMGoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderGoat extends RenderLiving<EntityTMGoat> {

    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goat/goat" + i + ".png");
        }
    }

    public static final RenderGoat.Factory FACTORY = new RenderGoat.Factory();

    public RenderGoat(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGoat(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTMGoat entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityTMGoat> {
        @Override
        public Render<? super EntityTMGoat> createRenderFor(RenderManager manager) {
            return new RenderGoat(manager);
        }

    }
}