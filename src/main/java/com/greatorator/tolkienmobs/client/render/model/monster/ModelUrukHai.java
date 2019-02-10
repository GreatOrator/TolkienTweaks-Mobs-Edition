package com.greatorator.tolkienmobs.client.render.model.monster;


import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import com.greatorator.tolkienmobs.entity.monster.EntityUrukHai;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Uruk Hai - GreatOrator
 */
public class ModelUrukHai extends ModelTTM {
    public ModelRenderer UrukHaiLegL;
    public ModelRenderer UrukHaiHead;
    public ModelRenderer UrukHaiBody;
    public ModelRenderer UrukHaiLegR;
    public ModelRenderer UrukHaiArmLUpper;
    public ModelRenderer UrukHaiNose;
    public ModelRenderer UrukHaiBrowL;
    public ModelRenderer UrukHaiBrowR;
    public ModelRenderer Tooth3;
    public ModelRenderer Tooth4;
    public ModelRenderer UrukHaiHairTop;
    public ModelRenderer UrukHaiHairBack;
    public ModelRenderer UrukHaiEar1;
    public ModelRenderer UrukHaiEar2;
    public ModelRenderer UrukHaiEar3;
    public ModelRenderer UrukHaiEar4;
    public ModelRenderer UrukHaiHairBack2;
    public ModelRenderer UrukHaiHairBack3;
    public ModelRenderer UrukHaiBodyUpper;
    public ModelRenderer UrukHaiArmRUpper;

