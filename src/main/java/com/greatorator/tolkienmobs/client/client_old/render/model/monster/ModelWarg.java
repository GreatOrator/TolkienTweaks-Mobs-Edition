//package com.greatorator.tolkienmobs.client.render.model.monster;
//
//import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.util.math.MathHelper;
//
///**
// * Warg - GreatOrator
// */
//public class ModelWarg extends ModelTTM {
//    public ModelRenderer WargBody;
//    public ModelRenderer WargNeck;
//    public ModelRenderer WargLegFL;
//    public ModelRenderer WargLegFR;
//    public ModelRenderer WargLegRR;
//    public ModelRenderer WargLegRL;
//    public ModelRenderer chesttop;
//    public ModelRenderer back;
//    public ModelRenderer WargTail1;
//    public ModelRenderer back2;
//    public ModelRenderer WargTail2;
//    public ModelRenderer WargTail3;
//    public ModelRenderer WargTail4;
//    public ModelRenderer head;
//    public ModelRenderer neck2;
//    public ModelRenderer jaw;
//    public ModelRenderer ear1l;
//    public ModelRenderer snout1;
//    public ModelRenderer ear1r;
//    public ModelRenderer head2;
//    public ModelRenderer ear2l;
//    public ModelRenderer ear3l;
//    public ModelRenderer snout2;
//    public ModelRenderer ear2r;
//    public ModelRenderer ear3r;
//    public ModelRenderer leg2rf;
//    public ModelRenderer feetrf;
//    public ModelRenderer leg2lf;
//    public ModelRenderer feetlf;
//    public ModelRenderer leg2lb;
//    public ModelRenderer leg3lb;
//    public ModelRenderer feetlb;
//    public ModelRenderer leg2rb;
//    public ModelRenderer leg3rb;
//    public ModelRenderer feetrb;
//
//    public ModelWarg() {
//        this.textureWidth = 64;
//        this.textureHeight = 64;
//        this.ear1l = new ModelRenderer(this, 33, 4);
//        this.ear1l.setRotationPoint(-2.2F, -1.0F, -0.7F);
//        this.ear1l.addBox(-1.0F, -2.2F, 0.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(ear1l, -0.6108652381980153F, 0.6108652381980153F, -0.8726646259971648F);
//        this.leg3lb = new ModelRenderer(this, 56, 47);
//        this.leg3lb.mirror = true;
//        this.leg3lb.setRotationPoint(-0.1F, 3.2F, 0.6F);
//        this.leg3lb.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
//        this.setRotateAngle(leg3lb, -0.6373942428283291F, 0.0F, 0.0F);
//        this.leg2rb = new ModelRenderer(this, 52, 47);
//        this.leg2rb.setRotationPoint(1.4F, 2.8F, -1.1F);
//        this.leg2rb.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
//        this.setRotateAngle(leg2rb, 0.8196066167365371F, 0.0F, 0.0F);
//        this.jaw = new ModelRenderer(this, 24, 22);
//        this.jaw.setRotationPoint(0.5F, 2.1F, -3.2F);
//        this.jaw.addBox(-1.5F, -0.5F, -2.9F, 2, 1, 4, 0.0F);
//        this.setRotateAngle(jaw, -0.08726646259971647F, 0.0F, 0.0F);
//        this.ear2l = new ModelRenderer(this, 35, 0);
//        this.ear2l.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.ear2l.addBox(-0.5F, -3.2F, 0.0F, 1, 1, 1, 0.0F);
//        this.WargTail2 = new ModelRenderer(this, 40, 59);
//        this.WargTail2.setRotationPoint(1.0F, 0.8F, 1.8F);
//        this.WargTail2.addBox(-1.5F, -1.5F, 0.0F, 1, 1, 4, 0.0F);
//        this.setRotateAngle(WargTail2, -0.3331833542057175F, 0.0F, 0.0F);
//        this.WargBody = new ModelRenderer(this, 32, 0);
//        this.WargBody.setRotationPoint(-1.0F, 11.4F, -0.4F);
//        this.WargBody.addBox(-3.0F, -3.0F, -6.8F, 8, 9, 8, 0.0F);
//        this.head2 = new ModelRenderer(this, 19, 27);
//        this.head2.setRotationPoint(0.5F, 2.7F, 0.4F);
//        this.head2.addBox(-2.5F, -2.0F, -3.5F, 4, 2, 4, 0.0F);
//        this.ear2r = new ModelRenderer(this, 35, 0);
//        this.ear2r.mirror = true;
//        this.ear2r.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.ear2r.addBox(-0.5F, -3.2F, 0.0F, 1, 1, 1, 0.0F);
//        this.WargLegFR = new ModelRenderer(this, 50, 54);
//        this.WargLegFR.mirror = true;
//        this.WargLegFR.setRotationPoint(-4.1F, 14.2F, -4.3F);
//        this.WargLegFR.addBox(-1.0F, -2.4F, -1.5F, 3, 6, 4, 0.0F);
//        this.setRotateAngle(WargLegFR, -0.0F, 0.0F, 0.091106186954104F);
//        this.WargTail4 = new ModelRenderer(this, 42, 60);
//        this.WargTail4.setRotationPoint(0.0F, -0.2F, 3.0F);
//        this.WargTail4.addBox(-1.5F, -1.5F, 0.0F, 1, 1, 3, 0.0F);
//        this.setRotateAngle(WargTail4, 0.580496509213314F, 0.0F, 0.0F);
//        this.back2 = new ModelRenderer(this, 8, 0);
//        this.back2.setRotationPoint(0.5F, 3.1F, -0.3F);
//        this.back2.addBox(-2.5F, -2.5F, -0.2F, 5, 5, 7, 0.0F);
//        this.setRotateAngle(back2, 0.21327923459370707F, 0.0F, 0.0F);
//        this.ear1r = new ModelRenderer(this, 33, 4);
//        this.ear1r.mirror = true;
//        this.ear1r.setRotationPoint(2.2F, -1.0F, -0.7F);
//        this.ear1r.addBox(-1.0F, -2.2F, 0.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(ear1r, -0.6108652381980153F, -0.6108652381980153F, 0.8726646259971648F);
//        this.leg3rb = new ModelRenderer(this, 56, 47);
//        this.leg3rb.setRotationPoint(0.1F, 3.2F, 0.8F);
//        this.leg3rb.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
//        this.setRotateAngle(leg3rb, -0.6373942428283291F, 0.0F, 0.0F);
//        this.WargLegRL = new ModelRenderer(this, 50, 54);
//        this.WargLegRL.setRotationPoint(1.5F, 13.8F, 5.2F);
//        this.WargLegRL.addBox(0.0F, -1.9F, -2.0F, 3, 6, 4, 0.0F);
//        this.back = new ModelRenderer(this, 36, 17);
//        this.back.setRotationPoint(0.5F, 0.2F, 1.0F);
//        this.back.addBox(-2.5F, -2.5F, -0.2F, 6, 7, 8, 0.0F);
//        this.setRotateAngle(back, -0.1434660645139339F, 0.0F, 0.0F);
//        this.head = new ModelRenderer(this, 0, 40);
//        this.head.setRotationPoint(0.0F, -0.7F, -4.1F);
//        this.head.addBox(-2.5F, -2.0F, -3.5F, 5, 4, 5, 0.0F);
//        this.setRotateAngle(head, 0.8443902921148566F, 0.0F, 0.0F);
//        this.WargLegRR = new ModelRenderer(this, 50, 54);
//        this.WargLegRR.mirror = true;
//        this.WargLegRR.setRotationPoint(-1.5F, 13.8F, 5.2F);
//        this.WargLegRR.addBox(-3.0F, -1.9F, -2.0F, 3, 6, 4, 0.0F);
//        this.ear3l = new ModelRenderer(this, 27, 0);
//        this.ear3l.setRotationPoint(0.0F, -0.1F, 1.3F);
//        this.ear3l.addBox(-0.5F, -2.6F, -0.4F, 1, 3, 1, 0.0F);
//        this.setRotateAngle(ear3l, 0.27314402793711257F, 0.0F, 0.0F);
//        this.leg2rf = new ModelRenderer(this, 54, 44);
//        this.leg2rf.setRotationPoint(0.8F, 2.4F, 0.1F);
//        this.leg2rf.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F);
//        this.setRotateAngle(leg2rf, -0.22759093446006054F, 0.0F, 0.091106186954104F);
//        this.leg2lb = new ModelRenderer(this, 52, 47);
//        this.leg2lb.mirror = true;
//        this.leg2lb.setRotationPoint(-1.4F, 2.8F, -1.1F);
//        this.leg2lb.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
//        this.setRotateAngle(leg2lb, 0.8196066167365371F, 0.0F, 0.0F);
//        this.WargNeck = new ModelRenderer(this, 0, 22);
//        this.WargNeck.setRotationPoint(0.0F, 12.2F, -7.1F);
//        this.WargNeck.addBox(-2.0F, -2.5F, -4.0F, 4, 5, 5, 0.0F);
//        this.setRotateAngle(WargNeck, -0.42551127163621755F, 0.0F, 0.0F);
//        this.feetlf = new ModelRenderer(this, 50, 40);
//        this.feetlf.mirror = true;
//        this.feetlf.setRotationPoint(-0.0F, 6.7F, 0.4F);
//        this.feetlf.addBox(-1.5F, -0.9F, -2.3F, 3, 2, 4, 0.0F);
//        this.setRotateAngle(feetlf, 0.22689280275926282F, 0.0F, 0.0F);
//        this.ear3r = new ModelRenderer(this, 27, 0);
//        this.ear3r.mirror = true;
//        this.ear3r.setRotationPoint(0.0F, -0.1F, 1.3F);
//        this.ear3r.addBox(-0.5F, -2.6F, -0.4F, 1, 3, 1, 0.0F);
//        this.setRotateAngle(ear3r, 0.27314402793711257F, 0.0F, 0.0F);
//        this.feetlb = new ModelRenderer(this, 50, 40);
//        this.feetlb.mirror = true;
//        this.feetlb.setRotationPoint(0.0F, 4.7F, -0.7F);
//        this.feetlb.addBox(-1.5F, -0.9F, -2.3F, 3, 2, 4, 0.0F);
//        this.setRotateAngle(feetlb, -0.17453292519943295F, 0.0F, 0.0F);
//        this.snout1 = new ModelRenderer(this, 0, 58);
//        this.snout1.setRotationPoint(0.0F, 0.4F, -3.7F);
//        this.snout1.addBox(-1.5F, -0.7F, -3.0F, 3, 2, 4, 0.0F);
//        this.setRotateAngle(snout1, 0.15707963267948966F, 0.0F, 0.0F);
//        this.feetrb = new ModelRenderer(this, 50, 40);
//        this.feetrb.setRotationPoint(0.0F, 4.8F, -0.7F);
//        this.feetrb.addBox(-1.5F, -0.9F, -2.3F, 3, 2, 4, 0.0F);
//        this.setRotateAngle(feetrb, -0.17453292519943295F, 0.0F, 0.0F);
//        this.WargTail3 = new ModelRenderer(this, 42, 60);
//        this.WargTail3.setRotationPoint(0.0F, 0.1F, 3.8F);
//        this.WargTail3.addBox(-1.5F, -1.5F, 0.0F, 1, 1, 3, 0.0F);
//        this.setRotateAngle(WargTail3, 0.31869712141416456F, 0.0F, 0.0F);
//        this.chesttop = new ModelRenderer(this, 4, 12);
//        this.chesttop.setRotationPoint(0.5F, -2.3F, -6.6F);
//        this.chesttop.addBox(-3.0F, -1.0F, 0.0F, 7, 3, 7, 0.0F);
//        this.setRotateAngle(chesttop, 0.18587756533739608F, 0.0F, 0.0F);
//        this.neck2 = new ModelRenderer(this, 0, 32);
//        this.neck2.setRotationPoint(0.0F, 1.2F, -1.6F);
//        this.neck2.addBox(-1.5F, -0.3F, -1.6F, 3, 2, 6, 0.0F);
//        this.setRotateAngle(neck2, -0.23596851486963336F, 0.0F, 0.0F);
//        this.WargLegFL = new ModelRenderer(this, 50, 54);
//        this.WargLegFL.setRotationPoint(3.1F, 14.2F, -4.3F);
//        this.WargLegFL.addBox(-1.0F, -2.4F, -1.5F, 3, 6, 4, 0.0F);
//        this.setRotateAngle(WargLegFL, 0.0F, 0.0F, -0.091106186954104F);
//        this.WargTail1 = new ModelRenderer(this, 40, 59);
//        this.WargTail1.setRotationPoint(0.5F, -0.5F, 7.0F);
//        this.WargTail1.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(WargTail1, -0.20943951023931953F, 0.0F, 0.0F);
//        this.snout2 = new ModelRenderer(this, 0, 53);
//        this.snout2.setRotationPoint(0.0F, -0.8F, 0.1F);
//        this.snout2.addBox(-1.0F, -0.7F, -3.1F, 2, 1, 4, 0.0F);
//        this.setRotateAngle(snout2, 0.2553416695667704F, 0.0F, 0.0F);
//        this.feetrf = new ModelRenderer(this, 50, 40);
//        this.feetrf.setRotationPoint(-0.0F, 6.7F, 0.4F);
//        this.feetrf.addBox(-1.5F, -0.9F, -2.3F, 3, 2, 4, 0.0F);
//        this.setRotateAngle(feetrf, 0.22689280275926282F, 0.0F, 0.0F);
//        this.leg2lf = new ModelRenderer(this, 54, 44);
//        this.leg2lf.mirror = true;
//        this.leg2lf.setRotationPoint(0.2F, 2.4F, 0.1F);
//        this.leg2lf.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F);
//        this.setRotateAngle(leg2lf, -0.22759093446006054F, 0.0F, -0.091106186954104F);
//        this.head.addChild(this.ear1l);
//        this.leg2lb.addChild(this.leg3lb);
//        this.WargLegRL.addChild(this.leg2rb);
//        this.head.addChild(this.jaw);
//        this.ear1l.addChild(this.ear2l);
//        this.WargTail1.addChild(this.WargTail2);
//        this.head.addChild(this.head2);
//        this.ear1r.addChild(this.ear2r);
//        this.WargTail3.addChild(this.WargTail4);
//        this.back.addChild(this.back2);
//        this.head.addChild(this.ear1r);
//        this.leg2rb.addChild(this.leg3rb);
//        this.WargBody.addChild(this.back);
//        this.WargNeck.addChild(this.head);
//        this.ear1l.addChild(this.ear3l);
//        this.WargLegFL.addChild(this.leg2rf);
//        this.WargLegRR.addChild(this.leg2lb);
//        this.leg2lf.addChild(this.feetlf);
//        this.ear1r.addChild(this.ear3r);
//        this.leg3lb.addChild(this.feetlb);
//        this.head.addChild(this.snout1);
//        this.leg3rb.addChild(this.feetrb);
//        this.WargTail2.addChild(this.WargTail3);
//        this.WargBody.addChild(this.chesttop);
//        this.WargNeck.addChild(this.neck2);
//        this.back.addChild(this.WargTail1);
//        this.snout1.addChild(this.snout2);
//        this.leg2rf.addChild(this.feetrf);
//        this.WargLegFR.addChild(this.leg2lf);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//
//        this.WargLegRR.render(f5);
//        this.WargLegRL.render(f5);
//        this.WargBody.render(f5);
//        this.WargLegFR.render(f5);
//        this.WargLegFL.render(f5);
//        this.WargNeck.render(f5);
//    }
//
//    @Override
//    public void setLivingAnimations(LivingEntity parLivingEntity, float parLimbSwingAngle, float parMaxLimbSwingDistance, float parHeadAngleChangeRate)
//    {
//    }
//
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
//    {
//        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
//        float baseNeckRotation = -0.42551127163621755F; // needs to be the original value of leg.rotateAngleX
//
//        this.WargLegFR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.WargLegRR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        this.WargLegFL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        this.WargLegRL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//
//        this.WargNeck.rotateAngleX = baseNeckRotation + (headPitch * 0.017453292F);
//        this.WargNeck.rotateAngleY = (netHeadYaw * 0.017453292F);
//
//        ear1l.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(15);
//        ear1r.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(-15);
//
//        WargTail1.rotateAngleX = MathHelper.sin(degToRad(entityIn.ticksExisted*7)) * degToRad(5);
//        WargTail2.rotateAngleX = WargTail1.rotateAngleX * 3;
//        WargTail3.rotateAngleX = WargTail2.rotateAngleX * 1;
//        WargTail4.rotateAngleX = WargTail3.rotateAngleX * 1;
//    }
//}