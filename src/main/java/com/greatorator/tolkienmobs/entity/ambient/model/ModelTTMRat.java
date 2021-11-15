package com.greatorator.tolkienmobs.entity.ambient.model;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMRat;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.utils.TTMRand.degToRad;

@OnlyIn(Dist.CLIENT)
public class ModelTTMRat<E extends AnimalEntity> extends EntityModel<EntityTTMRat> {
    public ModelRenderer RatBody;
    public ModelRenderer RatHead;
    public ModelRenderer RatLegFR;
    public ModelRenderer RatLegFL;
    public ModelRenderer RatLegRR;
    public ModelRenderer RatLegRL;
    public ModelRenderer RatBody_1;
    public ModelRenderer RatTail1;
    public ModelRenderer RatTail2;
    public ModelRenderer RatTail3;
    public ModelRenderer RatMouth;
    public ModelRenderer RatEarR;
    public ModelRenderer RatEarL;
    public ModelRenderer RatNose;
    public ModelRenderer RatWhiskers;
    public ModelRenderer RatWhiskers_1;
    public ModelRenderer RatWhiskers_2;
    public ModelRenderer RatFootFR;
    public ModelRenderer RatFootFL;
    public ModelRenderer RatFootRR;
    public ModelRenderer RatFootRL;

