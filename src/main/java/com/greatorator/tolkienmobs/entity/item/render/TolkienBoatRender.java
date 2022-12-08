package com.greatorator.tolkienmobs.entity.item.render;

import com.google.common.collect.ImmutableMap;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.entity.item.model.TolkienBoatModel;
import com.greatorator.tolkienmobs.handler.BoatLayerHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Map;
import java.util.stream.Stream;

public class TolkienBoatRender extends EntityRenderer<TolkienBoatEntity> {
    private final Map<TolkienBoatEntity.Type, Pair<ResourceLocation, TolkienBoatModel>> boatResources;

    public TolkienBoatRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(TolkienBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> {
            return type;
        }, (type) -> {
            return Pair.of(new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/" + type.getName() + ".png"), new TolkienBoatModel(rendererManager.bakeLayer(BoatLayerHandler.createBoatModelName(type))));
        }));
    }

    @Override
    public void render(TolkienBoatEntity tolkienBoatEntity, float vector, float angle, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int type) {
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - vector));
        float f = (float)tolkienBoatEntity.getHurtTime() - angle;
        float f1 = tolkienBoatEntity.getDamage() - angle;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)tolkienBoatEntity.getHurtDir()));
        }

        float f2 = tolkienBoatEntity.getBubbleAngle(angle);
        if (!Mth.equal(f2, 0.0F)) {
            matrixStack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), tolkienBoatEntity.getBubbleAngle(angle), true));
        }

        Pair<ResourceLocation, TolkienBoatModel> pair = getModelWithLocation(tolkienBoatEntity);
        ResourceLocation resourcelocation = pair.getFirst();
        TolkienBoatModel boatmodel = pair.getSecond();
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        boatmodel.setupAnim(tolkienBoatEntity, angle, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer ivertexbuilder = renderTypeBuffer.getBuffer(boatmodel.renderType(this.getTextureLocation(tolkienBoatEntity)));
        boatmodel.renderToBuffer(matrixStack, ivertexbuilder, type, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!tolkienBoatEntity.isUnderWater()) {
            VertexConsumer ivertexbuilder1 = renderTypeBuffer.getBuffer(RenderType.waterMask());
            boatmodel.waterPatch().render(matrixStack, ivertexbuilder1, type, OverlayTexture.NO_OVERLAY);
        }

        matrixStack.popPose();
        super.render(tolkienBoatEntity, vector, angle, matrixStack, renderTypeBuffer, type);
    }

    @Override
    public ResourceLocation getTextureLocation(TolkienBoatEntity tolkienBoatEntity) {
        return getModelWithLocation(tolkienBoatEntity).getFirst();
    }

    public Pair<ResourceLocation, TolkienBoatModel> getModelWithLocation(TolkienBoatEntity boat) { return this.boatResources.get(boat.getWoodType()); }
}