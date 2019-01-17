package com.greatorator.tolkienmobs.client.render.model.boss;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import com.greatorator.tolkienmobs.entity.boss.EntityWitchKing;
import net.minecraft.client.model.ModelBiped;
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
 * WitchKing - GreatOrator
 */
public class ModelWitchKing extends ModelTolkienMobs {
    public ModelRenderer witchArmorLeft;
    public ModelRenderer witchArmor2Right;
    public ModelRenderer witchArmorRight;
    public ModelRenderer witchHelm;
    public ModelRenderer witchArmor2Left;
    public ModelRenderer bipedRightArm;
    public ModelRenderer witchRightLeg;
    public ModelRenderer witchHead;
    public ModelRenderer witchBody;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer witchLeftLeg;
    public ModelRenderer witchChest;

    public ModelWitchKing() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.witchArmor2Right = new ModelRenderer(this, 0, 32);
        this.witchArmor2Right.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.witchArmor2Right.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.witchArmorRight = new ModelRenderer(this, 40, 32);
        this.witchArmorRight.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.witchArmorRight.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.witchHelm = new ModelRenderer(this, 32, 0);
        this.witchHelm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.witchHelm.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.witchArmor2Left = new ModelRenderer(this, 0, 48);
        this.witchArmor2Left.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.witchArmor2Left.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.witchHead = new ModelRenderer(this, 0, 0);
        this.witchHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.witchHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.witchLeftLeg = new ModelRenderer(this, 16, 48);
        this.witchLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.witchLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.witchBody = new ModelRenderer(this, 16, 16);
        this.witchBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.witchBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.witchArmorLeft = new ModelRenderer(this, 48, 48);
        this.witchArmorLeft.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.witchArmorLeft.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.witchRightLeg = new ModelRenderer(this, 0, 16);
        this.witchRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.witchRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.witchChest = new ModelRenderer(this, 16, 32);
        this.witchChest.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.witchChest.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaleFactor = 1.6F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

        this.witchHead.render(f5);
        this.witchBody.render(f5);
        this.bipedLeftArm.render(f5);
        this.witchRightLeg.render(f5);
        this.bipedRightArm.render(f5);
        this.witchLeftLeg.render(f5);

        GL11.glPopMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        this.rightArmPose = ModelBiped.ArmPose.EMPTY;
        this.leftArmPose = ModelBiped.ArmPose.EMPTY;
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);

        if (itemstack.getItem() == Items.BOW && ((EntityWitchKing)entitylivingbaseIn).isSwingingArms())
        {
            if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT)
            {
                this.rightArmPose = ModelBiped.ArmPose.ITEM;
            }
            else
            {
                this.leftArmPose = ModelBiped.ArmPose.ITEM;
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

        this.witchLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.witchRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.witchHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.witchHead.rotateAngleX = headPitch * 0.017453292F;

        this.bipedRightArm.rotationPointX = -4.9F;
        this.bipedLeftArm.rotationPointX = 4.9F;
    }

    public void postRenderArm(float scale, EnumHandSide side)
    {
        float f = side == EnumHandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArmForSide(side);
        modelrenderer.rotationPointX += f;
        modelrenderer.postRender(scale);
        modelrenderer.rotationPointX -= f;
    }
}
