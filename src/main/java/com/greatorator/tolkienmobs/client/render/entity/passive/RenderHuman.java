package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelHuman;
import com.greatorator.tolkienmobs.entity.passive.EntityHuman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderHuman extends RenderLiving<EntityHuman> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[16];
    static {
        for (int i = 0; i < 16; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/human/human" + i + ".png");
        }
    }

    public static final RenderHuman.Factory FACTORY = new RenderHuman.Factory();

    public RenderHuman(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHuman(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHuman entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityHuman> {
        @Override
        public Render<? super EntityHuman> createRenderFor(RenderManager manager) {
            return new RenderHuman(manager);
        }
    }
}
