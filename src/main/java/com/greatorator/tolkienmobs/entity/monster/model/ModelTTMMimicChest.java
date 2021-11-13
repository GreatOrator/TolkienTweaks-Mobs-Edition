package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMMimicChest;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.MathHelper;

import static com.greatorator.tolkienmobs.utils.TTMRand.degToRad;

public class ModelTTMMimicChest<T extends MonsterEntity> extends SegmentedModel<T> {
    public ModelRenderer box;
    public ModelRenderer boxLid;
    public ModelRenderer box2;
    public ModelRenderer boxLid2;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer leftbackLeg;
    public ModelRenderer rightbackLeg;
    public ModelRenderer MimicTeeth;
    public ModelRenderer MimicGums;
    public ModelRenderer MimicGums_1;
    public ModelRenderer MimicGums_2;
    public ModelRenderer MimicGums_3;
    public ModelRenderer MimicThroat;
    public ModelRenderer MimicTongue;
    public ModelRenderer MimicTongue_1;
    public ModelRenderer MimicTongue_2;
    public ModelRenderer MimicTeeth_1;
    public ModelRenderer MimicThroat_1;
    public ModelRenderer MimicLatch;
    public ModelRenderer MimicGums_4;
    public ModelRenderer MimicGums_5;
    public ModelRenderer MimicGums_6;
    public ModelRenderer MimicGums_7;
    public ModelRenderer MimicEye;
    public ModelRenderer MimiEyePart;
    public ModelRenderer MimicEyePart;
    public ModelRenderer MimicEyePart_1;
    public ModelRenderer MimiEyePart_1;
    public ModelRenderer MimiEyePart_2;
    public ModelRenderer MimiEyePart_3;
    public ModelRenderer leftLeg_1;
    public ModelRenderer rightLeg_1;
    public ModelRenderer leftbackLeg_1;
    public ModelRenderer rightbackLeg_1;
    public static boolean renderChest = true;

