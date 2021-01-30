//package com.greatorator.tolkienmobs.client.render.model.items;
//
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//
///**
// *CustomArmor - GreatOrator
// */
//public class ModelMithrilArmor extends ModelBiped {
//    public ModelRenderer HelmMithril;
//    public ModelRenderer ChestMithril;
//    public ModelRenderer MithrilLA;
//    public ModelRenderer MithrilRA;
//    public ModelRenderer HelmMithrilPart;
//    public ModelRenderer HelmMithrilPart_1;
//    public ModelRenderer HelmMithrilPart_2;
//    public ModelRenderer HelmMithrilPart_3;
//    public ModelRenderer ChestMithrilPart;
//    public ModelRenderer MithrilLAPart;
//    public ModelRenderer MithrilRAPart;
//    public ModelRenderer MithrilLAPart_1;
//    public ModelRenderer MithrilRAPart_1;
//    public ModelRenderer MithrilRL;
//    public ModelRenderer MithrilLL;
//    public ModelRenderer MithrilBody;
//    public ModelRenderer MithrilRLPart;
//    public ModelRenderer MithrilLLPart;
//    public ModelRenderer MithrilRF;
//    public ModelRenderer MithrilLF;
//    public ModelRenderer MithrilRFPart;
//    public ModelRenderer MithrilLFPart;
//
//    public ModelMithrilArmor() {
//        this.textureWidth = 128;
//        this.textureHeight = 128;
//        this.MithrilRAPart_1 = new ModelRenderer(this, 102, 94);
//        this.MithrilRAPart_1.mirror = true;
//        this.MithrilRAPart_1.setRotationPoint(-1.0F, 8.5F, -1.0F);
//        this.MithrilRAPart_1.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6, 0.0F);
//        this.MithrilLAPart = new ModelRenderer(this, 102, 103);
//        this.MithrilLAPart.mirror = true;
//        this.MithrilLAPart.setRotationPoint(-0.5F, 2.0F, -0.5F);
//        this.MithrilLAPart.addBox(0.0F, 0.0F, 0.0F, 5, 9, 5, 0.0F);
//        this.HelmMithrilPart_1 = new ModelRenderer(this, 40, 118);
//        this.HelmMithrilPart_1.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.HelmMithrilPart_1.addBox(-1.0F, 4.5F, -0.5F, 10, 5, 5, 0.0F);
//        this.setRotateAngle(HelmMithrilPart_1, 0.5235987755982988F, 0.0F, 0.0F);
//        this.MithrilLA = new ModelRenderer(this, 102, 117);
//        this.MithrilLA.mirror = true;
//        this.MithrilLA.setRotationPoint(-1.0F, -2.0F, -2.0F);
//        this.MithrilLA.addBox(-1.0F, -0.3F, -1.0F, 6, 5, 6, 0.0F);
//        this.HelmMithrilPart_3 = new ModelRenderer(this, 40, 99);
//        this.HelmMithrilPart_3.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.HelmMithrilPart_3.addBox(3.0F, -1.0F, -1.0F, 2, 3, 10, 0.0F);
//        this.HelmMithrilPart_2 = new ModelRenderer(this, 40, 83);
//        this.HelmMithrilPart_2.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.HelmMithrilPart_2.addBox(-1.0F, 1.7F, 6.0F, 10, 3, 3, 0.0F);
//        this.MithrilLAPart_1 = new ModelRenderer(this, 102, 94);
//        this.MithrilLAPart_1.setRotationPoint(-1.0F, 8.5F, -1.0F);
//        this.MithrilLAPart_1.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6, 0.0F);
//        this.HelmMithrilPart = new ModelRenderer(this, 0, 100);
//        this.HelmMithrilPart.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.HelmMithrilPart.addBox(-1.0F, 4.0F, -1.0F, 10, 4, 10, 0.0F);
//        this.ChestMithril = new ModelRenderer(this, 70, 117);
//        this.ChestMithril.setRotationPoint(-4.0F, 0.0F, -2.0F);
//        this.ChestMithril.addBox(-1.0F, -0.4F, -1.0F, 10, 5, 6, 0.0F);
//        this.MithrilRA = new ModelRenderer(this, 102, 117);
//        this.MithrilRA.setRotationPoint(-3.0F, -2.0F, -2.0F);
//        this.MithrilRA.addBox(-1.0F, -0.3F, -1.0F, 6, 5, 6, 0.0F);
//        this.MithrilRAPart = new ModelRenderer(this, 102, 103);
//        this.MithrilRAPart.mirror = true;
//        this.MithrilRAPart.setRotationPoint(-0.5F, 2.0F, -0.5F);
//        this.MithrilRAPart.addBox(0.0F, 0.0F, 0.0F, 5, 9, 5, 0.0F);
//        this.ChestMithrilPart = new ModelRenderer(this, 70, 100);
//        this.ChestMithrilPart.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.ChestMithrilPart.addBox(-0.5F, 0.6F, -0.5F, 9, 12, 5, 0.0F);
//        this.HelmMithril = new ModelRenderer(this, 0, 86);
//        this.HelmMithril.setRotationPoint(-4.0F, -8.0F, -4.0F);
//        this.HelmMithril.addBox(-0.5F, -0.5F, -0.5F, 9, 5, 9, 0.0F);
//        this.MithrilLLPart = new ModelRenderer(this, 0, 97);
//        this.MithrilLLPart.setRotationPoint(-0.5F, 3.0F, -0.5F);
//        this.MithrilLLPart.addBox(-0.5F, 0.1F, -0.5F, 6, 8, 6, 0.0F);
//        this.MithrilRLPart = new ModelRenderer(this, 0, 97);
//        this.MithrilRLPart.setRotationPoint(-0.5F, 3.0F, -0.5F);
//        this.MithrilRLPart.addBox(-0.5F, 0.1F, -0.5F, 6, 8, 6, 0.0F);
//        this.MithrilLL = new ModelRenderer(this, 0, 112);
//        this.MithrilLL.setRotationPoint(-2.0F, 0.0F, -2.0F);
//        this.MithrilLL.addBox(-0.5F, 0.0F, -0.5F, 5, 11, 5, 0.0F);
//        this.MithrilRL = new ModelRenderer(this, 0, 112);
//        this.MithrilRL.setRotationPoint(-2.0F, 0.0F, -2.0F);
//        this.MithrilRL.addBox(-0.5F, 0.0F, -0.5F, 5, 11, 5, 0.0F);
//        this.MithrilBody = new ModelRenderer(this, 20, 118);
//        this.MithrilBody.setRotationPoint(-4.0F, 0.0F, -2.0F);
//        this.MithrilBody.addBox(-1.0F, 11.5F, -0.5F, 10, 5, 5, 0.0F);
//        this.MithrilLF = new ModelRenderer(this, 108, 0);
//        this.MithrilLF.setRotationPoint(-2.0F, 0.0F, -2.0F);
//        this.MithrilLF.addBox(-0.5F, 8.0F, -0.5F, 5, 4, 5, 0.0F);
//        this.MithrilRF = new ModelRenderer(this, 108, 0);
//        this.MithrilRF.setRotationPoint(-2.0F, 0.0F, -2.0F);
//        this.MithrilRF.addBox(-0.5F, 8.0F, -0.5F, 5, 4, 5, 0.0F);
//        this.MithrilLFPart = new ModelRenderer(this, 104, 9);
//        this.MithrilLFPart.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MithrilLFPart.addBox(-1.0F, 9.0F, -1.0F, 6, 3, 6, 0.0F);
//        this.MithrilRFPart = new ModelRenderer(this, 104, 9);
//        this.MithrilRFPart.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MithrilRFPart.addBox(-1.0F, 9.0F, -1.0F, 6, 3, 6, 0.0F);
//        this.MithrilLF.addChild(this.MithrilLFPart);
//        this.MithrilRF.addChild(this.MithrilRFPart);
//        this.MithrilLL.addChild(this.MithrilLLPart);
//        this.MithrilRL.addChild(this.MithrilRLPart);
//        this.MithrilRA.addChild(this.MithrilRAPart_1);
//        this.MithrilLA.addChild(this.MithrilLAPart);
//        this.HelmMithril.addChild(this.HelmMithrilPart_1);
//        this.HelmMithril.addChild(this.HelmMithrilPart_3);
//        this.HelmMithril.addChild(this.HelmMithrilPart_2);
//        this.MithrilLA.addChild(this.MithrilLAPart_1);
//        this.HelmMithril.addChild(this.HelmMithrilPart);
//        this.MithrilRA.addChild(this.MithrilRAPart);
//        this.ChestMithril.addChild(this.ChestMithrilPart);
//
//        this.bipedHead.addChild(HelmMithril);
//        this.bipedLeftArm.addChild(MithrilLA);
//        this.bipedRightArm.addChild(MithrilRA);
//        this.bipedBody.addChild(ChestMithril);
//        this.bipedBody.addChild(MithrilBody);
//        this.bipedRightLeg.addChild(MithrilRL);
//        this.bipedLeftLeg.addChild(MithrilLL);
//        this.bipedRightLeg.addChild(MithrilRF);
//        this.bipedLeftLeg.addChild(MithrilLF);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
//    {
//        super.render(entity, f, f1, f2, f3, f4, f5);
//    }
//
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
//    {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//}