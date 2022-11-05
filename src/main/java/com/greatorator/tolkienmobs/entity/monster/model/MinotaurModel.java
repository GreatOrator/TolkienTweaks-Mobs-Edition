package com.greatorator.tolkienmobs.entity.monster.model;

//
//public class MinotaurModel<T extends MonsterEntity> extends BipedModel<T> {
//    public ModelRenderer MinotaurBody;
//    public ModelRenderer MinotaurNeck;
//    public ModelRenderer MinotaurTail1;
//    public ModelRenderer MinotaurLegL;
//    public ModelRenderer MinotaurLegR;
//    public ModelRenderer MinotaurChest;
//    public ModelRenderer loinclothWaist1;
//    public ModelRenderer loinclothWaist2;
//    public ModelRenderer loinclothwaist3;
//    public ModelRenderer loinclothWaist4;
//    public ModelRenderer loinclothHang1;
//    public ModelRenderer loinclothHang2;
//    public ModelRenderer MinotaurSkull;
//    public ModelRenderer MinotaurHornR1;
//    public ModelRenderer MinotaurHornL1;
//    public ModelRenderer MinotaurMouth;
//    public ModelRenderer brow1;
//    public ModelRenderer brow2;
//    public ModelRenderer ear1l;
//    public ModelRenderer ear1l_1;
//    public ModelRenderer MinotaurHornR2;
//    public ModelRenderer MinotaurHornR3;
//    public ModelRenderer MinotaurHornR4;
//    public ModelRenderer MinotaurHornR4_1;
//    public ModelRenderer MinotaurHornL2;
//    public ModelRenderer MinotaurHornL3;
//    public ModelRenderer MinotaurHornL4;
//    public ModelRenderer MinotaurHornL4_1;
//    public ModelRenderer ring;
//    public ModelRenderer nose;
//    public ModelRenderer MinotaurMouth_1;
//    public ModelRenderer ring_1;
//    public ModelRenderer ring_2;
//    public ModelRenderer ring_3;
//    public ModelRenderer ring_4;
//    public ModelRenderer ring_5;
//    public ModelRenderer ring_6;
//    public ModelRenderer ring_7;
//    public ModelRenderer nose_1;
//    public ModelRenderer ear2l;
//    public ModelRenderer ear3l;
//    public ModelRenderer ear2l_1;
//    public ModelRenderer ear3l_1;
//    public ModelRenderer bipedRightArmLower;
//    public ModelRenderer armbandr;
//    public ModelRenderer bipedLeftArmLower;
//    public ModelRenderer armbandl;
//    public ModelRenderer MinotaurTail2;
//    public ModelRenderer MinotaurTail3;
//    public ModelRenderer MinotaurTail4;
//    public ModelRenderer MinotaurTailBand;
//    public ModelRenderer MinotaurTail6;
//    public ModelRenderer LegLowL;
//    public ModelRenderer LegLLL;
//    public ModelRenderer FeetR;
//    public ModelRenderer FeetR1;
//    public ModelRenderer LegLowR;
//    public ModelRenderer LegLLR;
//    public ModelRenderer FeetR_1;
//    public ModelRenderer FeetR1_1;
//    private boolean ghostModel;
//    private float partialTicks;
//
//    public MinotaurModel(float modelSize, boolean p_i46303_2_) {
//        super(modelSize);
//        this.ghostModel = p_i46303_2_;
//        this.texWidth = 128;
//        this.texHeight = 128;
//        this.loinclothHang1 = new ModelRenderer(this, 0, 0);
//        this.loinclothHang1.setPos(0.0F, 0.0F, -0.2F);
//        this.loinclothHang1.addBox(-3.5F, 0.0F, 0.0F, 7, 5, 1, 0.0F);
//        this.MinotaurHornR3 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornR3.setPos(-3.9F, 0.6F, 0.0F);
//        this.MinotaurHornR3.addBox(-5.0F, -1.5F, -1.6F, 5, 3, 2, 0.0F);
//        this.setRotateAngle(MinotaurHornR3, 0.0F, 0.0F, 0.9599310885968813F);
//        this.ring_1 = new ModelRenderer(this, 7, 112);
//        this.ring_1.setPos(-1.4F, 0.5F, 0.0F);
//        this.ring_1.addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.bipedLeftArmLower = new ModelRenderer(this, 59, 111);
//        this.bipedLeftArmLower.mirror = true;
//        this.bipedLeftArmLower.setPos(4.0F, 8.0F, 1.0F);
//        this.bipedLeftArmLower.addBox(-2.5F, 0.0F, -3.0F, 4, 13, 4, 0.0F);
//        this.setRotateAngle(bipedLeftArmLower, -0.17453292519943295F, 0.0F, 0.0F);
//        this.MinotaurSkull = new ModelRenderer(this, 87, 110);
//        this.MinotaurSkull.setPos(0.0F, -6.5F, -1.8F);
//        this.MinotaurSkull.addBox(-4.5F, -4.5F, -4.5F, 9, 9, 9, 0.0F);
//        this.setRotateAngle(MinotaurSkull, -0.17453292519943295F, 0.0F, 0.0F);
//        this.MinotaurBody = new ModelRenderer(this, 76, 77);
//        this.MinotaurBody.setPos(0.0F, -20.4F, 0.0F);
//        this.MinotaurBody.addBox(-8.5F, 0.0F, -4.0F, 17, 14, 9, 0.0F);
//        this.setRotateAngle(MinotaurBody, 0.2181661564992912F, 0.0F, 0.0F);
//        this.brow2 = new ModelRenderer(this, 115, 119);
//        this.brow2.setPos(-3.0F, -4.1F, -4.2F);
//        this.brow2.addBox(-0.5F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(brow2, -0.17453292519943295F, 0.0F, 0.6108652381980153F);
//        this.MinotaurTail4 = new ModelRenderer(this, 0, 118);
//        this.MinotaurTail4.setPos(0.0F, 0.0F, 7.0F);
//        this.MinotaurTail4.addBox(-1.5F, -1.5F, 0.0F, 2, 2, 8, 0.0F);
//        this.setRotateAngle(MinotaurTail4, 0.16982053621904827F, 0.0F, 0.0F);
//        this.MinotaurNeck = new ModelRenderer(this, 33, 81);
//        this.MinotaurNeck.setPos(0.0F, -20.5F, -1.0F);
//        this.MinotaurNeck.addBox(-2.5F, -4.5F, -2.5F, 5, 6, 5, 0.0F);
//        this.setRotateAngle(MinotaurNeck, 0.17453292519943295F, 0.0F, 0.0F);
//        this.MinotaurHornL4_1 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornL4_1.mirror = true;
//        this.MinotaurHornL4_1.setPos(3.4F, 0.5F, 0.4F);
//        this.MinotaurHornL4_1.addBox(0.0F, -1.5F, -1.6F, 2, 1, 1, 0.0F);
//        this.ear3l = new ModelRenderer(this, 36, 0);
//        this.ear3l.mirror = true;
//        this.ear3l.setPos(0.0F, -0.1F, 1.3F);
//        this.ear3l.addBox(-0.5F, -2.6F, -0.4F, 1, 4, 1, 0.0F);
//        this.setRotateAngle(ear3l, 0.27314402793711257F, 0.0F, 0.0F);
//        this.FeetR1 = new ModelRenderer(this, 16, 63);
//        this.FeetR1.setPos(0.5F, -1.0F, 3.299999952316284F);
//        this.FeetR1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
//        this.setRotateAngle(FeetR1, -1.9268435208333508F, 0.0F, 0.0F);
//        this.MinotaurTail1 = new ModelRenderer(this, 0, 116);
//        this.MinotaurTail1.setPos(0.5F, 0.0F, 1.0F);
//        this.MinotaurTail1.addBox(-2.5F, -2.5F, -0.1F, 4, 4, 8, 0.0F);
//        this.setRotateAngle(MinotaurTail1, -0.8491026810952413F, 0.0F, 0.0F);
//        this.loinclothHang2 = new ModelRenderer(this, 0, 0);
//        this.loinclothHang2.setPos(0.0F, 4.5F, 0.0F);
//        this.loinclothHang2.addBox(-3.5F, 0.0F, 0.0F, 7, 7, 1, 0.0F);
//        this.bipedRightArmLower = new ModelRenderer(this, 59, 111);
//        this.bipedRightArmLower.setPos(-2.5F, 8.0F, 1.0F);
//        this.bipedRightArmLower.addBox(-2.5F, 0.0F, -3.0F, 4, 13, 4, 0.0F);
//        this.setRotateAngle(bipedRightArmLower, -0.17453292519943295F, 0.0F, 0.0F);
//        this.MinotaurChest = new ModelRenderer(this, 80, 58);
//        this.MinotaurChest.setPos(0.0F, 11.2F, 0.0F);
//        this.MinotaurChest.addBox(-8.0F, 0.0F, -3.5F, 16, 10, 8, 0.0F);
//        this.setRotateAngle(MinotaurChest, -0.5094616086571448F, 0.0F, 0.0F);
//        this.MinotaurMouth = new ModelRenderer(this, 26, 92);
//        this.MinotaurMouth.setPos(0.0F, 1.5F, -2.0F);
//        this.MinotaurMouth.addBox(-2.5F, -2.5F, -5.0F, 5, 3, 8, 0.0F);
//        this.setRotateAngle(MinotaurMouth, 0.2181661564992912F, 0.0F, 0.0F);
//        this.loinclothWaist4 = new ModelRenderer(this, 0, 0);
//        this.loinclothWaist4.setPos(-8.0F, 0.0F, 0.0F);
//        this.loinclothWaist4.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
//        this.FeetR = new ModelRenderer(this, 10, 67);
//        this.FeetR.setPos(-0.5F, 14.2F, 0.5F);
//        this.FeetR.addBox(0.0F, 0.0F, 0.0F, 5, 3, 6, 0.0F);
//        this.setRotateAngle(FeetR, 0.9056513488598577F, 0.0F, 0.0F);
//        this.ring_5 = new ModelRenderer(this, 7, 112);
//        this.ring_5.setPos(1.8F, -0.2F, 0.0F);
//        this.ring_5.addBox(-1.0F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(ring_5, 0.0F, 0.0F, 0.7853981633974483F);
//        this.ear1l = new ModelRenderer(this, 33, 9);
//        this.ear1l.mirror = true;
//        this.ear1l.setPos(4.6F, -0.5F, -0.9F);
//        this.ear1l.addBox(-1.0F, -2.2F, 0.0F, 2, 3, 1, 0.0F);
//        this.setRotateAngle(ear1l, -0.2617993877991494F, -0.4363323129985824F, 1.1344640137963142F);
//        this.armbandl = new ModelRenderer(this, 0, 109);
//        this.armbandl.setPos(-3.0F, 7.0F, -3.5F);
//        this.armbandl.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
//        this.ring_3 = new ModelRenderer(this, 7, 112);
//        this.ring_3.setPos(0.0F, 3.0F, 0.0F);
//        this.ring_3.addBox(-1.0F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
//        this.ear2l_1 = new ModelRenderer(this, 36, 6);
//        this.ear2l_1.setPos(0.0F, 0.0F, 0.0F);
//        this.ear2l_1.addBox(-0.5F, -3.2F, 0.0F, 1, 1, 1, 0.0F);
//        this.MinotaurHornR4 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornR4.setPos(-3.2F, -0.3F, 0.0F);
//        this.MinotaurHornR4.addBox(-5.0F, -1.5F, -1.6F, 4, 2, 2, 0.0F);
//        this.setRotateAngle(MinotaurHornR4, 0.0F, 0.0F, -0.5235987755982988F);
//        this.ear2l = new ModelRenderer(this, 36, 6);
//        this.ear2l.mirror = true;
//        this.ear2l.setPos(0.0F, 0.0F, 0.0F);
//        this.ear2l.addBox(-0.5F, -3.2F, 0.0F, 1, 1, 1, 0.0F);
//        this.MinotaurHornL2 = new ModelRenderer(this, 0, 103);
//        this.MinotaurHornL2.mirror = true;
//        this.MinotaurHornL2.setPos(3.9F, 0.6F, 0.5F);
//        this.MinotaurHornL2.addBox(0.0F, -1.5F, -1.5F, 5, 3, 2, 0.0F);
//        this.setRotateAngle(MinotaurHornL2, 0.0F, 0.0F, -0.5235987755982988F);
//        this.loinclothWaist1 = new ModelRenderer(this, 0, 0);
//        this.loinclothWaist1.setPos(0.0F, 6.0F, -4.0F);
//        this.loinclothWaist1.addBox(-8.0F, 0.0F, 0.0F, 16, 4, 1, 0.0F);
//        this.loinclothwaist3 = new ModelRenderer(this, 0, 0);
//        this.loinclothwaist3.setPos(8.0F, 0.0F, 0.0F);
//        this.loinclothwaist3.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
//        this.LegLLL = new ModelRenderer(this, 28, 112);
//        this.LegLLL.setPos(-2.0F, 10.2F, 2.0F);
//        this.LegLLL.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
//        this.setRotateAngle(LegLLL, -1.6414821615006667F, 0.0F, 0.0F);
//        this.ring_2 = new ModelRenderer(this, 7, 112);
//        this.ring_2.setPos(2.5F, 0.5F, 0.0F);
//        this.ring_2.addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.rightArm = new ModelRenderer(this, 56, 95);
//        this.rightArm.setPos(-8.0F, -20.0F, 0.5F);
//        this.rightArm.addBox(-5.5F, -1.5F, -3.0F, 6, 10, 6, 0.0F);
//        this.MinotaurTail6 = new ModelRenderer(this, 0, 122);
//        this.MinotaurTail6.setPos(0.5F, 0.6F, 3.0F);
//        this.MinotaurTail6.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
//        this.setRotateAngle(MinotaurTail6, 0.16982053621904827F, 0.0F, 0.0F);
//        this.MinotaurLegR = new ModelRenderer(this, 50, 67);
//        this.MinotaurLegR.mirror = true;
//        this.MinotaurLegR.setPos(2.5F, 1.0F, -0.2F);
//        this.MinotaurLegR.addBox(-1.0F, -2.0F, -4.0F, 7, 11, 8, 0.0F);
//        this.setRotateAngle(MinotaurLegR, -0.6544984694978736F, 0.0F, 0.0F);
//        this.armbandr = new ModelRenderer(this, 0, 109);
//        this.armbandr.setPos(-3.0F, 7.0F, -3.5F);
//        this.armbandr.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
//        this.MinotaurLegL = new ModelRenderer(this, 50, 67);
//        this.MinotaurLegL.mirror = true;
//        this.MinotaurLegL.setPos(-7.5F, 1.0F, -0.2F);
//        this.MinotaurLegL.addBox(-1.0F, -2.0F, -4.0F, 7, 11, 8, 0.0F);
//        this.setRotateAngle(MinotaurLegL, -0.6544984694978736F, 0.0F, 0.0F);
//        this.MinotaurHornR2 = new ModelRenderer(this, 0, 103);
//        this.MinotaurHornR2.setPos(-3.9F, 0.6F, 0.5F);
//        this.MinotaurHornR2.addBox(-5.0F, -1.5F, -1.5F, 5, 3, 2, 0.0F);
//        this.setRotateAngle(MinotaurHornR2, 0.0F, 0.0F, 0.5235987755982988F);
//        this.ring_4 = new ModelRenderer(this, 7, 112);
//        this.ring_4.setPos(1.0F, 2.6F, 0.0F);
//        this.ring_4.addBox(-1.0F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(ring_4, 0.0F, 0.0F, -0.7853981633974483F);
//        this.LegLowR = new ModelRenderer(this, 27, 108);
//        this.LegLowR.mirror = true;
//        this.LegLowR.setPos(2.5F, 6.5F, -1.5F);
//        this.LegLowR.addBox(-2.5F, 0.0F, -2.5F, 5, 14, 6, 0.0F);
//        this.setRotateAngle(LegLowR, 1.3962634015954636F, 0.0F, 0.0F);
//        this.MinotaurHornL4 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornL4.mirror = true;
//        this.MinotaurHornL4.setPos(4.2F, 0.3F, 0.0F);
//        this.MinotaurHornL4.addBox(0.0F, -1.5F, -1.6F, 4, 2, 2, 0.0F);
//        this.setRotateAngle(MinotaurHornL4, 0.0F, 0.0F, 0.5235987755982988F);
//        this.MinotaurHornL1 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornL1.mirror = true;
//        this.MinotaurHornL1.setPos(3.5F, -4.5F, 0.0F);
//        this.MinotaurHornL1.addBox(-3.0F, -1.5F, -1.5F, 8, 4, 3, 0.0F);
//        this.setRotateAngle(MinotaurHornL1, 0.0F, 0.0F, -0.5235987755982988F);
//        this.MinotaurTail3 = new ModelRenderer(this, 0, 118);
//        this.MinotaurTail3.setPos(0.0F, 0.3F, 6.0F);
//        this.MinotaurTail3.addBox(-1.5F, -1.5F, 0.0F, 2, 2, 8, 0.0F);
//        this.setRotateAngle(MinotaurTail3, 0.16982053621904827F, 0.0F, 0.0F);
//        this.MinotaurMouth_1 = new ModelRenderer(this, 10, 85);
//        this.MinotaurMouth_1.setPos(0.0F, 1.2F, -0.1F);
//        this.MinotaurMouth_1.addBox(-2.5F, -1.0F, -5.0F, 5, 2, 5, 0.0F);
//        this.setRotateAngle(MinotaurMouth_1, 0.2617993877991494F, 0.0F, 0.0F);
//        this.MinotaurTailBand = new ModelRenderer(this, 0, 109);
//        this.MinotaurTailBand.setPos(-1.0F, -0.8F, 7.0F);
//        this.MinotaurTailBand.addBox(-1.0F, -1.0F, 0.0F, 3, 3, 4, 0.0F);
//        this.setRotateAngle(MinotaurTailBand, 0.16982053621904827F, 0.0F, 0.0F);
//        this.LegLowL = new ModelRenderer(this, 27, 109);
//        this.LegLowL.mirror = true;
//        this.LegLowL.setPos(2.5F, 6.5F, -1.5F);
//        this.LegLowL.addBox(-2.5F, 0.0F, -2.5F, 5, 14, 5, 0.0F);
//        this.setRotateAngle(LegLowL, 1.3962634015954636F, 0.0F, 0.0F);
//        this.MinotaurHornR1 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornR1.setPos(-3.5F, -4.5F, 0.0F);
//        this.MinotaurHornR1.addBox(-5.0F, -1.5F, -1.5F, 8, 4, 3, 0.0F);
//        this.setRotateAngle(MinotaurHornR1, 0.0F, 0.0F, 0.5235987755982988F);
//        this.ear3l_1 = new ModelRenderer(this, 36, 0);
//        this.ear3l_1.setPos(0.0F, -0.1F, 1.3F);
//        this.ear3l_1.addBox(-0.5F, -2.6F, -0.4F, 1, 4, 1, 0.0F);
//        this.setRotateAngle(ear3l_1, 0.27314402793711257F, 0.0F, 0.0F);
//        this.ring = new ModelRenderer(this, 7, 112);
//        this.ring.setPos(0.0F, -1.5F, -4.9F);
//        this.ring.addBox(-1.0F, -0.9F, -0.5F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(ring, -0.3490658503988659F, 0.0F, 0.0F);
//        this.loinclothWaist2 = new ModelRenderer(this, 0, 0);
//        this.loinclothWaist2.setPos(0.0F, 0.0F, 8.0F);
//        this.loinclothWaist2.addBox(-8.0F, 0.0F, 0.0F, 16, 4, 1, 0.0F);
//        this.LegLLR = new ModelRenderer(this, 28, 109);
//        this.LegLLR.setPos(-2.0F, 10.2F, 2.0F);
//        this.LegLLR.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
//        this.setRotateAngle(LegLLR, -1.6331045810910938F, 0.0F, 0.0F);
//        this.MinotaurHornR4_1 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornR4_1.setPos(-1.4F, 0.5F, 0.4F);
//        this.MinotaurHornR4_1.addBox(-5.0F, -1.5F, -1.6F, 2, 1, 1, 0.0F);
//        this.MinotaurTail2 = new ModelRenderer(this, 0, 117);
//        this.MinotaurTail2.setPos(0.0F, 0.0F, 6.0F);
//        this.MinotaurTail2.addBox(-2.0F, -2.0F, 0.0F, 3, 3, 8, 0.0F);
//        this.setRotateAngle(MinotaurTail2, 0.16982053621904827F, 0.0F, 0.0F);
//        this.FeetR_1 = new ModelRenderer(this, 10, 69);
//        this.FeetR_1.setPos(-0.5F, 14.199999809265137F, 0.5F);
//        this.FeetR_1.addBox(0.0F, 0.0F, 0.0F, 5, 3, 6, 0.0F);
//        this.setRotateAngle(FeetR_1, 0.9056513382072132F, 0.0F, 0.0F);
//        this.brow1 = new ModelRenderer(this, 115, 119);
//        this.brow1.setPos(1.3F, -2.9F, -4.2F);
//        this.brow1.addBox(-0.5F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(brow1, -0.17453292519943295F, 0.0F, -0.6108652381980153F);
//        this.nose = new ModelRenderer(this, 110, 120);
//        this.nose.setPos(0.0F, -2.4F, -5.0F);
//        this.nose.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 4, 0.0F);
//        this.setRotateAngle(nose, 0.08726646259971647F, 0.0F, 0.0F);
//        this.nose_1 = new ModelRenderer(this, 115, 114);
//        this.nose_1.setPos(0.0F, 0.0F, -0.8F);
//        this.nose_1.addBox(-1.5F, -0.5F, 0.0F, 3, 3, 2, 0.0F);
//        this.MinotaurHornL3 = new ModelRenderer(this, 0, 102);
//        this.MinotaurHornL3.mirror = true;
//        this.MinotaurHornL3.setPos(3.9F, 0.6F, 0.0F);
//        this.MinotaurHornL3.addBox(0.0F, -1.5F, -1.6F, 5, 3, 2, 0.0F);
//        this.setRotateAngle(MinotaurHornL3, 0.0F, 0.0F, -0.9599310885968813F);
//        this.ear1l_1 = new ModelRenderer(this, 33, 9);
//        this.ear1l_1.setPos(-4.6F, -0.5F, -0.9F);
//        this.ear1l_1.addBox(-1.0F, -2.2F, 0.0F, 2, 3, 1, 0.0F);
//        this.setRotateAngle(ear1l_1, -0.2617993877991494F, 0.4363323129985824F, -1.1344640137963142F);
//        this.ring_6 = new ModelRenderer(this, 7, 112);
//        this.ring_6.setPos(-1.7F, -0.2F, 0.0F);
//        this.ring_6.addBox(-1.0F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(ring_6, 0.0F, 0.0F, -0.7853981633974483F);
//        this.FeetR1_1 = new ModelRenderer(this, 16, 63);
//        this.FeetR1_1.setPos(0.5F, -1.0F, 3.299999952316284F);
//        this.FeetR1_1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
//        this.setRotateAngle(FeetR1_1, -1.9268435208333508F, 0.0F, 0.0F);
//        this.leftArm = new ModelRenderer(this, 56, 95);
//        this.leftArm.mirror = true;
//        this.leftArm.setPos(7.5F, -20.0F, 0.5F);
//        this.leftArm.addBox(0.0F, -1.5F, -3.0F, 6, 10, 6, 0.0F);
//        this.ring_7 = new ModelRenderer(this, 7, 112);
//        this.ring_7.setPos(-1.0F, 2.4F, 0.0F);
//        this.ring_7.addBox(-1.0F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(ring_7, 0.0F, 0.0F, 0.7853981633974483F);
//        this.loinclothWaist1.addChild(this.loinclothHang1);
//        this.MinotaurHornR2.addChild(this.MinotaurHornR3);
//        this.ring.addChild(this.ring_1);
//        this.leftArm.addChild(this.bipedLeftArmLower);
//        this.MinotaurNeck.addChild(this.MinotaurSkull);
//        this.MinotaurSkull.addChild(this.brow2);
//        this.MinotaurTail3.addChild(this.MinotaurTail4);
//        this.MinotaurHornL4.addChild(this.MinotaurHornL4_1);
//        this.ear1l.addChild(this.ear3l);
//        this.FeetR.addChild(this.FeetR1);
//        this.loinclothHang1.addChild(this.loinclothHang2);
//        this.rightArm.addChild(this.bipedRightArmLower);
//        this.MinotaurBody.addChild(this.MinotaurChest);
//        this.MinotaurSkull.addChild(this.MinotaurMouth);
//        this.loinclothWaist1.addChild(this.loinclothWaist4);
//        this.LegLLL.addChild(this.FeetR);
//        this.ring.addChild(this.ring_5);
//        this.MinotaurSkull.addChild(this.ear1l);
//        this.bipedLeftArmLower.addChild(this.armbandl);
//        this.ring.addChild(this.ring_3);
//        this.ear1l_1.addChild(this.ear2l_1);
//        this.MinotaurHornR3.addChild(this.MinotaurHornR4);
//        this.ear1l.addChild(this.ear2l);
//        this.MinotaurHornL1.addChild(this.MinotaurHornL2);
//        this.MinotaurChest.addChild(this.loinclothWaist1);
//        this.loinclothWaist1.addChild(this.loinclothwaist3);
//        this.LegLowL.addChild(this.LegLLL);
//        this.ring.addChild(this.ring_2);
//        this.MinotaurTailBand.addChild(this.MinotaurTail6);
//        this.bipedRightArmLower.addChild(this.armbandr);
//        this.MinotaurHornR1.addChild(this.MinotaurHornR2);
//        this.ring.addChild(this.ring_4);
//        this.MinotaurLegR.addChild(this.LegLowR);
//        this.MinotaurHornL3.addChild(this.MinotaurHornL4);
//        this.MinotaurSkull.addChild(this.MinotaurHornL1);
//        this.MinotaurTail2.addChild(this.MinotaurTail3);
//        this.MinotaurMouth.addChild(this.MinotaurMouth_1);
//        this.MinotaurTail4.addChild(this.MinotaurTailBand);
//        this.MinotaurLegL.addChild(this.LegLowL);
//        this.MinotaurSkull.addChild(this.MinotaurHornR1);
//        this.ear1l_1.addChild(this.ear3l_1);
//        this.MinotaurMouth.addChild(this.ring);
//        this.loinclothWaist1.addChild(this.loinclothWaist2);
//        this.LegLowR.addChild(this.LegLLR);
//        this.MinotaurHornR4.addChild(this.MinotaurHornR4_1);
//        this.MinotaurTail1.addChild(this.MinotaurTail2);
//        this.LegLLR.addChild(this.FeetR_1);
//        this.MinotaurSkull.addChild(this.brow1);
//        this.MinotaurMouth.addChild(this.nose);
//        this.nose.addChild(this.nose_1);
//        this.MinotaurHornL2.addChild(this.MinotaurHornL3);
//        this.MinotaurSkull.addChild(this.ear1l_1);
//        this.ring.addChild(this.ring_6);
//        this.FeetR_1.addChild(this.FeetR1_1);
//        this.ring.addChild(this.ring_7);
//    }
//
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> headParts() {
//        return ImmutableList.of(this.MinotaurNeck);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> bodyParts() {
//        return ImmutableList.of(this.MinotaurBody, this.rightArm, this.leftArm, this.MinotaurLegR, this.MinotaurLegL, this.MinotaurTail1);
//    }
//
//    @Override
//    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
//        this.rightArmPose = BipedModel.ArmPose.EMPTY;
//        this.leftArmPose = BipedModel.ArmPose.EMPTY;
//        ItemStack itemstack = entityIn.getItemInHand(Hand.MAIN_HAND);
//        if (itemstack.getItem() instanceof net.minecraft.item.SwordItem || itemstack.getItem() instanceof net.minecraft.item.AxeItem && entityIn.isAggressive()) {
//            if (entityIn.getMainArm() == HandSide.RIGHT) {
//                this.rightArmPose = ArmPose.ITEM;
//            } else {
//                this.leftArmPose = ArmPose.ITEM;
//            }
//        }
//
//        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
//    }
//
//    @Override
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//        float baseLegRotation = -0.6544984694978736F; // needs to be the original value of leg.rotateAngleX
//        float baseNeckRotation = 0.17453292519943295F; // needs to be the original value of neck.rotateAngleX
//
//        this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//
//        this.MinotaurLegL.xRot = baseLegRotation + (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
//        this.MinotaurLegR.xRot = baseLegRotation + (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount);
//
//        this.MinotaurNeck.yRot = baseNeckRotation + (netHeadYaw * 0.017453292F);
//        this.MinotaurNeck.xRot = baseNeckRotation + (headPitch * 0.017453292F);
//
//        MinotaurMouth_1.xRot = MathHelper.cos(degToRad(entityIn.tickCount*7)) * degToRad(20);
//
//        loinclothHang2.xRot = MathHelper.cos(degToRad(entityIn.tickCount*7)) * degToRad(10);
//
//        MinotaurTail1.yRot = MathHelper.sin(degToRad(entityIn.tickCount*7)) * degToRad(5);
//        MinotaurTail2.yRot = MinotaurTail1.yRot * 3;
//        MinotaurTail3.yRot = MinotaurTail2.yRot * 1;
//        MinotaurTail4.yRot = MinotaurTail3.yRot * 1;
//        MinotaurTail6.yRot = MinotaurTail4.yRot * 1;
//
//        this.rightArm.y = -20.0F;
//        this.leftArm.y = -20.0F;
//
//        this.rightArm.x = -8.5F;
//        this.leftArm.x = 8.0F;
//    }
//
//    @Override
//    public void translateToHand(HandSide hand, MatrixStack mStack) {
//        this.getArm(hand).translateAndRotate(mStack);
//        mStack.scale(2, 2, 2);
//    }
//}
