package com.greatorator.tolkienmobs.client.render.entity;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.ModelWarg;
import com.greatorator.tolkienmobs.entity.EntityWarg;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderWarg extends RenderLiving<EntityWarg> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[4];
    static {
        for (int i = 0; i < 4; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/warg/warg" + i + ".png");
        }
    }

    public static final RenderWarg.Factory FACTORY = new RenderWarg.Factory();

    public RenderWarg(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelWarg(), 0.5F);
    }

    protected float handleRotationFloat(EntityWarg livingBase, float partialTicks)
    {
        return livingBase.getTailRotation();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityWarg entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityWarg> {

        @Override
        public Render<? super EntityWarg> createRenderFor(RenderManager manager) {
            return new RenderWarg(manager);
        }

    }
}