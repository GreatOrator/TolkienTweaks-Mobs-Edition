package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.MithrilGolemEntity;
import com.greatorator.tolkienmobs.entity.monster.model.ElementalGolemModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MithrilGolemRender extends MobRenderer<MithrilGolemEntity, ElementalGolemModel<MithrilGolemEntity>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/elementalgolem/elemental_golem_mithril.png");

    public MithrilGolemRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ElementalGolemModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(MithrilGolemEntity entity) {
        return mobTexture;
    }

    @Override
    protected void setupRotations(MithrilGolemEntity entity, MatrixStack matrixStack, float f1, float f2, float f3) {
        super.setupRotations(entity, matrixStack, f1, f2, f3);
        if (!((double)entity.animationSpeed < 0.01D)) {
            float lvt_6_1_ = 13.0F;
            float lvt_7_1_ = entity.animationPosition - entity.animationSpeed * (1.0F - f3) + 6.0F;
            float lvt_8_1_ = (Math.abs(lvt_7_1_ % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(6.5F * lvt_8_1_));
        }
    }
}
