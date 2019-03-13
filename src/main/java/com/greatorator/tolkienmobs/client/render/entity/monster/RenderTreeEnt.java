package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelTreeEnt;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMTreeEnt;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderTreeEnt extends RenderLiving<EntityTMTreeEnt> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/treeent/treeent" + i + ".png");
        }
    }

    public static final Factory FACTORY = new Factory();

    public RenderTreeEnt(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelTreeEnt(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTMTreeEnt entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityTMTreeEnt> {
        @Override
        public Render<? super EntityTMTreeEnt> createRenderFor(RenderManager manager) {
            return new RenderTreeEnt(manager);
        }
    }
}