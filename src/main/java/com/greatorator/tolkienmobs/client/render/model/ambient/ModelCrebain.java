package com.greatorator.tolkienmobs.client.render.model.ambient;

import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMCrebain;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/* Crebain - GreatOrator */
@SideOnly(Side.CLIENT)
public class ModelCrebain extends ModelTTM {
    public ModelRenderer CrebainBody;
    public ModelRenderer CrebainBodyWingless;
    public ModelRenderer CrebainTail1;
    public ModelRenderer CrebainLegL1;
    public ModelRenderer CrebainLegR1;
    public ModelRenderer CrebainHead;
    public ModelRenderer CrebainWingL1;
    public ModelRenderer CrebainWingR1;
    public ModelRenderer CrebainTail2;
    public ModelRenderer CrebainTail3;
    public ModelRenderer CrebainLegL2;
    public ModelRenderer CrebainLegR2;
    public ModelRenderer CrebainBeakTop;
    public ModelRenderer CrebainBeakBottom;
    public ModelRenderer CrebainWingL2;
    public ModelRenderer CrebainWingR2;
    private ModelCrebain.State state = ModelCrebain.State.STANDING;

    protected float[][] perchedCycle = new float[][] {
                    // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
                    { -70F, 70F, 70F, 0F, 0F, 0F, 90F, 0F }
    };

