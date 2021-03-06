package com.greatorator.tolkienmobs.entity.ambient.render;
//import com.greatorator.tolkienmobs.TolkienMobs;
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.util.ResourceLocation;
//

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

/** Borrowed from Twilight Forest */
public class RenderTTMSwarm<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation SWARM_TEXTURE;

    public RenderTTMSwarm(EntityRendererManager manager, M model, float shadowSize, String textureName) {
        super(manager, model, shadowSize);

        SWARM_TEXTURE = new ResourceLocation(textureName);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return SWARM_TEXTURE;
    }
}