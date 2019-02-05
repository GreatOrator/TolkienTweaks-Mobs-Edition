package com.greatorator.tolkienmobs.client.render.model.special;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * ModelGollum - GreatOrator
 * Created using Tabula 7.0.0
 */
public class ModelGollum extends ModelTolkienMobs {
    public ModelRenderer GollumLegL;
    public ModelRenderer GollumHead;
    public ModelRenderer GollumBody;
    public ModelRenderer GollumLegR;
    public ModelRenderer GollumPants;
    public ModelRenderer bipedRightArmLower;
    public ModelRenderer GollumLegCalfL;
    public ModelRenderer GollumFootL;
    public ModelRenderer GollumEarL;
    public ModelRenderer GollumEarR;
    public ModelRenderer GollumHair;
    public ModelRenderer GollumBodyLower;
    public ModelRenderer GollumBody_1;
    public ModelRenderer GollumLegCalfR;
    public ModelRenderer GollumFootR;
    public ModelRenderer bipedLeftArmLower;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightArm;

    public ModelGollum() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedLeftArm = new ModelRenderer(this, 56, 48);
        this.bipedLeftArm.setRotationPoint(3.0F, 8.5F, -3.5F);
        this.bipedLeftArm.addBox(0.0F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(bipedLeftArm, -0.2617993877991494F, 0.0F, 0.0F);
        this.GollumBody_1 = new ModelRenderer(this, 38, 46);
        this.GollumBody_1.setRotationPoint(1.5F, -1.5F, 0.2F);
        this.GollumBody_1.addBox(-3.0F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(GollumBody_1, -0.12217304763960307F, 0.0F, 0.0F);
        this.GollumHead = new ModelRenderer(this, 10, 34);
        this.GollumHead.setRotationPoint(0.0F, 6.5F, -6.5F);
        this.GollumHead.addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7, 0.0F);
        this.bipedRightArmLower = new ModelRenderer(this, 56, 56);
        this.bipedRightArmLower.mirror = true;
        this.bipedRightArmLower.setRotationPoint(-1.0F, 4.5F, 0.0F);
        this.bipedRightArmLower.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(bipedRightArmLower, -0.2617993877991494F, 0.0F, 0.0F);
        this.GollumLegR = new ModelRenderer(this, 0, 40);
        this.GollumLegR.mirror = true;
        this.GollumLegR.setRotationPoint(-2.3F, 15.5F, 0.0F);
        this.GollumLegR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(GollumLegR, -0.7853981633974483F, 0.0F, 0.0F);
        this.GollumBodyLower = new ModelRenderer(this, 38, 39);
        this.GollumBodyLower.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.GollumBodyLower.addBox(-3.0F, 0.0F, -1.5F, 6, 4, 3, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 56, 48);
        this.bipedRightArm.mirror = true;
        this.bipedRightArm.setRotationPoint(-3.0F, 8.5F, -3.5F);
        this.bipedRightArm.addBox(-2.0F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(bipedRightArm, -0.2617993877991494F, 0.0F, 0.0F);
        this.GollumHair = new ModelRenderer(this, 10, 48);
        this.GollumHair.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GollumHair.addBox(-4.0F, -7.1F, -4.0F, 8, 8, 8, 0.0F);
        this.GollumLegCalfR = new ModelRenderer(this, 0, 48);
        this.GollumLegCalfR.mirror = true;
        this.GollumLegCalfR.setRotationPoint(0.0F, 6.0F, -1.0F);
        this.GollumLegCalfR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(GollumLegCalfR, 1.5707963267948966F, 0.0F, 0.0F);
        this.GollumEarR = new ModelRenderer(this, 0, 36);
        this.GollumEarR.setRotationPoint(-2.8F, -3.0F, 0.0F);
        this.GollumEarR.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 2, 0.0F);
        this.setRotateAngle(GollumEarR, -0.4363323129985824F, -0.2617993877991494F, -0.2617993877991494F);
        this.GollumEarL = new ModelRenderer(this, 0, 36);
        this.GollumEarL.mirror = true;
        this.GollumEarL.setRotationPoint(2.8F, -3.0F, 0.0F);
        this.GollumEarL.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 2, 0.0F);
        this.setRotateAngle(GollumEarL, -0.4363323129985824F, 0.2617993877991494F, 0.2617993877991494F);
        this.bipedLeftArmLower = new ModelRenderer(this, 56, 56);
        this.bipedLeftArmLower.setRotationPoint(1.0F, 4.5F, 0.0F);
        this.bipedLeftArmLower.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(bipedLeftArmLower, -0.2617993877991494F, 0.0F, 0.0F);
        this.GollumPants = new ModelRenderer(this, 42, 31);
        this.GollumPants.setRotationPoint(0.0F, 14.8F, 0.7F);
        this.GollumPants.addBox(-3.5F, 0.0F, -2.3F, 7, 4, 4, 0.0F);
        this.GollumBody = new ModelRenderer(this, 38, 46);
        this.GollumBody.setRotationPoint(0.0F, 7.0F, -4.4F);
        this.GollumBody.addBox(-3.0F, 0.0F, -1.5F, 6, 7, 3, 0.0F);
        this.setRotateAngle(GollumBody, 0.5235987755982988F, 0.0F, 0.0F);
        this.GollumLegL = new ModelRenderer(this, 0, 40);
        this.GollumLegL.setRotationPoint(2.3F, 15.5F, 0.0F);
        this.GollumLegL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(GollumLegL, -0.7853981633974483F, 0.0F, 0.0F);
        this.GollumFootL = new ModelRenderer(this, 0, 56);
        this.GollumFootL.setRotationPoint(0.0F, 5.7F, 4.3F);
        this.GollumFootL.addBox(-1.5F, -1.0F, 0.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(GollumFootL, -0.7853981633974483F, 0.0F, 0.0F);
        this.GollumLegCalfL = new ModelRenderer(this, 0, 48);
        this.GollumLegCalfL.setRotationPoint(0.0F, 6.0F, -1.0F);
        this.GollumLegCalfL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(GollumLegCalfL, 1.5707963267948966F, 0.0F, 0.0F);
        this.GollumFootR = new ModelRenderer(this, 0, 56);
        this.GollumFootR.mirror = true;
        this.GollumFootR.setRotationPoint(0.0F, 5.7F, 4.3F);
        this.GollumFootR.addBox(-1.5F, -1.0F, 0.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(GollumFootR, -0.7853981633974483F, 0.0F, 0.0F);
        this.GollumBody.addChild(this.GollumBody_1);
        this.bipedRightArm.addChild(this.bipedRightArmLower);
        this.GollumBody.addChild(this.GollumBodyLower);
        this.GollumHead.addChild(this.GollumHair);
        this.GollumLegR.addChild(this.GollumLegCalfR);
        this.GollumHead.addChild(this.GollumEarR);
        this.GollumHead.addChild(this.GollumEarL);
        this.bipedLeftArm.addChild(this.bipedLeftArmLower);
        this.GollumLegL.addChild(this.GollumFootL);
        this.GollumLegL.addChild(this.GollumLegCalfL);
        this.GollumLegR.addChild(this.GollumFootR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaleFactor = 1.0F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

        this.bipedLeftArm.render(f5);
        this.GollumHead.render(f5);
        this.GollumLegR.render(f5);
        this.bipedRightArm.render(f5);
        this.GollumPants.render(f5);
        this.GollumBody.render(f5);
        this.GollumLegL.render(f5);

        GL11.glPopMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        float speed = 0.6662F; // Limb Swing Speed
        float angle = 1.4F; // How far the Limb swings
        float baseLegRotation = -0.7853981633974483F; // needs to be the original value of arm.rotateAngleX
        float baseArmRotation = -0.2617993877991494F; // needs to be the original value of leg.rotateAngleX

        this.GollumLegL.rotateAngleX = baseLegRotation + (MathHelper.cos(limbSwing * speed)) * angle * limbSwingAmount;
        this.GollumLegR.rotateAngleX = baseLegRotation + (MathHelper.cos(limbSwing * speed + (float) Math.PI)) * angle * limbSwingAmount;
        this.bipedRightArm.rotateAngleX = baseArmRotation + (MathHelper.cos(limbSwing * speed)) * angle * limbSwingAmount;
        this.bipedLeftArm.rotateAngleX = baseArmRotation + (MathHelper.cos(limbSwing * speed + (float) Math.PI)) * angle * limbSwingAmount;

        this.GollumHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.GollumHead.rotateAngleX = headPitch * 0.017453292F;
    }
}