package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.BalrogEntity;
import com.greatorator.tolkienmobs.entity.boss.model.BalrogModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BalrogRender extends BaseBossRender<BalrogEntity> {
    public BalrogRender(EntityRendererProvider.Context context) {
        super(context, new BalrogModel());
    }

    @Override
    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    @Override
    protected String getArmPartName() {
        return "bipedRightArmLower";
    }

    public void render(BalrogEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(BalrogEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/balrog.png");
    }
}
