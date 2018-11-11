package com.greatorator.tolkienmobs.client.render.entity;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.ModelGoblin;
import com.greatorator.tolkienmobs.entity.EntityGoblin;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderGoblin extends RenderLiving<EntityGoblin> {
    private static final ResourceLocation mobTexture = new ResourceLocation[3];
    static {
        for (int i = 0; i < 3; i++) {
            textures[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goblin" + i + ".png");
        }
    }

    public static final RenderGoblin.Factory FACTORY = new RenderGoblin.Factory();

    public RenderGoblin(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGoblin(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull Entity entity) {
        int index = ((EntityGoblin) entity).getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityGoblin> {

        @Override
        public Render<? super EntityGoblin> createRenderFor(RenderManager manager) {
            return new RenderGoblin(manager);
        }

    }
}