    public ModelTTMMimicChest()
    {
        this.texWidth = 128;
        this.texHeight = 64;
        this.MimiEyePart_3 = new ModelRenderer(this, 48, 11);
        this.MimiEyePart_3.setPos(0.0F, -0.1F, 0.0F);
        this.MimiEyePart_3.addBox(-1.0F, -1.0F, -2.0F, 2, 1, 4, 0.0F);
        this.rightLeg_1 = new ModelRenderer(this, 112, 20);
        this.rightLeg_1.setPos(-0.3F, 3.5F, -0.3F);
        this.rightLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(rightLeg_1, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
        this.box = new ModelRenderer(this, 0, 0);
        this.box.setPos(0.0F, 9.0F, 0.0F);
        this.box.addBox(-8.0F, 0.0F, -8.0F, 16, 10, 16, 0.0F);
        this.MimicTongue = new ModelRenderer(this, 0, 27);
        this.MimicTongue.setPos(0.0F, 1.5F, 3.5F);
        this.MimicTongue.addBox(-3.0F, -1.0F, -8.0F, 6, 1, 8, 0.0F);
        this.setRotateAngle(MimicTongue, -0.3665191429188092F, 0.0F, 0.0F);
        this.MimiEyePart_1 = new ModelRenderer(this, 50, 0);
        this.MimiEyePart_1.setPos(0.0F, 0.0F, 0.0F);
        this.MimiEyePart_1.addBox(-4.0F, -1.0F, -1.0F, 8, 1, 2, 0.0F);
        this.MimicGums_6 = new ModelRenderer(this, 68, 21);
        this.MimicGums_6.setPos(0.0F, 0.0F, 15.0F);
        this.MimicGums_6.addBox(0.0F, 0.0F, 0.0F, 16, 6, 1, 0.0F);
        this.rightLeg = new ModelRenderer(this, 112, 20);
        this.rightLeg.setPos(-5.8F, 16.0F, -5.8F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(rightLeg, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
        this.rightbackLeg_1 = new ModelRenderer(this, 112, 20);
        this.rightbackLeg_1.setPos(-0.3F, 3.5F, -0.3F);
        this.rightbackLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(rightbackLeg_1, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
        this.MimicEyePart_1 = new ModelRenderer(this, 50, 8);
        this.MimicEyePart_1.setPos(0.0F, -0.7F, 0.0F);
        this.MimicEyePart_1.addBox(-2.0F, -1.0F, -1.0F, 4, 1, 2, 0.0F);
        this.MimicTeeth = new ModelRenderer(this, 68, 46);
        this.MimicTeeth.setPos(0.5F, -2.5F, 0.5F);
        this.MimicTeeth.addBox(-8.0F, 0.0F, -8.0F, 15, 3, 15, 0.0F);
        this.MimiEyePart = new ModelRenderer(this, 61, 0);
        this.MimiEyePart.setPos(0.0F, 0.0F, 0.0F);
        this.MimiEyePart.addBox(-1.0F, -1.0F, -4.0F, 2, 1, 8, 0.0F);
        this.MimicThroat = new ModelRenderer(this, 68, 0);
        this.MimicThroat.setPos(0.5F, 0.5F, 0.5F);
        this.MimicThroat.addBox(-8.0F, 0.0F, -8.0F, 15, 2, 15, 0.0F);
        this.boxLid = new ModelRenderer(this, 0, 42);
        this.boxLid.setPos(-8.0F, 9.0F, 8.0F);
        this.boxLid.addBox(0.0F, 0.0F, 0.0F, 16, 6, 16, 0.0F);
        this.setRotateAngle(boxLid, 3.141592653589793F, 0.0F, 0.0F);
        this.MimicEye = new ModelRenderer(this, 50, 0);
        this.MimicEye.setPos(8.0F, 1.0F, 8.0F);
        this.MimicEye.addBox(-3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F);
        this.rightbackLeg = new ModelRenderer(this, 112, 20);
        this.rightbackLeg.setPos(-5.8F, 16.0F, 5.8F);
        this.rightbackLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(rightbackLeg, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
        this.MimicEyePart = new ModelRenderer(this, 50, 0);
        this.MimicEyePart.setPos(0.0F, -0.5F, 0.0F);
        this.MimicEyePart.addBox(-3.0F, -1.0F, -2.0F, 6, 1, 4, 0.0F);
        this.leftLeg = new ModelRenderer(this, 112, 20);
        this.leftLeg.setPos(5.8F, 16.0F, -5.8F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(leftLeg, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
        this.MimicLatch = new ModelRenderer(this, 0, 0);
        this.MimicLatch.setPos(6.9F, -2.0F, 15.5F);
        this.MimicLatch.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
        this.MimicGums_4 = new ModelRenderer(this, 68, 21);
        this.MimicGums_4.setPos(0.0F, 0.0F, 0.0F);
        this.MimicGums_4.addBox(0.0F, 0.0F, 0.0F, 16, 6, 1, 0.0F);
        this.MimicThroat_1 = new ModelRenderer(this, 68, 0);
        this.MimicThroat_1.setPos(0.5F, 0.5F, 0.5F);
        this.MimicThroat_1.addBox(0.0F, 0.0F, 0.0F, 15, 2, 15, 0.0F);
        this.MimicGums_3 = new ModelRenderer(this, 68, 21);
        this.MimicGums_3.setPos(15.0F, 0.0F, 0.0F);
        this.MimicGums_3.addBox(-8.0F, 0.0F, -8.0F, 1, 6, 16, 0.0F);
        this.leftbackLeg = new ModelRenderer(this, 112, 20);
        this.leftbackLeg.setPos(5.8F, 16.0F, 5.8F);
        this.leftbackLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(leftbackLeg, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
        this.MimicTongue_1 = new ModelRenderer(this, 2, 30);
        this.MimicTongue_1.setPos(0.0F, 0.0F, -7.8F);
        this.MimicTongue_1.addBox(-3.0F, -1.0F, -5.0F, 6, 1, 5, 0.0F);
        this.setRotateAngle(MimicTongue_1, 0.20943951023931953F, 0.0F, 0.0F);
        this.MimicGums_7 = new ModelRenderer(this, 68, 21);
        this.MimicGums_7.setPos(15.0F, 0.0F, 0.0F);
        this.MimicGums_7.addBox(0.0F, 0.0F, 0.0F, 1, 6, 16, 0.0F);
        this.leftLeg_1 = new ModelRenderer(this, 112, 20);
        this.leftLeg_1.setPos(0.3F, 3.5F, -0.3F);
        this.leftLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(leftLeg_1, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
        this.leftbackLeg_1 = new ModelRenderer(this, 112, 20);
        this.leftbackLeg_1.setPos(0.3F, 3.5F, -0.3F);
        this.leftbackLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(leftbackLeg_1, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
        this.MimiEyePart_2 = new ModelRenderer(this, 50, 0);
        this.MimiEyePart_2.setPos(0.0F, 0.0F, -0.1F);
        this.MimiEyePart_2.addBox(-2.0F, -1.0F, -3.0F, 4, 1, 6, 0.0F);
        this.MimicGums_1 = new ModelRenderer(this, 68, 21);
        this.MimicGums_1.setPos(0.0F, 0.0F, 0.0F);
        this.MimicGums_1.addBox(-8.0F, 0.0F, -8.0F, 1, 6, 16, 0.0F);
        this.MimicTeeth_1 = new ModelRenderer(this, 68, 46);
        this.MimicTeeth_1.setPos(0.5F, -2.4F, 0.5F);
        this.MimicTeeth_1.addBox(0.0F, 0.0F, 0.0F, 15, 3, 15, 0.0F);
        this.MimicGums = new ModelRenderer(this, 68, 21);
        this.MimicGums.setPos(0.0F, 0.0F, 0.0F);
        this.MimicGums.addBox(-8.0F, 0.0F, -8.0F, 16, 6, 1, 0.0F);
        this.MimicTongue_2 = new ModelRenderer(this, 2, 30);
        this.MimicTongue_2.setPos(0.0F, -0.1F, -4.7F);
        this.MimicTongue_2.addBox(-3.0F, -1.0F, -5.0F, 6, 1, 5, 0.0F);
        this.setRotateAngle(MimicTongue_2, 0.3490658503988659F, 0.0F, 0.0F);
        this.MimicGums_5 = new ModelRenderer(this, 68, 21);
        this.MimicGums_5.setPos(0.0F, 0.0F, 0.0F);
        this.MimicGums_5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 16, 0.0F);
        this.MimicGums_2 = new ModelRenderer(this, 68, 21);
        this.MimicGums_2.setPos(0.0F, 0.0F, 15.0F);
        this.MimicGums_2.addBox(-8.0F, 0.0F, -8.0F, 16, 6, 1, 0.0F);
        this.MimicEyePart_1.addChild(this.MimiEyePart_3);
        this.rightLeg.addChild(this.rightLeg_1);
        this.box.addChild(this.MimicTongue);
        this.MimicEye.addChild(this.MimiEyePart_1);
        this.boxLid.addChild(this.MimicGums_6);
        this.rightbackLeg.addChild(this.rightbackLeg_1);
        this.MimicEye.addChild(this.MimicEyePart_1);
        this.box.addChild(this.MimicTeeth);
        this.MimicEye.addChild(this.MimiEyePart);
        this.box.addChild(this.MimicThroat);
        this.boxLid.addChild(this.MimicEye);
        this.MimicEye.addChild(this.MimicEyePart);
        this.boxLid.addChild(this.MimicLatch);
        this.boxLid.addChild(this.MimicGums_4);
        this.boxLid.addChild(this.MimicThroat_1);
        this.box.addChild(this.MimicGums_3);
        this.MimicTongue.addChild(this.MimicTongue_1);
        this.boxLid.addChild(this.MimicGums_7);
        this.leftLeg.addChild(this.leftLeg_1);
        this.leftbackLeg.addChild(this.leftbackLeg_1);
        this.MimicEyePart.addChild(this.MimiEyePart_2);
        this.box.addChild(this.MimicGums_1);
        this.boxLid.addChild(this.MimicTeeth_1);
        this.box.addChild(this.MimicGums);
        this.MimicTongue_1.addChild(this.MimicTongue_2);
        this.boxLid.addChild(this.MimicGums_5);
        this.box.addChild(this.MimicGums_2);

        this.box2 = new ModelRenderer(this, 0, 0);
        this.box2.setPos(0.0F, 14.0F, 0.0F);
        this.box2.addBox(-8.0F, 0.0F, -8.0F, 16, 10, 16, 0.0F);
        this.boxLid2 = new ModelRenderer(this, 0, 42);
        this.boxLid2.setPos(-8.0F, 14.0F, 8.0F);
        this.boxLid2.addBox(0.0F, 0.0F, 0.0F, 16, 6, 16, 0.0F);
        this.setRotateAngle(boxLid2, 3.141592653589793F, 0.0F, 0.0F);
        this.boxLid2.addChild(this.MimicLatch);
    }

    public boolean getRenderChest() {
        renderChest = EntityTTMMimicChest.getMimicChest();
        return renderChest;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public Iterable<ModelRenderer> parts() {
        if(this.getRenderChest()){
            return ImmutableList.of(this.box2, this.boxLid2);
        }
        else {
            return ImmutableList.of(this.box, this.boxLid, this.leftLeg, this.rightLeg, this.leftbackLeg, this.rightbackLeg);
        }
    }

    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        if(EntityTTMMimicChest.getMimicAttack()){
            float baseBackLegRotation = 0.2617993877991494F;
            float baseFrontLegRotation = -0.2617993877991494F;
            float baseLidRotation = 2.356194490192345F;
            float baseTongueRotation = -0.3665191429188092F;
            float hinge = Math.min(p_225597_3_, 0.6F);
            this.boxLid.xRot = baseLidRotation + ((MathHelper.cos(p_225597_4_ * 0.6662F) - (float) Math.PI / 3) * hinge);

            this.leftLeg.xRot = baseFrontLegRotation + (MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_);
            this.leftbackLeg.xRot = baseBackLegRotation + (MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_);
            this.rightLeg.xRot = baseFrontLegRotation + (MathHelper.cos(p_225597_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_225597_3_);
            this.rightbackLeg.xRot = baseBackLegRotation + (MathHelper.cos(p_225597_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_225597_3_);

            MimicTongue.xRot = baseTongueRotation + (MathHelper.cos(degToRad(p_225597_1_.tickCount * 7)) * degToRad(10));
            MimicTongue_1.xRot = MimicTongue.xRot * 3;
            MimicTongue_2.xRot = MimicTongue_1.xRot * 1;
        }
    }
}