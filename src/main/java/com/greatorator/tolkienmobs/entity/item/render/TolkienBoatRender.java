package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.entity.item.model.TolkienBoatModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class TolkienBoatRender extends EntityRenderer<TolkienBoatEntity> {
    private static final ResourceLocation[] TOLKIEN_TEXTURE_LOCATIONS = new ResourceLocation[]{new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/mallorn.png"), new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/mirkwood.png"), new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/culumalda.png"), new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/lebethron.png")};
    protected final TolkienBoatModel model = new TolkienBoatModel();

    public TolkienBoatRender(EntityRendererManager rendererManager) {
        super(rendererManager);
        this.shadowRadius = 0.8F;
    }

    @Override
    public void render(TolkienBoatEntity tolkienBoatEntity, float vector, float angle, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int type) {
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - vector));
        float f = (float)tolkienBoatEntity.getHurtTime() - angle;
        float f1 = tolkienBoatEntity.getDamage() - angle;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float)tolkienBoatEntity.getHurtDir()));
        }

        float f2 = tolkienBoatEntity.getBubbleAngle(angle);
        if (!MathHelper.equal(f2, 0.0F)) {
            matrixStack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), tolkienBoatEntity.getBubbleAngle(angle), true));
        }

        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        this.model.setupAnim(tolkienBoatEntity, angle, 0.0F, -0.1F, 0.0F, 0.0F);
        IVertexBuilder ivertexbuilder = renderTypeBuffer.getBuffer(this.model.renderType(this.getTextureLocation(tolkienBoatEntity)));
        this.model.renderToBuffer(matrixStack, ivertexbuilder, type, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!tolkienBoatEntity.isUnderWater()) {
            IVertexBuilder ivertexbuilder1 = renderTypeBuffer.getBuffer(RenderType.waterMask());
            this.model.waterPatch().render(matrixStack, ivertexbuilder1, type, OverlayTexture.NO_OVERLAY);
        }

        matrixStack.popPose();
        super.render(tolkienBoatEntity, vector, angle, matrixStack, renderTypeBuffer, type);
    }

    @Override
    public ResourceLocation getTextureLocation(TolkienBoatEntity tolkienBoatEntity) {
        return TOLKIEN_TEXTURE_LOCATIONS[tolkienBoatEntity.getWoodType().ordinal()];
    }
}