    public ModelTTMRat() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.RatEarR = new ModelRenderer(this, 26, 29);
        this.RatEarR.setPos(-0.7F, -0.7F, -1.0F);
        this.RatEarR.addBox(-2.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);
        this.RatWhiskers_2 = new ModelRenderer(this, 20, 24);
        this.RatWhiskers_2.setPos(1.5F, -0.7F, 0.0F);
        this.RatWhiskers_2.addBox(0.0F, -2.5F, 0.0F, 2, 5, 0, 0.0F);
        this.setRotateAngle(RatWhiskers_2, 0.0F, -0.3490658503988659F, 0.0F);
        this.RatEarL = new ModelRenderer(this, 26, 29);
        this.RatEarL.mirror = true;
        this.RatEarL.setPos(0.7F, -0.7F, -1.0F);
        this.RatEarL.addBox(0.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);
        this.RatBody = new ModelRenderer(this, 0, 22);
        this.RatBody.setPos(0.0F, 20.0F, 0.0F);
        this.RatBody.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 5, 0.0F);
        this.RatFootRR = new ModelRenderer(this, 20, 29);
        this.RatFootRR.setPos(-0.5F, 3.0F, -1.0F);
        this.RatFootRR.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F);
        this.RatWhiskers = new ModelRenderer(this, 26, 20);
        this.RatWhiskers.setPos(0.0F, 1.5F, -0.3F);
        this.RatWhiskers.addBox(-1.5F, -2.2F, 0.0F, 3, 3, 0, 0.0F);
        this.RatLegFL = new ModelRenderer(this, 25, 25);
        this.RatLegFL.setPos(1.5F, 22.0F, -3.0F);
        this.RatLegFL.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.RatBody_1 = new ModelRenderer(this, 1, 23);
        this.RatBody_1.setPos(0.0F, 0.0F, 0.0F);
        this.RatBody_1.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.0F);
        this.RatTail3 = new ModelRenderer(this, 20, 0);
        this.RatTail3.setPos(0.0F, 0.0F, 4.0F);
        this.RatTail3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
        this.RatFootRL = new ModelRenderer(this, 20, 29);
        this.RatFootRL.setPos(0.5F, 3.0F, -1.0F);
        this.RatFootRL.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F);
        this.RatWhiskers_1 = new ModelRenderer(this, 20, 24);
        this.RatWhiskers_1.setPos(-1.4F, -0.7F, 0.0F);
        this.RatWhiskers_1.addBox(-2.0F, -2.5F, 0.0F, 2, 5, 0, 0.0F);
        this.setRotateAngle(RatWhiskers_1, 0.0F, 0.3490658503988659F, 0.0F);
        this.RatTail2 = new ModelRenderer(this, 19, 0);
        this.RatTail2.setPos(0.0F, 0.0F, 4.0F);
        this.RatTail2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.RatFootFR = new ModelRenderer(this, 20, 29);
        this.RatFootFR.setPos(-0.5F, 1.5F, -0.5F);
        this.RatFootFR.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F);
        this.RatLegFR = new ModelRenderer(this, 25, 25);
        this.RatLegFR.setPos(-1.5F, 22.0F, -3.0F);
        this.RatLegFR.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.RatLegRR = new ModelRenderer(this, 24, 23);
        this.RatLegRR.setPos(-2.0F, 20.5F, 2.7F);
        this.RatLegRR.addBox(-1.0F, -0.5F, -1.5F, 1, 3, 3, 0.0F);
        this.RatTail1 = new ModelRenderer(this, 18, 0);
        this.RatTail1.setPos(0.0F, 0.0F, 4.8F);
        this.RatTail1.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 4, 0.0F);
        this.RatHead = new ModelRenderer(this, 0, 15);
        this.RatHead.setPos(0.0F, 20.0F, -5.5F);
        this.RatHead.addBox(-1.5F, -1.5F, -3.0F, 3, 3, 4, 0.0F);
        this.RatMouth = new ModelRenderer(this, 0, 11);
        this.RatMouth.setPos(0.0F, 0.5F, -3.0F);
        this.RatMouth.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);
        this.RatNose = new ModelRenderer(this, 0, 16);
        this.RatNose.setPos(0.0F, -0.8F, -1.8F);
        this.RatNose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.RatLegRL = new ModelRenderer(this, 24, 23);
        this.RatLegRL.setPos(2.0F, 20.5F, 2.7F);
        this.RatLegRL.addBox(0.0F, -0.5F, -1.5F, 1, 3, 3, 0.0F);
        this.RatFootFL = new ModelRenderer(this, 20, 29);
        this.RatFootFL.setPos(0.5F, 1.5F, -0.5F);
        this.RatFootFL.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F);
        this.RatHead.addChild(this.RatEarR);
        this.RatWhiskers.addChild(this.RatWhiskers_2);
        this.RatHead.addChild(this.RatEarL);
        this.RatLegRR.addChild(this.RatFootRR);
        this.RatNose.addChild(this.RatWhiskers);
        this.RatBody.addChild(this.RatBody_1);
        this.RatTail2.addChild(this.RatTail3);
        this.RatLegRL.addChild(this.RatFootRL);
        this.RatWhiskers.addChild(this.RatWhiskers_1);
        this.RatTail1.addChild(this.RatTail2);
        this.RatLegFR.addChild(this.RatFootFR);
        this.RatBody.addChild(this.RatTail1);
        this.RatHead.addChild(this.RatMouth);
        this.RatMouth.addChild(this.RatNose);
        this.RatLegFL.addChild(this.RatFootFL);
    }

    @Override
    public void setupAnim(EntityTTMRat entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.RatLegFL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RatLegFR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.RatLegRR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RatLegRL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.RatHead.yRot = netHeadYaw * 0.017453292F;
        this.RatHead.xRot = headPitch * 0.017453292F;

        if (limbSwingAmount > 0.2) {
            float flick = Math.min(limbSwingAmount, 0.6F);
            this.RatTail1.yRot = MathHelper.sin(degToRad(entityIn.tickCount*7)) * (degToRad(5) * flick);
        } else {
            this.RatTail1.yRot = MathHelper.sin(degToRad(entityIn.tickCount*7)) * degToRad(5);
        }
        RatTail2.yRot = RatTail1.xRot * 4;
        RatTail3.yRot = RatTail2.xRot * 3;

        // flick ears
        RatEarR.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(15);
        RatEarL.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(-15);
    }

    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.RatHead);
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.RatBody, this.RatLegRR, this.RatLegRL, this.RatLegFR, this.RatLegFL);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.getHeadParts().forEach((p_228228_8_) -> {
            p_228228_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
        this.getBodyParts().forEach((p_228227_8_) -> {
            p_228227_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }
}