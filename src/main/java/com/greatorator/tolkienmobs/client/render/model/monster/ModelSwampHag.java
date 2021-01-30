//package com.greatorator.tolkienmobs.client.render.model.monster;
//
//import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.math.MathHelper;
//
///** ModelSwampHag - GreatOrator */
//public class ModelSwampHag extends ModelTTM {
//    public ModelRenderer villagerHead;
//    public ModelRenderer villagerArmsR;
//    public ModelRenderer villagerArmsL;
//    public ModelRenderer villagerArms;
//    public ModelRenderer leftVillagerLeg;
//    public ModelRenderer villagerBody;
//    public ModelRenderer villagerClothes;
//    public ModelRenderer rightVillagerLeg;
//    public ModelRenderer villagerNose;
//    public ModelRenderer witchHat;
//    public ModelRenderer mole;
//    public ModelRenderer witchHat2;
//    public ModelRenderer witchHat3;
//    public ModelRenderer witchHat4;
//
//    public boolean holdingItem;
//
//    public ModelSwampHag() {
//        this.textureWidth = 64;
//        this.textureHeight = 128;
//        this.villagerHead = new ModelRenderer(this, 0, 0);
//        this.villagerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.villagerHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
//        this.villagerNose = new ModelRenderer(this, 24, 0);
//        this.villagerNose.setRotationPoint(0.0F, -2.0F, 0.0F);
//        this.villagerNose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
//        this.setRotateAngle(villagerNose, 0.0F, 0.0F, 0.04363323129985824F);
//        this.villagerClothes = new ModelRenderer(this, 0, 38);
//        this.villagerClothes.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.villagerClothes.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
//        this.mole = new ModelRenderer(this, 0, 0);
//        this.mole.mirror = true;
//        this.mole.setRotationPoint(0.0F, -2.0F, 0.0F);
//        this.mole.addBox(0.0F, 3.0F, -6.75F, 1, 1, 1, 0.0F);
//        this.rightVillagerLeg = new ModelRenderer(this, 0, 22);
//        this.rightVillagerLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
//        this.rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
//        this.witchHat = new ModelRenderer(this, 0, 64);
//        this.witchHat.setRotationPoint(-5.0F, -10.03F, -5.0F);
//        this.witchHat.addBox(0.0F, 0.0F, 0.0F, 10, 2, 10, 0.0F);
//        this.villagerArms = new ModelRenderer(this, 40, 38);
//        this.villagerArms.setRotationPoint(0.0F, 3.0F, -1.0F);
//        this.villagerArms.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
//        this.setRotateAngle(villagerArms, -0.7499679795819634F, 0.0F, 0.0F);
//        this.leftVillagerLeg = new ModelRenderer(this, 0, 22);
//        this.leftVillagerLeg.mirror = true;
//        this.leftVillagerLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
//        this.leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
//        this.villagerBody = new ModelRenderer(this, 16, 20);
//        this.villagerBody.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.villagerBody.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
//        this.villagerArmsR = new ModelRenderer(this, 44, 22);
//        this.villagerArmsR.setRotationPoint(0.0F, 3.0F, -1.0F);
//        this.villagerArmsR.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
//        this.setRotateAngle(villagerArmsR, -0.7499679795819634F, 0.0F, 0.0F);
//        this.witchHat3 = new ModelRenderer(this, 0, 87);
//        this.witchHat3.setRotationPoint(1.75F, -4.0F, 2.0F);
//        this.witchHat3.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
//        this.setRotateAngle(witchHat3, -0.10471975511965977F, 0.0F, 0.05235987755982988F);
//        this.witchHat2 = new ModelRenderer(this, 0, 76);
//        this.witchHat2.setRotationPoint(1.75F, -4.0F, 2.0F);
//        this.witchHat2.addBox(0.0F, 0.0F, 0.0F, 7, 4, 7, 0.0F);
//        this.setRotateAngle(witchHat2, -0.05235987755982988F, 0.0F, 0.02617993877991494F);
//        this.villagerArmsL = new ModelRenderer(this, 44, 22);
//        this.villagerArmsL.setRotationPoint(0.0F, 3.0F, -1.0F);
//        this.villagerArmsL.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
//        this.setRotateAngle(villagerArmsL, -0.7499679795819634F, 0.0F, 0.0F);
//        this.witchHat4 = new ModelRenderer(this, 0, 95);
//        this.witchHat4.setRotationPoint(1.75F, -2.0F, 2.0F);
//        this.witchHat4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(witchHat4, -0.20943951023931953F, 0.0F, 0.10471975511965977F);
//        this.villagerHead.addChild(this.villagerNose);
//        this.villagerNose.addChild(this.mole);
//        this.villagerHead.addChild(this.witchHat);
//        this.witchHat2.addChild(this.witchHat3);
//        this.witchHat.addChild(this.witchHat2);
//        this.witchHat3.addChild(this.witchHat4);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.villagerHead.render(f5);
//        this.villagerClothes.render(f5);
//        this.rightVillagerLeg.render(f5);
//        this.villagerArms.render(f5);
//        this.leftVillagerLeg.render(f5);
//        this.villagerBody.render(f5);
//        this.villagerArmsR.render(f5);
//        this.villagerArmsL.render(f5);
//    }
//
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
//    {
//        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
//        this.villagerHead.rotateAngleY = netHeadYaw * 0.017453292F;
//        this.villagerHead.rotateAngleX = headPitch * 0.017453292F;
//        this.villagerArmsL.rotationPointY = 3.0F;
//        this.villagerArmsL.rotationPointZ = -1.0F;
//        this.villagerArmsL.rotateAngleX = -0.75F;
//        this.villagerArmsR.rotationPointY = 3.0F;
//        this.villagerArmsR.rotationPointZ = -1.0F;
//        this.villagerArmsR.rotateAngleX = -0.75F;
//        this.rightVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
//        this.leftVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
//        this.rightVillagerLeg.rotateAngleY = 0.0F;
//        this.leftVillagerLeg.rotateAngleY = 0.0F;
//
//        this.villagerNose.offsetX = 0.0F;
//        this.villagerNose.offsetY = 0.0F;
//        this.villagerNose.offsetZ = 0.0F;
//        float f = 0.01F * (float)(entityIn.getEntityId() % 10);
//        this.villagerNose.rotateAngleX = MathHelper.sin((float)entityIn.ticksExisted * f) * 4.5F * 0.017453292F;
//        this.villagerNose.rotateAngleY = 0.0F;
//        this.villagerNose.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * f) * 2.5F * 0.017453292F;
//
//        if (this.holdingItem)
//        {
//            this.villagerNose.rotateAngleX = -0.9F;
//            this.villagerNose.offsetZ = -0.09375F;
//            this.villagerNose.offsetY = 0.1875F;
//        }
//    }
//}