    public ModelUrukHai() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.UrukHaiHairBack3 = new ModelRenderer(this, 52, 12);
        this.UrukHaiHairBack3.setRotationPoint(0.0F, 3.0F, 4.0F);
        this.UrukHaiHairBack3.addBox(-1.5F, -0.5F, -0.5F, 3, 3, 1, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 48);
        this.bipedLeftArm.setRotationPoint(-7.0F, 2.5F, 0.0F);
        this.bipedLeftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Tooth3 = new ModelRenderer(this, 4, 0);
        this.Tooth3.mirror = true;
        this.Tooth3.setRotationPoint(1.5F, -0.5F, -3.7F);
        this.Tooth3.addBox(-0.5F, -1.5F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth3, 0.17453292519943295F, 0.0F, 0.0F);
        this.UrukHaiArmRUpper = new ModelRenderer(this, 56, 37);
        this.UrukHaiArmRUpper.setRotationPoint(-1.0F, -0.5F, -1.0F);
        this.UrukHaiArmRUpper.addBox(-1.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.UrukHaiHairBack = new ModelRenderer(this, 52, 10);
        this.UrukHaiHairBack.setRotationPoint(0.0F, -8.0F, 4.0F);
        this.UrukHaiHairBack.addBox(-1.5F, -0.5F, -0.5F, 3, 10, 1, 0.0F);
        this.UrukHaiEar2 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar2.setRotationPoint(4.0F, -3.7F, 0.6F);
        this.UrukHaiEar2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(UrukHaiEar2, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiEar4 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar4.setRotationPoint(-4.0F, -3.7F, 0.6F);
        this.UrukHaiEar4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(UrukHaiEar4, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiLegR = new ModelRenderer(this, 72, 48);
        this.UrukHaiLegR.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.UrukHaiLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.UrukHaiArmLUpper = new ModelRenderer(this, 32, 37);
        this.UrukHaiArmLUpper.setRotationPoint(-1.0F, -0.5F, -1.0F);
        this.UrukHaiArmLUpper.addBox(-3.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.UrukHaiHairTop = new ModelRenderer(this, 32, 10);
        this.UrukHaiHairTop.setRotationPoint(0.0F, -8.0F, -2.5F);
        this.UrukHaiHairTop.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 7, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 24, 48);
        this.bipedRightArm.setRotationPoint(7.0F, 2.5F, 0.0F);
        this.bipedRightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.UrukHaiEar1 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar1.setRotationPoint(4.0F, -4.5F, 0.0F);
        this.UrukHaiEar1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(UrukHaiEar1, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiHairBack2 = new ModelRenderer(this, 48, 10);
        this.UrukHaiHairBack2.setRotationPoint(1.0F, 2.0F, 4.0F);
        this.UrukHaiHairBack2.addBox(-1.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.Tooth4 = new ModelRenderer(this, 4, 4);
        this.Tooth4.mirror = true;
        this.Tooth4.setRotationPoint(-1.5F, -0.5F, -3.7F);
        this.Tooth4.addBox(-0.5F, -1.5F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth4, 0.17453292519943295F, 0.0F, 0.0F);
        this.UrukHaiBodyUpper = new ModelRenderer(this, 0, 36);
        this.UrukHaiBodyUpper.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.UrukHaiBodyUpper.addBox(-4.0F, 0.0F, -2.0F, 10, 6, 6, 0.0F);
        this.UrukHaiNose = new ModelRenderer(this, 25, 0);
        this.UrukHaiNose.setRotationPoint(0.0F, -3.5F, -3.7F);
        this.UrukHaiNose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(UrukHaiNose, 0.7853981633974483F, -0.6684611035138281F, -3.6651914291880923F);
        this.UrukHaiLegL = new ModelRenderer(this, 56, 48);
        this.UrukHaiLegL.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.UrukHaiLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.UrukHaiBody = new ModelRenderer(this, 0, 48);
        this.UrukHaiBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.UrukHaiBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.UrukHaiEar3 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar3.setRotationPoint(-4.0F, -4.5F, 0.0F);
        this.UrukHaiEar3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(UrukHaiEar3, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiBrowL = new ModelRenderer(this, 32, 0);
        this.UrukHaiBrowL.setRotationPoint(-2.8F, -6.0F, -3.8F);
        this.UrukHaiBrowL.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(UrukHaiBrowL, 0.0F, 0.0F, 0.08726646259971647F);
        this.UrukHaiHead = new ModelRenderer(this, 0, 0);
        this.UrukHaiHead.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.UrukHaiHead.addBox(-4.0F, -8.0F, -4.0F, 8, 9, 8, 0.0F);
        this.UrukHaiBrowR = new ModelRenderer(this, 45, 0);
        this.UrukHaiBrowR.setRotationPoint(0.9F, -5.8F, -3.8F);
        this.UrukHaiBrowR.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(UrukHaiBrowR, 0.0F, 0.0F, -0.08726646259971647F);
        this.UrukHaiHead.addChild(this.UrukHaiHairBack3);
        this.UrukHaiHead.addChild(this.Tooth3);
        this.bipedRightArm.addChild(this.UrukHaiArmRUpper);
        this.UrukHaiHead.addChild(this.UrukHaiHairBack);
        this.UrukHaiHead.addChild(this.UrukHaiEar2);
        this.UrukHaiHead.addChild(this.UrukHaiEar4);
        this.bipedLeftArm.addChild(this.UrukHaiArmLUpper);
        this.UrukHaiHead.addChild(this.UrukHaiHairTop);
        this.UrukHaiHead.addChild(this.UrukHaiEar1);
        this.UrukHaiHead.addChild(this.UrukHaiHairBack2);
        this.UrukHaiHead.addChild(this.Tooth4);
        this.UrukHaiBody.addChild(this.UrukHaiBodyUpper);
        this.UrukHaiHead.addChild(this.UrukHaiNose);
        this.UrukHaiHead.addChild(this.UrukHaiEar3);
        this.UrukHaiHead.addChild(this.UrukHaiBrowL);
        this.UrukHaiHead.addChild(this.UrukHaiBrowR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.UrukHaiHead.render(f5);
        this.UrukHaiBody.render(f5);
        this.bipedLeftArm.render(f5);
        this.UrukHaiLegR.render(f5);
        this.bipedRightArm.render(f5);
        this.UrukHaiLegL.render(f5);
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);

        if (itemstack.getItem() == Items.BOW && ((EntityUrukHai)entitylivingbaseIn).isSwingingArms())
        {
            if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT)
            {
                this.rightArmPose = ArmPose.ITEM;
            }
            else
            {
                this.leftArmPose = ArmPose.ITEM;
            }
        }

        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.UrukHaiLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.UrukHaiLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.UrukHaiHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.UrukHaiHead.rotateAngleX = headPitch * 0.017453292F;

        this.bipedRightArm.rotationPointX = -8.9F;
        this.bipedLeftArm.rotationPointX = 8.9F;
    }
}