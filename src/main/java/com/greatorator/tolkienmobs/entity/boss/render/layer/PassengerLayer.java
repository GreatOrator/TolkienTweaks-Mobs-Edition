package com.greatorator.tolkienmobs.entity.boss.render.layer;

import com.greatorator.tolkienmobs.entity.boss.FellBeastEntity;
import com.greatorator.tolkienmobs.event.client.ClientEvents;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class PassengerLayer<T extends FellBeastEntity> extends GeoLayerRenderer<T> {
    protected final GeoModelProvider provider;
    protected GeoModel model;

    public PassengerLayer(IGeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
        this.provider = this.entityRenderer.getGeoModelProvider();
    }

    // The "real" player is hidden in ClientEvents#hidePlayerWhenOnDragon
    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T dragon, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (dragon.isBaby()) return;
        matrixStackIn.pushPose();
        model = provider.getModel(provider.getModelLocation(dragon));

        if (!dragon.getPassengers().isEmpty()) {
            int passengerIndex = 1;
            for (Entity passenger : dragon.getPassengers()) {
                ClientEvents.dragonRiders.remove(passenger.getUUID());
                float riderRot = passenger.yRotO + (passenger.getYRot() - passenger.yRotO) * partialTicks;
                translateToBody(matrixStackIn, model, passengerIndex); // TODO maybe make this only activate on needed frames?
                matrixStackIn.translate(0.0, -0.5, 0.0);
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(new Quaternion(Vector3f.YP, riderRot + 180, true));
                renderEntity(passenger, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                matrixStackIn.popPose();
                passengerIndex++;
                ClientEvents.dragonRiders.add(passenger.getUUID());
            }
        }
        matrixStackIn.popPose();
    }

    protected void translateToBody(PoseStack stack, GeoModel model, int passengerIndex) {
        if (!model.getBone("rider" + passengerIndex).isPresent()) {
            throw new ReportedException(CrashReport.forThrowable(new Throwable(), "Dragon should have a bone named 'rider" + passengerIndex + "' to have a rider layer!"));
        }
        GeoBone bone = model.getBone("rider" + passengerIndex).get();
        stack.translate(bone.getPositionX() * 00.0625F, bone.getPositionY() * 00.0625F, bone.getPositionZ() * 00.0625F);
        stack.mulPoseMatrix(bone.getModelRotationMat());
    }


    public <E extends Entity> void renderEntity(E entityIn, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLight) {
        EntityRenderer<? super E> render;
        EntityRenderDispatcher manager = Minecraft.getInstance().getEntityRenderDispatcher();

        render = manager.getRenderer(entityIn);
        matrixStack.pushPose();
        try {
            render.render(entityIn, 0, partialTicks, matrixStack, bufferIn, packedLight);
        } catch (Throwable throwable1) {
            throw new ReportedException(CrashReport.forThrowable(throwable1, "Rendering entity in world"));
        }
        matrixStack.popPose();
    }
}