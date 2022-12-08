package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.monster.render.BaseMonsterRender;
import com.greatorator.tolkienmobs.entity.special.GollumEntity;
import com.greatorator.tolkienmobs.entity.special.model.GollumModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GollumRender extends BaseMonsterRender<GollumEntity> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/gollum.png");

    public GollumRender(EntityRendererProvider.Context context) {
        super(context, new GollumModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    @Override
    protected String getArmPartName() {
        return "bipedRightArm";
    }

    @Override
    public ResourceLocation getTextureLocation(GollumEntity entity) {
        return mobTexture;
    }
}