    protected float[][] takingOffCycle = new float[][] {
                    // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
                    { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
                    { -4F, 4F, 70F, -10F, 0F, 0F, 15F, -15F },
                    { -2F, 2F, 70F, -10F, 0F, 0F, 10F, -10F },
                    { -1F, 1F, 70F, -10F, 0F, 0F, 5F, -5F },
                    { 0F, 0F, 70F, -10F, 0F, 0F, 0F, 0F },
                    { 1F, -1F, 70F, -10F, 0F, 0F, -5F, 5F },
                    { 2F, -2F, 70F, -10F, 0F, 0F, -10F, 10F },
                    { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
                    { 5F, -5F, 70F, -10F, 0F, 0F, -20F, 20F },
                    { 6F, -6F, 70F, -10F, 0F, 0F, -25F, 20F },
                    { 7F, -7F, 70F, -10F, 0F, 0F, -30F, 20F },
                    { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
                    { 0F, 0F, 70F, -10F, 0F, 0F, -0F, 0F },
                    { -3F, 3F, 70F, -10F, 0F, 0F, 10F, -10F },
                    { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
                    { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
                    { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
                    { -12F, 12F, 70F, -10F, 0F, 0F, 50F, -20F },
                    { -10F, 10F, 70F, -10F, 0F, 0F, 45F, -20F },
                    { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
                    { -8F, 8F, 70F, -10F, 0F, 0F, 35F, -20F },
                    { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
                    { -6F, 6F, 70F, -10F, 0F, 0F, 25F, -20F }
    };

    protected float[][] soaringCycle = new float[][] {
                    // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
                    { 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F }
    };

    protected float[][] divingCycle = new float[][] {
                    // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
                    { 90F, 0F, 0F, 0F, 0F, -50F, 0F, 0F }
    };

    protected float[][] attackingCycle = new float[][] {
                    // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
                    { 90F, 0F, 0F, 0F, 0F, -50F, 0F, 0F }
    };

    protected float[][] landingCycle = new float[][] {
                    // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
                    { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
    };

    protected float[][] travellingCycle = new float[][] {
                    // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
                    { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
                    { -4F, 4F, 70F, -10F, 0F, 0F, 15F, -15F },
                    { -2F, 2F, 70F, -10F, 0F, 0F, 10F, -10F },
                    { -1F, 1F, 70F, -10F, 0F, 0F, 5F, -5F },
                    { 0F, 0F, 70F, -10F, 0F, 0F, 0F, 0F },
                    { 1F, -1F, 70F, -10F, 0F, 0F, -5F, 5F },
                    { 2F, -2F, 70F, -10F, 0F, 0F, -10F, 10F },
                    { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
                    { 5F, -5F, 70F, -10F, 0F, 0F, -20F, 20F },
                    { 6F, -6F, 70F, -10F, 0F, 0F, -25F, 20F },
                    { 7F, -7F, 70F, -10F, 0F, 0F, -30F, 20F },
                    { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
                    { 0F, 0F, 70F, -10F, 0F, 0F, -0F, 0F },
                    { -3F, 3F, 70F, -10F, 0F, 0F, 10F, -10F },
                    { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
                    { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
                    { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
                    { -12F, 12F, 70F, -10F, 0F, 0F, 50F, -20F },
                    { -10F, 10F, 70F, -10F, 0F, 0F, 45F, -20F },
                    { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
                    { -8F, 8F, 70F, -10F, 0F, 0F, 35F, -20F },
                    { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
                    { -6F, 6F, 70F, -10F, 0F, 0F, 25F, -20F }
    };

    public ModelCrebain() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.CrebainWingL2 = new ModelRenderer(this, 2, 9);
        this.CrebainWingL2.setRotationPoint(5.0F, -1.0F, -1.5F);
        this.CrebainWingL2.addBox(0.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.CrebainWingR1 = new ModelRenderer(this, 0, 9);
        this.CrebainWingR1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.CrebainWingR1.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.CrebainTail3 = new ModelRenderer(this, -3, 9);
        this.CrebainTail3.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.CrebainTail3.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(CrebainTail3, 0.0F, -0.2665117767795341F, 0.0F);
        this.CrebainBody = new ModelRenderer(this, 0, 0);
        this.CrebainBody.setRotationPoint(0.0F, 20.1F, 0.5F);
        this.CrebainBody.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(CrebainBody, -0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainWingL1 = new ModelRenderer(this, 0, 9);
        this.CrebainWingL1.setRotationPoint(1.0F, 1.0F, 1.3F);
        this.CrebainWingL1.addBox(1.0F, -1.0F, -3.0F, 4, 0, 3, 0.0F);
        this.CrebainBeakBottom = new ModelRenderer(this, 0, 0);
        this.CrebainBeakBottom.setRotationPoint(0.0F, 0.4F, 0.0F);
        this.CrebainBeakBottom.addBox(-0.5F, -0.9F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(CrebainBeakBottom, -0.08726646259971647F, 0.0F, 0.0F);
        this.CrebainWingR2 = new ModelRenderer(this, 2, 9);
        this.CrebainWingR2.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.CrebainWingR2.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.CrebainLegL1 = new ModelRenderer(this, 0, 3);
        this.CrebainLegL1.setRotationPoint(1.3F, 1.5F, 0.0F);
        this.CrebainLegL1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.CrebainLegR2 = new ModelRenderer(this, 0, 5);
        this.CrebainLegR2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.CrebainLegR2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(CrebainLegR2, -1.3613568165555772F, 0.0F, 0.0F);
        this.CrebainLegR1 = new ModelRenderer(this, 0, 3);
        this.CrebainLegR1.setRotationPoint(-0.3F, 1.5F, 0.0F);
        this.CrebainLegR1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.CrebainTail1 = new ModelRenderer(this, -4, 9);
        this.CrebainTail1.setRotationPoint(0.0F, -0.8F, 4.3F);
        this.CrebainTail1.addBox(-0.5F, -0.5F, -1.5F, 2, 0, 4, 0.0F);
        this.setRotateAngle(CrebainTail1, 0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainTail2 = new ModelRenderer(this, -3, 9);
        this.CrebainTail2.setRotationPoint(1.0F, 0.0F, -1.5F);
        this.CrebainTail2.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(CrebainTail2, 0.0F, 0.2617993877991494F, 0.0F);
        this.CrebainHead = new ModelRenderer(this, 12, 0);
        this.CrebainHead.setRotationPoint(0.5F, -0.1F, -3.0F);
        this.CrebainHead.addBox(-1.0F, -1.5F, -2.5F, 2, 2, 3, 0.0F);
        this.setRotateAngle(CrebainHead, 0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainLegL2 = new ModelRenderer(this, 0, 5);
        this.CrebainLegL2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.CrebainLegL2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(CrebainLegL2, -1.3613568165555772F, 0.0F, 0.0F);
        this.CrebainBeakTop = new ModelRenderer(this, 0, 0);
        this.CrebainBeakTop.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.CrebainBeakTop.addBox(-0.5F, -0.8F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(CrebainBeakTop, 0.08726646259971647F, 0.0F, 0.0F);
        this.CrebainWingL1.addChild(this.CrebainWingL2);
        this.CrebainBody.addChild(this.CrebainWingR1);
        this.CrebainTail1.addChild(this.CrebainTail3);
        this.CrebainBody.addChild(this.CrebainWingL1);
        this.CrebainBeakTop.addChild(this.CrebainBeakBottom);
        this.CrebainWingR1.addChild(this.CrebainWingR2);
        this.CrebainBody.addChild(this.CrebainLegL1);
        this.CrebainLegR1.addChild(this.CrebainLegR2);
        this.CrebainBody.addChild(this.CrebainLegR1);
        this.CrebainBody.addChild(this.CrebainTail1);
        this.CrebainTail1.addChild(this.CrebainTail2);
        this.CrebainBody.addChild(this.CrebainHead);
        this.CrebainLegL1.addChild(this.CrebainLegL2);
        this.CrebainHead.addChild(this.CrebainBeakTop);

        this.CrebainBodyWingless = new ModelRenderer(this, 0, 0);
        this.CrebainBodyWingless.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.CrebainBodyWingless.setRotationPoint(0.0F, 20.1F, 0.5F);
        this.CrebainBodyWingless.setTextureSize(textureWidth, textureHeight);
        this.CrebainBodyWingless.mirror = true;
        this.setRotateAngle(CrebainBodyWingless, -0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainBodyWingless.addChild(CrebainHead);
        this.CrebainBodyWingless.addChild(CrebainLegL1);
        this.CrebainBodyWingless.addChild(CrebainLegR1);
        this.CrebainBodyWingless.addChild(CrebainTail1);
    }

    @Override
    public void render(Entity parEntity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        if (this.state != State.STANDING) {
            this.CrebainBody.render(f5);
        }else {
            this.CrebainBodyWingless.render(f5);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        float f = ageInTicks * 0.3F;
        this.CrebainHead.rotateAngleX = headPitch * 0.017453292F;
        this.CrebainHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.CrebainHead.rotateAngleZ = 0.0F;
        this.CrebainHead.rotationPointX = 0.5F;
        this.CrebainBody.rotationPointX = 0.0F;
        this.CrebainTail1.rotationPointX = 0.0F;
        this.CrebainWingR1.rotationPointX = 1.0F;
        this.CrebainWingL1.rotationPointX = 1.0F;

        if (this.state != State.FLYING)
        {
            if (this.state == State.SITTING)
            {
                return;
            }

            if (this.state == State.PARTY)
            {
                float f1 = MathHelper.cos((float)entityIn.ticksExisted);
                float f2 = MathHelper.sin((float)entityIn.ticksExisted);
                this.CrebainHead.rotationPointX = f1;
                this.CrebainHead.rotationPointY = 15.69F + f2;
                this.CrebainHead.rotateAngleX = 0.0F;
                this.CrebainHead.rotateAngleY = 0.0F;
                this.CrebainHead.rotateAngleZ = MathHelper.sin((float)entityIn.ticksExisted) * 0.4F;
                this.CrebainBody.rotationPointX = f1;
                this.CrebainBody.rotationPointY = -0.1F + f2;
                this.CrebainWingL1.rotateAngleZ = -0.0873F - ageInTicks;
                this.CrebainWingL1.rotationPointX = 1.0F + f1;
                this.CrebainWingL1.rotationPointY = 1.65F + f2;
                this.CrebainWingL1.rotationPointZ = -0.3F + f1;
                this.CrebainWingR1.rotateAngleZ = 0.0873F + ageInTicks;
                this.CrebainWingR1.rotationPointX = -1.0F + f1;
                this.CrebainWingR1.rotationPointY = -0.05F + f2;
                this.CrebainTail1.rotationPointX = f1;
                this.CrebainTail1.rotationPointY = -0.8F + f2;
                return;
            }

            this.CrebainLegL1.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.CrebainLegR1.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        }

        this.CrebainHead.rotationPointY = f;
        this.CrebainTail1.rotateAngleX = 1.015F + MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
        this.CrebainTail1.rotationPointY = -0.8F + f;
        this.CrebainBody.rotationPointY = -0.1F + f;
        this.CrebainWingL1.rotateAngleZ = -0.0873F - ageInTicks;
        this.CrebainWingL1.rotationPointY = 1.65F + f;
        this.CrebainWingL1.rotationPointZ = -0.3F + f;
        this.CrebainWingR1.rotateAngleZ = 0.0873F + ageInTicks;
        this.CrebainWingR1.rotationPointY = -0.05F + f;
        this.CrebainLegL1.rotationPointY = 1.5F + f;
        this.CrebainLegR1.rotationPointY = 1.5F + f;
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        //this.feather.rotateAngleX = -0.2214F;
        this.CrebainBody.rotateAngleX = 0.4937F;
        this.CrebainWingL1.rotateAngleX = -((float)Math.PI * 2F / 9F);
        this.CrebainWingL1.rotateAngleY = -(float)Math.PI;
        this.CrebainWingR1.rotateAngleX = -((float)Math.PI * 2F / 9F);
        this.CrebainWingR1.rotateAngleY = -(float)Math.PI;
        this.CrebainLegL1.rotateAngleX = -0.0299F;
        this.CrebainLegR1.rotateAngleX = -0.0299F;
        this.CrebainLegL1.rotationPointY = 1.5F;
        this.CrebainLegR1.rotationPointY = 1.5F;

        if (entitylivingbaseIn instanceof EntityTMCrebain)
        {
            EntityTMCrebain entitycrebain = (EntityTMCrebain)entitylivingbaseIn;

            if (entitycrebain.isPartying())
            {
                this.CrebainLegL1.rotateAngleZ = -0.34906584F;
                this.CrebainLegR1.rotateAngleZ = 0.34906584F;
                this.state = ModelCrebain.State.PARTY;
                return;
            }

            if (entitycrebain.isSitting())
            {
                float f = 1.9F;
                this.CrebainHead.rotationPointY = -0.1F;
                this.CrebainTail1.rotateAngleX = 1.5388988F;
                this.CrebainTail1.rotationPointY = 1.1F;
                this.CrebainBody.rotationPointY = 20.3F;
                this.CrebainWingL1.rotateAngleZ = -0.0873F;
                this.CrebainWingL1.rotationPointY = 0.0F;
                this.CrebainWingR1.rotateAngleZ = 0.0873F;
                this.CrebainWingR1.rotationPointY = 0.0F;
                ++this.CrebainLegL1.rotationPointY;
                ++this.CrebainLegR1.rotationPointY;
                ++this.CrebainLegL1.rotateAngleX;
                ++this.CrebainLegR1.rotateAngleX;
                this.state = ModelCrebain.State.SITTING;
            }
            else if (entitycrebain.isFlying())
            {
                this.CrebainLegL1.rotateAngleX += ((float)Math.PI * 2F / 9F);
                this.CrebainLegR1.rotateAngleX += ((float)Math.PI * 2F / 9F);
                this.state = ModelCrebain.State.FLYING;
            }
            else
            {
                this.state = ModelCrebain.State.STANDING;
            }

            this.CrebainLegL1.rotateAngleZ = 0.0F;
            this.CrebainLegR1.rotateAngleZ = 0.0F;
        }
    }

    @SideOnly(Side.CLIENT)
    static enum State
    {
        FLYING,
        STANDING,
        SITTING,
        PARTY
    }
}