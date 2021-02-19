//package com.greatorator.tolkienmobs.client.render.model.ambient;
//
//import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMThrush;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.util.math.MathHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
///* Thrush - GreatOrator */
//@SideOnly(Side.CLIENT)
//public class ModelThrush extends ModelTTM {
//    public ModelRenderer ThrushBody;
//    public ModelRenderer ThrushBodyWingless;
//    public ModelRenderer ThrushTail1;
//    public ModelRenderer ThrushLegL1;
//    public ModelRenderer ThrushLegR1;
//    public ModelRenderer ThrushHead;
//    public ModelRenderer ThrushWingL1;
//    public ModelRenderer ThrushWingR1;
//    public ModelRenderer ThrushTail2;
//    public ModelRenderer ThrushTail3;
//    public ModelRenderer ThrushLegL2;
//    public ModelRenderer ThrushLegR2;
//    public ModelRenderer ThrushBeakTop;
//    public ModelRenderer ThrushBeakBottom;
//    public ModelRenderer ThrushWingL2;
//    public ModelRenderer ThrushWingR2;
//    private ModelThrush.State state = ModelThrush.State.STANDING;
//
//    public ModelThrush() {
//        this.textureWidth = 32;
//        this.textureHeight = 32;
//        this.ThrushTail3 = new ModelRenderer(this, -3, 9);
//        this.ThrushTail3.setRotationPoint(0.0F, 0.0F, -1.5F);
//        this.ThrushTail3.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
//        this.setRotateAngle(ThrushTail3, 0.0F, -0.2665117767795341F, 0.0F);
//        this.ThrushLegL2 = new ModelRenderer(this, 0, 5);
//        this.ThrushLegL2.setRotationPoint(0.0F, 2.4F, 0.5F);
//        this.ThrushLegL2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
//        this.setRotateAngle(ThrushLegL2, -1.3613568165555772F, 0.0F, 0.0F);
//        this.ThrushLegL1 = new ModelRenderer(this, 0, 3);
//        this.ThrushLegL1.setRotationPoint(1.3F, 1.5F, 0.0F);
//        this.ThrushLegL1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
//        this.setRotateAngle(ThrushLegL1, 0.2617993877991494F, 0.0F, 0.0F);
//        this.ThrushWingL2 = new ModelRenderer(this, 2, 9);
//        this.ThrushWingL2.setRotationPoint(5.0F, -1.0F, -1.5F);
//        this.ThrushWingL2.addBox(0.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
//        this.ThrushHead = new ModelRenderer(this, 12, 0);
//        this.ThrushHead.setRotationPoint(0.5F, -0.1F, -3.0F);
//        this.ThrushHead.addBox(-1.0F, -1.5F, -2.5F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(ThrushHead, 0.5235987755982988F, 0.0F, 0.0F);
//        this.ThrushLegR1 = new ModelRenderer(this, 0, 3);
//        this.ThrushLegR1.setRotationPoint(-0.3F, 1.5F, 0.0F);
//        this.ThrushLegR1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
//        this.setRotateAngle(ThrushLegR1, 0.2617993877991494F, 0.0F, 0.0F);
//        this.ThrushWingL1 = new ModelRenderer(this, 0, 9);
//        this.ThrushWingL1.setRotationPoint(1.0F, 1.0F, 1.3F);
//        this.ThrushWingL1.addBox(1.0F, -1.0F, -3.0F, 4, 0, 3, 0.0F);
//        this.ThrushWingR1 = new ModelRenderer(this, 0, 9);
//        this.ThrushWingR1.setRotationPoint(-1.0F, 0.0F, 0.0F);
//        this.ThrushWingR1.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
//        this.ThrushBody = new ModelRenderer(this, 0, 0);
//        this.ThrushBody.setRotationPoint(0.0F, 20.3F, 0.5F);
//        this.ThrushBody.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
//        this.setRotateAngle(ThrushBody, -0.5235987755982988F, 0.0F, 0.0F);
//        this.ThrushTail1 = new ModelRenderer(this, -4, 9);
//        this.ThrushTail1.setRotationPoint(0.0F, -0.8F, 4.3F);
//        this.ThrushTail1.addBox(-0.5F, -0.5F, -1.5F, 2, 0, 4, 0.0F);
//        this.setRotateAngle(ThrushTail1, 0.2617993877991494F, 0.0F, 0.0F);
//        this.ThrushBeakTop = new ModelRenderer(this, 0, 0);
//        this.ThrushBeakTop.setRotationPoint(0.0F, 0.0F, -2.0F);
//        this.ThrushBeakTop.addBox(-0.5F, -0.8F, -2.0F, 1, 1, 2, 0.0F);
//        this.setRotateAngle(ThrushBeakTop, 0.08726646259971647F, 0.0F, 0.0F);
//        this.ThrushBeakBottom = new ModelRenderer(this, 20, 3);
//        this.ThrushBeakBottom.setRotationPoint(0.0F, 0.4F, 0.0F);
//        this.ThrushBeakBottom.addBox(-0.5F, -0.9F, -2.0F, 1, 1, 2, 0.0F);
//        this.setRotateAngle(ThrushBeakBottom, -0.08726646259971647F, 0.0F, 0.0F);
//        this.ThrushTail2 = new ModelRenderer(this, -3, 9);
//        this.ThrushTail2.setRotationPoint(1.0F, 0.0F, -1.5F);
//        this.ThrushTail2.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
//        this.setRotateAngle(ThrushTail2, 0.0F, 0.2617993877991494F, 0.0F);
//        this.ThrushWingR2 = new ModelRenderer(this, 2, 9);
//        this.ThrushWingR2.setRotationPoint(-4.0F, 0.0F, 0.0F);
//        this.ThrushWingR2.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
//        this.ThrushLegR2 = new ModelRenderer(this, 0, 5);
//        this.ThrushLegR2.setRotationPoint(0.0F, 2.4F, 0.5F);
//        this.ThrushLegR2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
//        this.setRotateAngle(ThrushLegR2, -1.3613568165555772F, 0.0F, 0.0F);
//        this.ThrushTail1.addChild(this.ThrushTail3);
//        this.ThrushLegL1.addChild(this.ThrushLegL2);
//        this.ThrushBody.addChild(this.ThrushLegL1);
//        this.ThrushWingL1.addChild(this.ThrushWingL2);
//        this.ThrushBody.addChild(this.ThrushHead);
//        this.ThrushBody.addChild(this.ThrushLegR1);
//        this.ThrushBody.addChild(this.ThrushWingL1);
//        this.ThrushBody.addChild(this.ThrushWingR1);
//        this.ThrushBody.addChild(this.ThrushTail1);
//        this.ThrushHead.addChild(this.ThrushBeakTop);
//        this.ThrushBeakTop.addChild(this.ThrushBeakBottom);
//        this.ThrushTail1.addChild(this.ThrushTail2);
//        this.ThrushWingR1.addChild(this.ThrushWingR2);
//        this.ThrushLegR1.addChild(this.ThrushLegR2);
//
//        this.ThrushBodyWingless = new ModelRenderer(this, 0, 0);
//        this.ThrushBodyWingless.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
//        this.ThrushBodyWingless.setRotationPoint(0.0F, 20.1F, 0.5F);
//        this.ThrushBodyWingless.setTextureSize(textureWidth, textureHeight);
//        this.ThrushBodyWingless.mirror = true;
//        this.setRotateAngle(ThrushBodyWingless, -0.2617993877991494F, 0.0F, 0.0F);
//        this.ThrushBodyWingless.addChild(ThrushHead);
//        this.ThrushBodyWingless.addChild(ThrushLegL1);
//        this.ThrushBodyWingless.addChild(ThrushLegR1);
//        this.ThrushBodyWingless.addChild(ThrushTail1);
//    }
//
//    @Override
//    public void render(Entity parEntity, float f, float f1, float f2, float f3, float f4, float f5)
//    {
//        if (this.state != State.STANDING) {
//            this.ThrushBody.render(f5);
//        }else {
//            this.ThrushBodyWingless.render(f5);
//        }
//    }
//
//    /**
//     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
//     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
//     * "far" arms and legs can swing at most.
//     */
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
//    {
//        float f = ageInTicks * 0.3F;
//        this.ThrushHead.rotateAngleX = headPitch * 0.017453292F;
//        this.ThrushHead.rotateAngleY = netHeadYaw * 0.017453292F;
//        this.ThrushHead.rotateAngleZ = 0.0F;
//        this.ThrushHead.rotationPointX = 0.5F;
//        this.ThrushBody.rotationPointX = 0.0F;
//        this.ThrushTail1.rotationPointX = 0.0F;
//        this.ThrushWingR1.rotationPointX = 1.0F;
//        this.ThrushWingL1.rotationPointX = 1.0F;
//
//        if (this.state != State.FLYING)
//        {
//            if (this.state == State.SITTING)
//            {
//                return;
//            }
//
//            if (this.state == State.PARTY)
//            {
//                float f1 = MathHelper.cos((float)entityIn.ticksExisted);
//                float f2 = MathHelper.sin((float)entityIn.ticksExisted);
//                this.ThrushHead.rotationPointX = f1;
//                this.ThrushHead.rotationPointY = 15.69F + f2;
//                this.ThrushHead.rotateAngleX = 0.0F;
//                this.ThrushHead.rotateAngleY = 0.0F;
//                this.ThrushHead.rotateAngleZ = MathHelper.sin((float)entityIn.ticksExisted) * 0.4F;
//                this.ThrushBody.rotationPointX = f1;
//                this.ThrushBody.rotationPointY = -0.1F + f2;
//                this.ThrushWingL1.rotateAngleZ = -0.0873F - ageInTicks;
//                this.ThrushWingL1.rotationPointX = 1.0F + f1;
//                this.ThrushWingL1.rotationPointY = 1.65F + f2;
//                this.ThrushWingL1.rotationPointZ = -0.3F + f1;
//                this.ThrushWingR1.rotateAngleZ = 0.0873F + ageInTicks;
//                this.ThrushWingR1.rotationPointX = -1.0F + f1;
//                this.ThrushWingR1.rotationPointY = -0.05F + f2;
//                this.ThrushTail1.rotationPointX = f1;
//                this.ThrushTail1.rotationPointY = -0.8F + f2;
//                return;
//            }
//
//            this.ThrushLegL1.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//            this.ThrushLegR1.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        }
//
//        this.ThrushHead.rotationPointY = f;
//        this.ThrushTail1.rotateAngleX = 1.015F + MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
//        this.ThrushTail1.rotationPointY = -0.8F + f;
//        this.ThrushBody.rotationPointY = -0.1F + f;
//        this.ThrushWingL1.rotateAngleZ = -0.0873F - ageInTicks;
//        this.ThrushWingL1.rotationPointY = 1.65F + f;
//        this.ThrushWingL1.rotationPointZ = -0.3F + f;
//        this.ThrushWingR1.rotateAngleZ = 0.0873F + ageInTicks;
//        this.ThrushWingR1.rotationPointY = -0.05F + f;
//        this.ThrushLegL1.rotationPointY = 1.5F + f;
//        this.ThrushLegR1.rotationPointY = 1.5F + f;
//    }
//
//    /**
//     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
//     * and third as in the setRotationAngles method.
//     */
//    public void setLivingAnimations(LivingEntity LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTickTime)
//    {
//        //this.feather.rotateAngleX = -0.2214F;
//        this.ThrushBody.rotateAngleX = 0.4937F;
//        this.ThrushWingL1.rotateAngleX = -((float)Math.PI * 2F / 9F);
//        this.ThrushWingL1.rotateAngleY = -(float)Math.PI;
//        this.ThrushWingR1.rotateAngleX = -((float)Math.PI * 2F / 9F);
//        this.ThrushWingR1.rotateAngleY = -(float)Math.PI;
//        this.ThrushLegL1.rotateAngleX = -0.0299F;
//        this.ThrushLegR1.rotateAngleX = -0.0299F;
//        this.ThrushLegL1.rotationPointY = 1.5F;
//        this.ThrushLegR1.rotationPointY = 1.5F;
//
//        if (LivingEntityIn instanceof EntityTMThrush)
//        {
//            EntityTMThrush entitythrush = (EntityTMThrush)LivingEntityIn;
//
//            if (entitythrush.isPartying())
//            {
//                this.ThrushLegL1.rotateAngleZ = -0.34906584F;
//                this.ThrushLegR1.rotateAngleZ = 0.34906584F;
//                this.state = ModelThrush.State.PARTY;
//                return;
//            }
//
//            if (entitythrush.isSitting())
//            {
//                float f = 1.9F;
//                this.ThrushHead.rotationPointY = -0.1F;
//                this.ThrushTail1.rotateAngleX = 1.5388988F;
//                this.ThrushTail1.rotationPointY = 1.1F;
//                this.ThrushBody.rotationPointY = 20.3F;
//                this.ThrushWingL1.rotateAngleZ = -0.0873F;
//                this.ThrushWingL1.rotationPointY = 0.0F;
//                this.ThrushWingR1.rotateAngleZ = 0.0873F;
//                this.ThrushWingR1.rotationPointY = 0.0F;
//                ++this.ThrushLegL1.rotationPointY;
//                ++this.ThrushLegR1.rotationPointY;
//                ++this.ThrushLegL1.rotateAngleX;
//                ++this.ThrushLegR1.rotateAngleX;
//                this.state = ModelThrush.State.SITTING;
//            }
//            else if (entitythrush.isFlying())
//            {
//                this.ThrushLegL1.rotateAngleX += ((float)Math.PI * 2F / 9F);
//                this.ThrushLegR1.rotateAngleX += ((float)Math.PI * 2F / 9F);
//                this.state = ModelThrush.State.FLYING;
//            }
//            else
//            {
//                this.state = ModelThrush.State.STANDING;
//            }
//
//            this.ThrushLegL1.rotateAngleZ = 0.0F;
//            this.ThrushLegR1.rotateAngleZ = 0.0F;
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    static enum State
//    {
//        FLYING,
//        STANDING,
//        SITTING,
//        PARTY
//    }
//}