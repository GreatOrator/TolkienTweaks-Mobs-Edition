package com.greatorator.tolkienmobs.entity.boss.model;//package com.greatorator.tolkienmobs.client.render.model.boss;

/** Balrog - GreatOrator */
//
//public class BalrogModel<T extends MonsterEntity> extends BipedModel<T> {
//    public ModelRenderer BalrogBody;
//    public ModelRenderer BalrogNeck;
//    public ModelRenderer BalrogTail1;
//    public ModelRenderer BalrogLegR;
//    public ModelRenderer BalrogLegL;
//    public ModelRenderer BalrogChest;
//    public ModelRenderer BalrogWingR;
//    public ModelRenderer BalrogWingL;
//    public ModelRenderer BalrogWingSkin2;
//    public ModelRenderer BalrogWingBone2R;
//    public ModelRenderer BalrogWingBone3R;
//    public ModelRenderer wingfingerl132;
//    public ModelRenderer wingfingerl122;
//    public ModelRenderer wingfingerl14;
//    public ModelRenderer BalrogWingBone4R;
//    public ModelRenderer BalrogWingBone5R;
//    public ModelRenderer BalrogWingBone6R;
//    public ModelRenderer BalrogWingSkin1;
//    public ModelRenderer BalrogWingBone2L;
//    public ModelRenderer BalrogWingBone3L;
//    public ModelRenderer wingfingerl1;
//    public ModelRenderer wingfingerl12;
//    public ModelRenderer wingfingerl13;
//    public ModelRenderer BalrogWingBone4L;
//    public ModelRenderer BalrogWingBone5L;
//    public ModelRenderer BalrogWingBone6L;
//    public ModelRenderer BalrogSkull;
//    public ModelRenderer BalrogHornR1;
//    public ModelRenderer BalrogHornL1;
//    public ModelRenderer BalrogMouth;
//    public ModelRenderer BalrogHornR2;
//    public ModelRenderer BalrogHornR3;
//    public ModelRenderer BalrogHornR4;
//    public ModelRenderer BalrogHornR4_1;
//    public ModelRenderer BalrogHornL2;
//    public ModelRenderer BalrogHornL3;
//    public ModelRenderer BalrogHornL4;
//    public ModelRenderer BalrogHornL4_1;
//    public ModelRenderer BalrogTooth1;
//    public ModelRenderer BalrogTooth2;
//    public ModelRenderer BalrogTooth3;
//    public ModelRenderer BalrogTooth4;
//    public ModelRenderer BalrogTooth22;
//    public ModelRenderer BalrogTooth42;
//    public ModelRenderer BalrogTooth32;
//    public ModelRenderer BalrogTooth12;
//    public ModelRenderer bipedRightArmLower;
//    public ModelRenderer bipedLeftArmLower;
//    public ModelRenderer BalrogTail2;
//    public ModelRenderer BalrogTail3;
//    public ModelRenderer BalrogTail4;
//    public ModelRenderer BalrogTail5;
//    public ModelRenderer LegLowR;
//    public ModelRenderer leftArm;
//    public ModelRenderer rightArm;
//    public ModelRenderer LegLLR;
//    public ModelRenderer FeetR;
//    public ModelRenderer FeetR1;
//    public ModelRenderer FeetR2;
//    public ModelRenderer LegLowR_1;
//    public ModelRenderer LegLLR_1;
//    public ModelRenderer FeetR_1;
//    public ModelRenderer FeetR1_1;
//    public ModelRenderer FeetR2_1;
//    private boolean ghostModel;
//
//    public BalrogModel(float modelSize, boolean ghostModel) {
//        super(modelSize);
//        this.ghostModel = ghostModel;
//        this.texWidth = 128;
//        this.texHeight = 128;
//        this.BalrogHornL4_1 = new ModelRenderer(this, 115, 2);
//        this.BalrogHornL4_1.setPos(4.400000095367432F, 0.5F, 0.4000000059604645F);
//        this.BalrogHornL4_1.addBox(0.0F, -1.5F, -1.600000023841858F, 5, 1, 1, 0.0F);
//        this.setRotateAngle(BalrogHornL4_1, 0.0F, 0.17453292519943295F, 0.0F);
//        this.BalrogTooth12 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth12.setPos(0.699999988079071F, 2.4000000953674316F, -4.699999809265137F);
//        this.BalrogTooth12.addBox(-0.5F, -2.0F, -0.5F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth12, 0.08726646259971647F, 0.0F, 0.0F);
//        this.wingfingerl122 = new ModelRenderer(this, 122, 103);
//        this.wingfingerl122.setPos(33.70000076293945F, 6.699999809265137F, -0.30000001192092896F);
//        this.wingfingerl122.addBox(0.0F, 0.0F, 0.0F, 2, 24, 1, 0.0F);
//        this.setRotateAngle(wingfingerl122, 0.0F, 0.0F, 0.8208283259076444F);
//        this.BalrogTooth42 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth42.setPos(-0.699999988079071F, -1.2000000476837158F, -4.599999904632568F);
//        this.BalrogTooth42.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth42, -0.2617993877991494F, 0.0F, 0.0F);
//        this.wingfingerl14 = new ModelRenderer(this, 122, 103);
//        this.wingfingerl14.setPos(0.0F, 5.800000190734863F, -0.30000001192092896F);
//        this.wingfingerl14.addBox(0.0F, 0.0F, 0.0F, 2, 24, 1, 0.0F);
//        this.setRotateAngle(wingfingerl14, 0.0F, 0.0F, -0.6806784082777886F);
//        this.BalrogWingBone2L = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone2L.setPos(6.5F, 0.5F, 0.5F);
//        this.BalrogWingBone2L.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
//        this.FeetR2 = new ModelRenderer(this, 67, 62);
//        this.FeetR2.setPos(0.5F, 0.5F, 0.10000000149011612F);
//        this.FeetR2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
//        this.setRotateAngle(FeetR2, -0.5201081037785047F, 0.0F, 0.0F);
//        this.BalrogHornR1 = new ModelRenderer(this, 114, 0);
//        this.BalrogHornR1.setPos(-3.5F, -2.799999952316284F, 0.0F);
//        this.BalrogHornR1.addBox(-5.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
//        this.setRotateAngle(BalrogHornR1, -0.17453292519943295F, 0.17453292519943295F, 0.5235987755982988F);
//        this.bipedLeftArmLower = new ModelRenderer(this, 0, 31);
//        this.bipedLeftArmLower.mirror = true;
//        this.bipedLeftArmLower.setPos(3.0F, 8.0F, 1.0F);
//        this.bipedLeftArmLower.addBox(-2.5F, 0.0F, -3.0F, 4, 13, 4, 0.0F);
//        this.setRotateAngle(bipedLeftArmLower, -0.17453292519943295F, 0.0F, 0.0F);
//        this.FeetR = new ModelRenderer(this, 56, 56);
//        this.FeetR.setPos(-0.5F, 15.699999809265137F, -0.800000011920929F);
//        this.FeetR.addBox(0.0F, 0.0F, 0.0F, 5, 2, 6, 0.0F);
//        this.setRotateAngle(FeetR, 0.9056513382072132F, 0.0F, 0.0F);
//        this.BalrogChest = new ModelRenderer(this, 82, 26);
//        this.BalrogChest.setPos(0.0F, 11.199999809265137F, 0.0F);
//        this.BalrogChest.addBox(-8.0F, 0.0F, -3.5F, 16, 10, 7, 0.0F);
//        this.setRotateAngle(BalrogChest, -0.5094616179782085F, 0.0F, 0.0F);
//        this.BalrogSkull = new ModelRenderer(this, 45, 30);
//        this.BalrogSkull.setPos(0.0F, -6.5F, -1.7999999523162842F);
//        this.BalrogSkull.addBox(-4.5F, -4.5F, -4.5F, 9, 9, 9, 0.0F);
//        this.setRotateAngle(BalrogSkull, -0.17453292519943295F, 0.0F, 0.0F);
//        this.FeetR2_1 = new ModelRenderer(this, 67, 62);
//        this.FeetR2_1.setPos(0.5F, 0.5F, 0.10000000149011612F);
//        this.FeetR2_1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
//        this.setRotateAngle(FeetR2_1, -0.5201081037785047F, 0.0F, 0.0F);
//        this.wingfingerl12 = new ModelRenderer(this, 122, 103);
//        this.wingfingerl12.setPos(33.70000076293945F, 6.699999809265137F, -0.30000001192092896F);
//        this.wingfingerl12.addBox(0.0F, 0.0F, 0.0F, 2, 24, 1, 0.0F);
//        this.setRotateAngle(wingfingerl12, 0.0F, 0.0F, 0.8208283259076444F);
//        this.BalrogTooth2 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth2.setPos(-2.0999999046325684F, 2.200000047683716F, -4.699999809265137F);
//        this.BalrogTooth2.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth2, 0.08726646259971647F, 0.0F, 0.0F);
//        this.BalrogWingBone4L = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone4L.setPos(16.200000762939453F, -0.20000000298023224F, 0.0F);
//        this.BalrogWingBone4L.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingBone4L, 0.0F, 0.0F, 0.3490658503988659F);
//        this.BalrogTooth1 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth1.setPos(2.0999999046325684F, 2.200000047683716F, -4.699999809265137F);
//        this.BalrogTooth1.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth1, 0.08726646259971647F, 0.0F, 0.0F);
//        this.BalrogTail2 = new ModelRenderer(this, 25, 52);
//        this.BalrogTail2.setPos(0.0F, 0.0F, 6.0F);
//        this.BalrogTail2.addBox(-2.0F, -2.0F, 0.0F, 3, 3, 8, 0.0F);
//        this.setRotateAngle(BalrogTail2, 0.16982053621904827F, 0.0F, 0.0F);
//        this.LegLowR_1 = new ModelRenderer(this, 22, 43);
//        this.LegLowR_1.mirror = true;
//        this.LegLowR_1.setPos(2.5F, 6.5F, -1.5F);
//        this.LegLowR_1.addBox(-2.5F, 0.0F, -2.5F, 5, 16, 5, 0.0F);
//        this.setRotateAngle(LegLowR_1, 1.3962634015954636F, 0.0F, 0.0F);
//        this.BalrogWingBone2R = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone2R.mirror = true;
//        this.BalrogWingBone2R.setPos(6.5F, 0.5F, 0.5F);
//        this.BalrogWingBone2R.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
//        this.BalrogHornL3 = new ModelRenderer(this, 114, 1);
//        this.BalrogHornL3.setPos(4.0F, -0.20000000298023224F, 0.0F);
//        this.BalrogHornL3.addBox(0.0F, -1.5F, -1.600000023841858F, 5, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogHornL3, 0.0F, 0.3490658503988659F, 0.8726646259971648F);
//        this.leftArm = new ModelRenderer(this, 0, 48);
//        this.leftArm.mirror = true;
//        this.leftArm.setPos(8.5F, -20.0F, 0.5F);
//        this.leftArm.addBox(0.0F, -1.5F, -3.0F, 5, 10, 6, 0.0F);
//        this.wingfingerl1 = new ModelRenderer(this, 122, 103);
//        this.wingfingerl1.setPos(0.0F, 5.800000190734863F, -0.30000001192092896F);
//        this.wingfingerl1.addBox(0.0F, 0.0F, 0.0F, 2, 24, 1, 0.0F);
//        this.setRotateAngle(wingfingerl1, 0.0F, 0.0F, -0.6806784082777886F);
//        this.BalrogNeck = new ModelRenderer(this, 106, 15);
//        this.BalrogNeck.setPos(0.0F, -22.0F, -1.0F);
//        this.BalrogNeck.addBox(-2.5F, -4.5F, -2.5F, 5, 6, 5, 0.0F);
//        this.setRotateAngle(BalrogNeck, 0.17453292519943295F, 0.0F, 0.0F);
//        this.BalrogHornL2 = new ModelRenderer(this, 114, 1);
//        this.BalrogHornL2.setPos(4.5F, 0.30000001192092896F, 0.5F);
//        this.BalrogHornL2.addBox(0.0F, -1.5F, -1.5F, 5, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogHornL2, 0.0F, 0.17453292519943295F, 0.3490658503988659F);
//        this.BalrogMouth = new ModelRenderer(this, 16, 28);
//        this.BalrogMouth.setPos(0.0F, 3.5F, -2.0F);
//        this.BalrogMouth.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 8, 0.0F);
//        this.setRotateAngle(BalrogMouth, 0.2181661564992912F, 0.0F, 0.0F);
//        this.FeetR1 = new ModelRenderer(this, 64, 59);
//        this.FeetR1.setPos(0.5F, -1.0F, 5.300000190734863F);
//        this.FeetR1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
//        this.setRotateAngle(FeetR1, -1.9093902283134072F, 0.0F, 0.0F);
//        this.BalrogLegR = new ModelRenderer(this, 22, 45);
//        this.BalrogLegR.mirror = true;
//        this.BalrogLegR.setPos(-7.5F, 1.0F, -0.20000000298023224F);
//        this.BalrogLegR.addBox(-1.0F, -2.0F, -4.0F, 7, 11, 8, 0.0F);
//        this.setRotateAngle(BalrogLegR, -0.6544984694978736F, 0.0F, 0.0F);
//        this.BalrogTooth32 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth32.setPos(0.800000011920929F, -1.2000000476837158F, -4.599999904632568F);
//        this.BalrogTooth32.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth32, -0.2617993877991494F, 0.0F, 0.0F);
//        this.BalrogWingSkin2 = new ModelRenderer(this, 0, 0);
//        this.BalrogWingSkin2.setPos(18.5F, -4.300000190734863F, 0.0F);
//        this.BalrogWingSkin2.addBox(-8.5F, 0.0F, 0.0F, 53, 26, 0, 0.0F);
//        this.setRotateAngle(BalrogWingSkin2, 0.0F, 0.0F, 0.7853981633974483F);
//        this.BalrogHornR2 = new ModelRenderer(this, 114, 1);
//        this.BalrogHornR2.setPos(-4.5F, 0.30000001192092896F, 0.5F);
//        this.BalrogHornR2.addBox(-5.0F, -1.5F, -1.5F, 5, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogHornR2, 0.0F, -0.17453292519943295F, -0.3490658503988659F);
//        this.BalrogWingSkin1 = new ModelRenderer(this, 0, 0);
//        this.BalrogWingSkin1.setPos(18.5F, -4.300000190734863F, 0.0F);
//        this.BalrogWingSkin1.addBox(-8.5F, 0.0F, 0.0F, 53, 26, 0, 0.0F);
//        this.setRotateAngle(BalrogWingSkin1, 0.0F, 0.0F, 0.7853981633974483F);
//        this.FeetR1_1 = new ModelRenderer(this, 64, 59);
//        this.FeetR1_1.setPos(0.5F, -1.0F, 5.300000190734863F);
//        this.FeetR1_1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
//        this.setRotateAngle(FeetR1_1, -1.9093902283134072F, 0.0F, 0.0F);
//        this.wingfingerl132 = new ModelRenderer(this, 120, 103);
//        this.wingfingerl132.setPos(16.399999618530273F, 2.799999952316284F, -1.0F);
//        this.wingfingerl132.addBox(0.0F, 0.0F, 0.0F, 2, 23, 2, 0.0F);
//        this.setRotateAngle(wingfingerl132, 0.0F, 0.0F, 0.07487462424476646F);
//        this.wingfingerl13 = new ModelRenderer(this, 120, 103);
//        this.wingfingerl13.setPos(16.899999618530273F, 2.799999952316284F, -1.0F);
//        this.wingfingerl13.addBox(0.0F, 0.0F, 0.0F, 2, 23, 2, 0.0F);
//        this.setRotateAngle(wingfingerl13, 0.0F, 0.0F, 0.07487462424476646F);
//        this.BalrogWingBone6L = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone6L.setPos(16.200000762939453F, -0.20000000298023224F, 0.0F);
//        this.BalrogWingBone6L.addBox(0.0F, -1.0F, -1.0F, 21, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingBone6L, 0.0F, 0.0F, 0.6981317007977318F);
//        this.BalrogWingL = new ModelRenderer(this, 38, 26);
//        this.BalrogWingL.setPos(4.5F, 5.5F, 3.0F);
//        this.BalrogWingL.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingL, 0.0F, -0.7853981633974483F, -0.7853981633974483F);
//        this.BalrogTail4 = new ModelRenderer(this, 25, 52);
//        this.BalrogTail4.setPos(0.0F, 0.0F, 7.0F);
//        this.BalrogTail4.addBox(-1.5F, -1.5F, 0.0F, 2, 2, 8, 0.0F);
//        this.setRotateAngle(BalrogTail4, 0.16982053621904827F, 0.0F, 0.0F);
//        this.BalrogWingBone3L = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone3L.setPos(16.200000762939453F, -0.4000000059604645F, 0.0F);
//        this.BalrogWingBone3L.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingBone3L, 0.0F, 0.0F, 0.6981317007977318F);
//        this.BalrogTail3 = new ModelRenderer(this, 25, 52);
//        this.BalrogTail3.setPos(0.0F, 0.3F, 6.0F);
//        this.BalrogTail3.addBox(-1.5F, -1.5F, 0.0F, 2, 2, 8, 0.0F);
//        this.setRotateAngle(BalrogTail3, 0.16982053621904827F, 0.0F, 0.0F);
//        this.BalrogWingBone4R = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone4R.mirror = true;
//        this.BalrogWingBone4R.setPos(16.200000762939453F, -0.20000000298023224F, 0.0F);
//        this.BalrogWingBone4R.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingBone4R, 0.0F, 0.0F, 0.3490658503988659F);
//        this.LegLLR = new ModelRenderer(this, 62, 48);
//        this.LegLLR.setPos(-2.0F, 12.199999809265137F, 2.0F);
//        this.LegLLR.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
//        this.setRotateAngle(LegLLR, -1.6414822147638888F, 0.0F, 0.0F);
//        this.BalrogLegL = new ModelRenderer(this, 22, 45);
//        this.BalrogLegL.mirror = true;
//        this.BalrogLegL.setPos(2.5F, 1.0F, -0.20000000298023224F);
//        this.BalrogLegL.addBox(-1.0F, -2.0F, -4.0F, 7, 11, 8, 0.0F);
//        this.setRotateAngle(BalrogLegL, -0.6544984694978736F, 0.0F, 0.0F);
//        this.BalrogWingBone5R = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone5R.mirror = true;
//        this.BalrogWingBone5R.setPos(6.099999904632568F, 0.5F, 0.5F);
//        this.BalrogWingBone5R.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
//        this.BalrogWingBone6R = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone6R.mirror = true;
//        this.BalrogWingBone6R.setPos(16.200000762939453F, -0.20000000298023224F, 0.0F);
//        this.BalrogWingBone6R.addBox(0.0F, -1.0F, -1.0F, 21, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingBone6R, 0.0F, 0.0F, 0.6981317007977318F);
//        this.BalrogTail1 = new ModelRenderer(this, 25, 52);
//        this.BalrogTail1.setPos(0.5F, 0.0F, 1.0F);
//        this.BalrogTail1.addBox(-2.5F, -2.5F, -0.1F, 4, 4, 8, 0.0F);
//        this.setRotateAngle(BalrogTail1, -0.8491026810952413F, 0.0F, 0.0F);
//        this.BalrogWingR = new ModelRenderer(this, 38, 26);
//        this.BalrogWingR.mirror = true;
//        this.BalrogWingR.setPos(-4.5F, 5.5F, 3.0F);
//        this.BalrogWingR.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingR, 0.0F, -2.356194490192345F, 0.7853981633974483F);
//        this.LegLowR = new ModelRenderer(this, 22, 43);
//        this.LegLowR.mirror = true;
//        this.LegLowR.setPos(2.5F, 6.5F, -1.5F);
//        this.LegLowR.addBox(-2.5F, 0.0F, -2.5F, 5, 16, 5, 0.0F);
//        this.setRotateAngle(LegLowR, 1.3962634015954636F, 0.0F, 0.0F);
//        this.BalrogWingBone3R = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone3R.mirror = true;
//        this.BalrogWingBone3R.setPos(16.200000762939453F, -0.4000000059604645F, 0.0F);
//        this.BalrogWingBone3R.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogWingBone3R, 0.0F, 0.0F, 0.6981317007977318F);
//        this.LegLLR_1 = new ModelRenderer(this, 62, 48);
//        this.LegLLR_1.setPos(-2.0F, 12.199999809265137F, 2.0F);
//        this.LegLLR_1.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
//        this.setRotateAngle(LegLLR_1, -1.6414822147638888F, 0.0F, 0.0F);
//        this.BalrogTail5 = new ModelRenderer(this, 25, 52);
//        this.BalrogTail5.setPos(0.0F, 0.2F, 7.0F);
//        this.BalrogTail5.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 8, 0.0F);
//        this.setRotateAngle(BalrogTail5, 0.16982053621904827F, 0.0F, 0.0F);
//        this.rightArm = new ModelRenderer(this, 0, 48);
//        this.rightArm.setPos(-8.0F, -20.0F, 0.5F);
//        this.rightArm.addBox(-5.5F, -1.5F, -3.0F, 5, 10, 6, 0.0F);
//        this.BalrogHornR4 = new ModelRenderer(this, 114, 1);
//        this.BalrogHornR4.setPos(-3.700000047683716F, -0.20000000298023224F, 0.0F);
//        this.BalrogHornR4.addBox(-5.0F, -1.5F, -1.600000023841858F, 5, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogHornR4, 0.0F, -0.3490658503988659F, -0.8726646259971648F);
//        this.BalrogHornR4_1 = new ModelRenderer(this, 114, 2);
//        this.BalrogHornR4_1.setPos(-4.400000095367432F, 0.5F, 0.4000000059604645F);
//        this.BalrogHornR4_1.addBox(-5.0F, -1.5F, -1.600000023841858F, 5, 1, 1, 0.0F);
//        this.setRotateAngle(BalrogHornR4_1, 0.0F, -0.17453292519943295F, 0.0F);
//        this.BalrogTooth3 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth3.setPos(2.200000047683716F, -2.0F, -4.699999809265137F);
//        this.BalrogTooth3.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth3, -0.08726646259971647F, 0.0F, 0.0F);
//        this.BalrogTooth4 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth4.setPos(-2.200000047683716F, -2.0999999046325684F, -4.699999809265137F);
//        this.BalrogTooth4.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth4, -0.08726646259971647F, 0.0F, 0.0F);
//        this.FeetR_1 = new ModelRenderer(this, 56, 56);
//        this.FeetR_1.setPos(-0.5F, 15.699999809265137F, -0.800000011920929F);
//        this.FeetR_1.addBox(0.0F, 0.0F, 0.0F, 5, 2, 6, 0.0F);
//        this.setRotateAngle(FeetR_1, 0.9056513382072132F, 0.0F, 0.0F);
//        this.BalrogHornL1 = new ModelRenderer(this, 114, 0);
//        this.BalrogHornL1.setPos(3.5F, -2.799999952316284F, 0.0F);
//        this.BalrogHornL1.addBox(0.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
//        this.setRotateAngle(BalrogHornL1, -0.17453292519943295F, -0.17453292519943295F, -0.5235987755982988F);
//        this.BalrogTooth22 = new ModelRenderer(this, 0, 28);
//        this.BalrogTooth22.setPos(-0.699999988079071F, 2.4000000953674316F, -4.699999809265137F);
//        this.BalrogTooth22.addBox(-0.5F, -2.0F, -0.5F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(BalrogTooth22, 0.08726646259971647F, 0.0F, 0.0F);
//        this.BalrogWingBone5L = new ModelRenderer(this, 38, 26);
//        this.BalrogWingBone5L.setPos(6.099999904632568F, 0.5F, 0.5F);
//        this.BalrogWingBone5L.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
//        this.BalrogHornR3 = new ModelRenderer(this, 114, 1);
//        this.BalrogHornR3.setPos(-4.0F, -0.20000000298023224F, 0.0F);
//        this.BalrogHornR3.addBox(-5.0F, -1.5F, -1.600000023841858F, 5, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogHornR3, 0.0F, -0.3490658503988659F, -0.8726646259971648F);
//        this.BalrogHornL4 = new ModelRenderer(this, 114, 1);
//        this.BalrogHornL4.setPos(4.0F, -0.20000000298023224F, 0.0F);
//        this.BalrogHornL4.addBox(0.0F, -1.5F, -1.600000023841858F, 5, 2, 2, 0.0F);
//        this.setRotateAngle(BalrogHornL4, 0.0F, 0.3490658503988659F, 0.8726646259971648F);
//        this.bipedRightArmLower = new ModelRenderer(this, 0, 31);
//        this.bipedRightArmLower.setPos(-2.5F, 8.0F, 1.0F);
//        this.bipedRightArmLower.addBox(-2.5F, 0.0F, -3.0F, 4, 13, 4, 0.0F);
//        this.setRotateAngle(bipedRightArmLower, -0.17453292519943295F, 0.0F, 0.0F);
//        this.BalrogBody = new ModelRenderer(this, 78, 43);
//        this.BalrogBody.setPos(0.0F, -21.399999618530273F, 0.0F);
//        this.BalrogBody.addBox(-8.5F, 0.0F, -4.0F, 17, 13, 8, 0.0F);
//        this.setRotateAngle(BalrogBody, 0.2181661564992912F, 0.0F, 0.0F);
//        this.BalrogHornL4.addChild(this.BalrogHornL4_1);
//        this.BalrogMouth.addChild(this.BalrogTooth12);
//        this.BalrogWingSkin2.addChild(this.wingfingerl122);
//        this.BalrogMouth.addChild(this.BalrogTooth42);
//        this.BalrogWingSkin2.addChild(this.wingfingerl14);
//        this.BalrogWingL.addChild(this.BalrogWingBone2L);
//        this.FeetR.addChild(this.FeetR2);
//        this.BalrogSkull.addChild(this.BalrogHornR1);
//        this.leftArm.addChild(this.bipedLeftArmLower);
//        this.LegLLR.addChild(this.FeetR);
//        this.BalrogBody.addChild(this.BalrogChest);
//        this.BalrogNeck.addChild(this.BalrogSkull);
//        this.FeetR_1.addChild(this.FeetR2_1);
//        this.BalrogWingSkin1.addChild(this.wingfingerl12);
//        this.BalrogMouth.addChild(this.BalrogTooth2);
//        this.BalrogWingBone3L.addChild(this.BalrogWingBone4L);
//        this.BalrogMouth.addChild(this.BalrogTooth1);
//        this.BalrogTail1.addChild(this.BalrogTail2);
//        this.BalrogLegL.addChild(this.LegLowR_1);
//        this.BalrogWingR.addChild(this.BalrogWingBone2R);
//        this.BalrogHornL2.addChild(this.BalrogHornL3);
//        this.BalrogWingSkin1.addChild(this.wingfingerl1);
//        this.BalrogHornL1.addChild(this.BalrogHornL2);
//        this.BalrogSkull.addChild(this.BalrogMouth);
//        this.FeetR.addChild(this.FeetR1);
//        this.BalrogMouth.addChild(this.BalrogTooth32);
//        this.BalrogWingR.addChild(this.BalrogWingSkin2);
//        this.BalrogHornR1.addChild(this.BalrogHornR2);
//        this.BalrogWingL.addChild(this.BalrogWingSkin1);
//        this.FeetR_1.addChild(this.FeetR1_1);
//        this.BalrogWingSkin2.addChild(this.wingfingerl132);
//        this.BalrogWingSkin1.addChild(this.wingfingerl13);
//        this.BalrogWingBone4L.addChild(this.BalrogWingBone6L);
//        this.BalrogBody.addChild(this.BalrogWingL);
//        this.BalrogTail3.addChild(this.BalrogTail4);
//        this.BalrogWingL.addChild(this.BalrogWingBone3L);
//        this.BalrogTail2.addChild(this.BalrogTail3);
//        this.BalrogWingBone3R.addChild(this.BalrogWingBone4R);
//        this.LegLowR.addChild(this.LegLLR);
//        this.BalrogWingBone4R.addChild(this.BalrogWingBone5R);
//        this.BalrogWingBone4R.addChild(this.BalrogWingBone6R);
//        this.BalrogBody.addChild(this.BalrogWingR);
//        this.BalrogLegR.addChild(this.LegLowR);
//        this.BalrogWingR.addChild(this.BalrogWingBone3R);
//        this.LegLowR_1.addChild(this.LegLLR_1);
//        this.BalrogTail4.addChild(this.BalrogTail5);
//        this.BalrogHornR3.addChild(this.BalrogHornR4);
//        this.BalrogHornR4.addChild(this.BalrogHornR4_1);
//        this.BalrogMouth.addChild(this.BalrogTooth3);
//        this.BalrogMouth.addChild(this.BalrogTooth4);
//        this.LegLLR_1.addChild(this.FeetR_1);
//        this.BalrogSkull.addChild(this.BalrogHornL1);
//        this.BalrogMouth.addChild(this.BalrogTooth22);
//        this.BalrogWingBone4L.addChild(this.BalrogWingBone5L);
//        this.BalrogHornR2.addChild(this.BalrogHornR3);
//        this.BalrogHornL3.addChild(this.BalrogHornL4);
//        this.rightArm.addChild(this.bipedRightArmLower);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> headParts() {
//        return ImmutableList.of(this.BalrogNeck);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> bodyParts() {
//        return ImmutableList.of(this.BalrogBody, this.BalrogLegR, this.BalrogLegL, this.rightArm, this.leftArm, this.BalrogTail1);
//    }
//
//    @Override
//    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
//        this.rightArmPose = BipedModel.ArmPose.EMPTY;
//        this.leftArmPose = BipedModel.ArmPose.EMPTY;
//        ItemStack itemstack = entityIn.getItemInHand(Hand.MAIN_HAND);
//        if (itemstack.getItem() instanceof net.minecraft.item.SwordItem || itemstack.getItem() instanceof net.minecraft.item.AxeItem && entityIn.isAggressive()) {
//            if (entityIn.getMainArm() == HandSide.RIGHT) {
//                this.rightArmPose = BipedModel.ArmPose.ITEM;
//            } else {
//                this.leftArmPose = BipedModel.ArmPose.ITEM;
//            }
//        }
//
//        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
//    }
//
//    @Override
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        boolean flag = entityIn.getFallFlyingTicks() > 4;
//        boolean flag1 = entityIn.isVisuallySwimming();
//        float anim = (entityIn.tickCount + headPitch) / 20F;
//        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
//
//        if (flag) {
//            this.head.xRot = (-(float)Math.PI / 4F);
//        } else if (this.swimAmount > 0.0F) {
//            if (flag1) {
//                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-(float)Math.PI / 4F));
//            } else {
//                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, headPitch * ((float)Math.PI / 180F));
//            }
//        } else {
//            this.head.xRot = headPitch * ((float)Math.PI / 180F);
//        }
//
//        this.body.yRot = 0.0F;
//        float f = 1.0F;
//        if (flag) {
//            f = (float)entityIn.getDeltaMovement().lengthSqr();
//            f = f / 0.2F;
//            f = f * f * f;
//        }
//
//        if (f < 1.0F) {
//            f = 1.0F;
//        }
//
//        this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
//        this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
//        this.rightArm.zRot = 0.0F;
//        this.leftArm.zRot = 0.0F;
//        this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
//        this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
//        this.rightLeg.yRot = 0.0F;
//        this.leftLeg.yRot = 0.0F;
//        this.rightLeg.zRot = 0.0F;
//        this.leftLeg.zRot = 0.0F;
//        if (this.riding) {
//            this.rightArm.xRot += (-(float)Math.PI / 5F);
//            this.leftArm.xRot += (-(float)Math.PI / 5F);
//            this.rightLeg.xRot = -1.4137167F;
//            this.rightLeg.yRot = ((float)Math.PI / 10F);
//            this.rightLeg.zRot = 0.07853982F;
//            this.leftLeg.xRot = -1.4137167F;
//            this.leftLeg.yRot = (-(float)Math.PI / 10F);
//            this.leftLeg.zRot = -0.07853982F;
//        }
//
//        this.rightArm.yRot = 0.0F;
//        this.leftArm.yRot = 0.0F;
//        boolean flag2 = entityIn.getMainArm() == HandSide.RIGHT;
//        boolean flag3 = flag2 ? this.leftArmPose.isTwoHanded() : this.rightArmPose.isTwoHanded();
//        if (flag2 != flag3) {
//            this.poseLeftArm(entityIn);
//            this.poseRightArm(entityIn);
//        } else {
//            this.poseRightArm(entityIn);
//            this.poseLeftArm(entityIn);
//        }
//
//        this.setupAttackAnimation(entityIn, ageInTicks);
//        if (this.crouching) {
//            this.body.xRot = 0.5F;
//            this.rightArm.xRot += 0.4F;
//            this.leftArm.xRot += 0.4F;
//        } else {
//            this.body.xRot = 0.0F;
//        }
//
//        ModelHelper.bobArms(this.rightArm, this.leftArm, ageInTicks);
//        if (this.swimAmount > 0.0F) {
//            float f1 = limbSwing % 26.0F;
//            HandSide handside = this.getAttackArm(entityIn);
//            float f2 = handside == HandSide.RIGHT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
//            float f3 = handside == HandSide.LEFT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
//            if (f1 < 14.0F) {
//                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, 0.0F);
//                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, 0.0F);
//                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
//                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
//                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, (float)Math.PI + 1.8707964F * this.quadraticArmUpdate(f1) / this.quadraticArmUpdate(14.0F));
//                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, (float)Math.PI - 1.8707964F * this.quadraticArmUpdate(f1) / this.quadraticArmUpdate(14.0F));
//            } else if (f1 >= 14.0F && f1 < 22.0F) {
//                float f6 = (f1 - 14.0F) / 8.0F;
//                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, ((float)Math.PI / 2F) * f6);
//                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, ((float)Math.PI / 2F) * f6);
//                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
//                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
//                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, 5.012389F - 1.8707964F * f6);
//                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, 1.2707963F + 1.8707964F * f6);
//            } else if (f1 >= 22.0F && f1 < 26.0F) {
//                float f4 = (f1 - 22.0F) / 4.0F;
//                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
//                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
//                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
//                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
//                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, (float)Math.PI);
//                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, (float)Math.PI);
//            }
//
//            float f7 = 0.3F;
//            float f5 = 0.33333334F;
//            this.leftLeg.xRot = MathHelper.lerp(this.swimAmount, this.leftLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
//            this.rightLeg.xRot = MathHelper.lerp(this.swimAmount, this.rightLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
//        }
//        this.hat.copyFrom(this.head);
//
//        // Wing and Tail animations
//        BalrogTail1.yRot = MathHelper.sin(degToRad(entityIn.tickCount*7)) * degToRad(5);
//        BalrogTail2.yRot = BalrogTail1.yRot * 3;
//        BalrogTail3.yRot = BalrogTail2.yRot * 1;
//        BalrogTail4.yRot = BalrogTail3.yRot * 1;
//        BalrogTail5.yRot = BalrogTail4.yRot * 1;
//
//        BalrogWingL.xRot = (float) (Math.cos(anim) / 4D);
//        BalrogWingL.yRot = -0.2858F + (float) (Math.sin(anim) / 4D);
//
//        BalrogWingR.xRot = -(float) (Math.cos(anim) / 4D);
//        BalrogWingR.yRot = (-2.8561945f) - (float) (Math.sin(anim) / 4D);
//    }
//
//    private void poseRightArm(T entityIn) {
//        switch(this.rightArmPose) {
//            case EMPTY:
//                this.rightArm.yRot = 0.0F;
//                break;
//            case BLOCK:
//                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.9424779F;
//                this.rightArm.yRot = (-(float)Math.PI / 6F);
//                break;
//            case ITEM:
//                this.rightArm.xRot = this.rightArm.xRot * 0.5F - ((float)Math.PI / 10F);
//                this.rightArm.yRot = 0.0F;
//                break;
//            case THROW_SPEAR:
//                this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float)Math.PI;
//                this.rightArm.yRot = 0.0F;
//                break;
//            case BOW_AND_ARROW:
//                this.rightArm.yRot = -0.1F + this.head.yRot;
//                this.leftArm.yRot = 0.1F + this.head.yRot + 0.4F;
//                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                break;
//            case CROSSBOW_CHARGE:
//                ModelHelper.animateCrossbowCharge(this.rightArm, this.leftArm, entityIn, true);
//                break;
//            case CROSSBOW_HOLD:
//                ModelHelper.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
//        }
//    }
//
//    private void poseLeftArm(T entityIn) {
//        switch(this.leftArmPose) {
//            case EMPTY:
//                this.leftArm.yRot = 0.0F;
//                break;
//            case BLOCK:
//                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 0.9424779F;
//                this.leftArm.yRot = ((float)Math.PI / 6F);
//                break;
//            case ITEM:
//                this.leftArm.xRot = this.leftArm.xRot * 0.5F - ((float)Math.PI / 10F);
//                this.leftArm.yRot = 0.0F;
//                break;
//            case THROW_SPEAR:
//                this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float)Math.PI;
//                this.leftArm.yRot = 0.0F;
//                break;
//            case BOW_AND_ARROW:
//                this.rightArm.yRot = -0.1F + this.head.yRot - 0.4F;
//                this.leftArm.yRot = 0.1F + this.head.yRot;
//                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                break;
//            case CROSSBOW_CHARGE:
//                ModelHelper.animateCrossbowCharge(this.rightArm, this.leftArm, entityIn, false);
//                break;
//            case CROSSBOW_HOLD:
//                ModelHelper.animateCrossbowHold(this.rightArm, this.leftArm, this.head, false);
//        }
//    }
//
//    @Override
//    protected void setupAttackAnimation(T entityIn, float p_230486_2_) {
//        if (!(this.attackTime <= 0.0F)) {
//            HandSide handside = this.getAttackArm(entityIn);
//            ModelRenderer modelrenderer = this.getArm(handside);
//            float f = this.attackTime;
//            this.body.yRot = MathHelper.sin(MathHelper.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
//            if (handside == HandSide.LEFT) {
//                this.body.yRot *= -1.0F;
//            }
//
//            this.rightArm.yRot += this.body.yRot;
//            this.leftArm.yRot += this.body.yRot;
//            this.leftArm.xRot += this.body.yRot;
//            f = 1.0F - this.attackTime;
//            f = f * f;
//            f = f * f;
//            f = 1.0F - f;
//            float f1 = MathHelper.sin(f * (float)Math.PI);
//            float f2 = MathHelper.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
//            modelrenderer.xRot = (float)((double)modelrenderer.xRot - ((double)f1 * 1.2D + (double)f2));
//            modelrenderer.yRot += this.body.yRot * 2.0F;
//            modelrenderer.zRot += MathHelper.sin(this.attackTime * (float)Math.PI) * -0.4F;
//        }
//    }
//
//    @Override
//    protected float rotlerpRad(float p_205060_1_, float p_205060_2_, float p_205060_3_) {
//        float f = (p_205060_3_ - p_205060_2_) % ((float)Math.PI * 2F);
//        if (f < -(float)Math.PI) {
//            f += ((float)Math.PI * 2F);
//        }
//
//        if (f >= (float)Math.PI) {
//            f -= ((float)Math.PI * 2F);
//        }
//
//        return p_205060_2_ + p_205060_1_ * f;
//    }
//
//    private float quadraticArmUpdate(float p_203068_1_) {
//        return -65.0F * p_203068_1_ + p_203068_1_ * p_203068_1_;
//    }
//
//    @Override
//    public void translateToHand(HandSide hand, MatrixStack mStack) {
//        this.getArm(hand).translateAndRotate(mStack);
//        mStack.scale(1.75F, 1.75F, 1.75F);
//        mStack.translate(0, 0.125, 0);
//    }
//
//    @Override
//    protected ModelRenderer getArm(HandSide hand) {
//        return hand == HandSide.LEFT ? this.leftArm : this.rightArm;
//    }
//
//    @Override
//    protected HandSide getAttackArm(T entityIn) {
//        HandSide handside = entityIn.getMainArm();
//        return entityIn.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
//    }
//
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//}