package com.greatorator.tolkienmobs.entity.ambient.render;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

/** Borrowed from Twilight Forest */
public class SwarmRender<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation SWARM_TEXTURE;

    public SwarmRender(EntityRendererProvider.Context manager, M model, float shadowSize, String textureName) {
        super(manager, model, shadowSize);

        SWARM_TEXTURE = new ResourceLocation(textureName);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return SWARM_TEXTURE;
    }}