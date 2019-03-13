package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelWarg;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMWarg;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderWarg extends RenderLiving<EntityTMWarg> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/warg/warg" + i + ".png");
        }
    }

    public static final RenderWarg.Factory FACTORY = new RenderWarg.Factory();

    public RenderWarg(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelWarg(), 0.5F);
    }

    protected float handleRotationFloat(EntityTMWarg livingBase, float partialTicks)
    {
        return livingBase.getTailRotation();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTMWarg entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityTMWarg> {

        @Override
        public Render<? super EntityTMWarg> createRenderFor(RenderManager manager) {
            return new RenderWarg(manager);
        }

    }
}