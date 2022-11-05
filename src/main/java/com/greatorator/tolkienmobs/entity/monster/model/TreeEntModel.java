package com.greatorator.tolkienmobs.entity.monster.model;

/**
 * Tree Ent - GreatOrator
 */
//
//public class TreeEntModel<T extends TreeEntEntity> extends SegmentedModel<T> {
//    private final ModelRenderer root;
//    private final ModelRenderer head;
//    private final ModelRenderer EntNose;
//    private final ModelRenderer EntBeardMain1;
//    private final ModelRenderer EntBeardMain2;
//    private final ModelRenderer EntBeardMain3;
//    private final ModelRenderer EntBeardBot1;
//    private final ModelRenderer EntBeardBot2;
//    private final ModelRenderer EntBeardBot3;
//    private final ModelRenderer EntBeardBot4;
//    private final ModelRenderer EntBeardBot5;
//    private final ModelRenderer EntBeardBot6;
//    private final ModelRenderer EntBeardBot7;
//    private final ModelRenderer EntBeardBot8;
//    private final ModelRenderer EntBeardBot9;
//    private final ModelRenderer EntBeardBot10;
//    private final ModelRenderer EntBeardBot11;
//    private final ModelRenderer EntBeardBot12;
//    private final ModelRenderer EntBeardBot13;
//    private final ModelRenderer EntBeardBot14;
//    private final ModelRenderer EntBeardBot15;
//    private final ModelRenderer EntBrowR;
//    private final ModelRenderer EntBrowL;
//    private final ModelRenderer EntHairMain;
//    private final ModelRenderer EntHair1;
//    private final ModelRenderer EntHair2;
//    private final ModelRenderer EntHair3;
//    private final ModelRenderer EntHair4;
//    private final ModelRenderer EntHair5;
//    private final ModelRenderer EntHair6;
//    private final ModelRenderer EntHair7;
//    private final ModelRenderer EntBush1;
//    private final ModelRenderer EntBush2;
//    private final ModelRenderer EntBush4;
//    private final ModelRenderer EntBush3;
//    private final ModelRenderer EntHair1a;
//    private final ModelRenderer EntHair2a;
//    private final ModelRenderer EntHair3a;
//    private final ModelRenderer EntHair4a;
//    private final ModelRenderer EntHair5a;
//    private final ModelRenderer EntHair6a;
//    private final ModelRenderer EntHair7a;
//    private final ModelRenderer EntBush1a;
//    private final ModelRenderer EntBush2a;
//    private final ModelRenderer EntBush4a;
//    private final ModelRenderer EntBush3a;
//    private final ModelRenderer EntHair1b;
//    private final ModelRenderer EntHair2b;
//    private final ModelRenderer EntHair3b;
//    private final ModelRenderer EntHair4b;
//    private final ModelRenderer EntHair5b;
//    private final ModelRenderer EntHair6b;
//    private final ModelRenderer EntHair7b;
//    private final ModelRenderer EntBush1b;
//    private final ModelRenderer EntBush2b;
//    private final ModelRenderer EntBush4b;
//    private final ModelRenderer EntBush3b;
//    private final ModelRenderer EntHair1c;
//    private final ModelRenderer EntHair2c;
//    private final ModelRenderer EntHair3c;
//    private final ModelRenderer EntHair4c;
//    private final ModelRenderer EntHair5c;
//    private final ModelRenderer EntHair6c;
//    private final ModelRenderer EntHair7c;
//    private final ModelRenderer EntBush1c;
//    private final ModelRenderer EntBush2c;
//    private final ModelRenderer EntBush4c;
//    private final ModelRenderer EntBush3c;
//    private final ModelRenderer EntHair1d;
//    private final ModelRenderer EntHair2d;
//    private final ModelRenderer EntHair3d;
//    private final ModelRenderer EntHair4d;
//    private final ModelRenderer EntHair5d;
//    private final ModelRenderer EntHair6d;
//    private final ModelRenderer EntHair7d;
//    private final ModelRenderer EntBush1d;
//    private final ModelRenderer EntBush2d;
//    private final ModelRenderer EntBush4d;
//    private final ModelRenderer EntBush3d;
//    private final ModelRenderer EntHair1e;
//    private final ModelRenderer EntHair2e;
//    private final ModelRenderer EntHair3e;
//    private final ModelRenderer EntHair4e;
//    private final ModelRenderer EntHair5e;
//    private final ModelRenderer EntHair6e;
//    private final ModelRenderer EntHair7e;
//    private final ModelRenderer EntBush1e;
//    private final ModelRenderer EntBush2e;
//    private final ModelRenderer EntBush4e;
//    private final ModelRenderer EntBush3e;
//    private final ModelRenderer EntHair1f;
//    private final ModelRenderer EntHair2f;
//    private final ModelRenderer EntHair3f;
//    private final ModelRenderer EntHair4f;
//    private final ModelRenderer EntHair5f;
//    private final ModelRenderer EntHair6f;
//    private final ModelRenderer EntHair7f;
//    private final ModelRenderer EntBush1f;
//    private final ModelRenderer EntBush2f;
//    private final ModelRenderer EntBush4f;
//    private final ModelRenderer EntBush3f;
//    private final ModelRenderer EntHair1g;
//    private final ModelRenderer EntHair2g;
//    private final ModelRenderer EntHair3g;
//    private final ModelRenderer EntHair4g;
//    private final ModelRenderer EntHair5g;
//    private final ModelRenderer EntHair6g;
//    private final ModelRenderer EntHair7g;
//    private final ModelRenderer EntBush1g;
//    private final ModelRenderer EntBush2g;
//    private final ModelRenderer EntBush4g;
//    private final ModelRenderer EntBush3g;
//    private final ModelRenderer EntHair1h;
//    private final ModelRenderer EntHair2h;
//    private final ModelRenderer EntHair3h;
//    private final ModelRenderer EntHair4h;
//    private final ModelRenderer EntHair5h;
//    private final ModelRenderer EntHair6h;
//    private final ModelRenderer EntHair7h;
//    private final ModelRenderer EntBush1h;
//    private final ModelRenderer EntBush2h;
//    private final ModelRenderer EntBush4h;
//    private final ModelRenderer EntBush3h;
//    private final ModelRenderer body;
//    private final ModelRenderer rightArm;
//    private final ModelRenderer EntArmMidR;
//    private final ModelRenderer EntArmWristR;
//    private final ModelRenderer EntArmFingersR;
//    private final ModelRenderer leftArm;
//    private final ModelRenderer EntArmMidL;
//    private final ModelRenderer EntArmWristL;
//    private final ModelRenderer EntArmFingersL;
//    private final ModelRenderer rightLeg;
//    private final ModelRenderer EntLegMidR;
//    private final ModelRenderer EntLegFootR;
//    private final ModelRenderer EntLegRootsR;
//    private final ModelRenderer leftLeg;
//    private final ModelRenderer EntLegMidL;
//    private final ModelRenderer EntLegFootL;
//    private final ModelRenderer EntLegRootsL;
//
//    public TreeEntModel() {
//        texWidth = 256;
//        texHeight = 128;
//
//        root = new ModelRenderer(this);
//        root.setPos(0.0F, 0.0F, 0.0F);
//
//
//        head = new ModelRenderer(this);
//        head.setPos(0.0F, -48.0F, 0.0F);
//        root.addChild(head);
//        head.texOffs(188, 48).addBox(-8.5F, -15.0F, -8.5F, 17.0F, 15.0F, 17.0F, 0.0F, false);
//
//        EntNose = new ModelRenderer(this);
//        EntNose.setPos(0.0F, -8.0F, -6.8F);
//        head.addChild(EntNose);
//        setRotationAngle(EntNose, -0.192F, 0.0F, 0.0F);
//        EntNose.texOffs(215, 6).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 5.0F, 2.0F, 0.0F, false);
//
//        EntBeardMain1 = new ModelRenderer(this);
//        EntBeardMain1.setPos(0.0F, -3.0F, -7.2F);
//        head.addChild(EntBeardMain1);
//        EntBeardMain1.texOffs(80, 0).addBox(-9.0F, 0.0F, -1.5F, 18.0F, 3.0F, 1.0F, 0.0F, false);
//
//        EntBeardMain2 = new ModelRenderer(this);
//        EntBeardMain2.setPos(1.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardMain2);
//        EntBeardMain2.texOffs(80, 0).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 3.0F, 8.0F, 0.0F, false);
//
//        EntBeardMain3 = new ModelRenderer(this);
//        EntBeardMain3.setPos(-0.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardMain3);
//        EntBeardMain3.texOffs(80, 0).addBox(8.0F, 0.0F, -1.5F, 1.0F, 3.0F, 8.0F, 0.0F, false);
//
//        EntBeardBot1 = new ModelRenderer(this);
//        EntBeardBot1.setPos(1.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot1);
//        EntBeardBot1.texOffs(80, 8).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot2 = new ModelRenderer(this);
//        EntBeardBot2.setPos(2.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot2);
//        EntBeardBot2.texOffs(80, 9).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot3 = new ModelRenderer(this);
//        EntBeardBot3.setPos(3.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot3);
//        EntBeardBot3.texOffs(80, 6).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot4 = new ModelRenderer(this);
//        EntBeardBot4.setPos(4.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot4);
//        EntBeardBot4.texOffs(80, 11).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot5 = new ModelRenderer(this);
//        EntBeardBot5.setPos(5.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot5);
//        EntBeardBot5.texOffs(80, 5).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 13.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot6 = new ModelRenderer(this);
//        EntBeardBot6.setPos(6.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot6);
//        EntBeardBot6.texOffs(80, 6).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 16.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot7 = new ModelRenderer(this);
//        EntBeardBot7.setPos(7.2F, 2.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot7);
//        EntBeardBot7.texOffs(80, 6).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 16.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot8 = new ModelRenderer(this);
//        EntBeardBot8.setPos(10.2F, 2.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot8);
//        EntBeardBot8.texOffs(80, 6).addBox(-12.0F, 0.0F, -1.5F, 3.0F, 19.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot9 = new ModelRenderer(this);
//        EntBeardBot9.setPos(11.2F, 2.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot9);
//        EntBeardBot9.texOffs(80, 6).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 16.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot10 = new ModelRenderer(this);
//        EntBeardBot10.setPos(12.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot10);
//        EntBeardBot10.texOffs(80, 5).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 16.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot11 = new ModelRenderer(this);
//        EntBeardBot11.setPos(13.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot11);
//        EntBeardBot11.texOffs(80, 11).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 13.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot12 = new ModelRenderer(this);
//        EntBeardBot12.setPos(14.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot12);
//        EntBeardBot12.texOffs(80, 5).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot13 = new ModelRenderer(this);
//        EntBeardBot13.setPos(15.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot13);
//        EntBeardBot13.texOffs(80, 6).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot14 = new ModelRenderer(this);
//        EntBeardBot14.setPos(16.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot14);
//        EntBeardBot14.texOffs(80, 9).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//        EntBeardBot15 = new ModelRenderer(this);
//        EntBeardBot15.setPos(17.2F, 0.0F, 0.0F);
//        EntBeardMain1.addChild(EntBeardBot15);
//        EntBeardBot15.texOffs(80, 8).addBox(-10.0F, 0.0F, -1.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBrowR = new ModelRenderer(this);
//        EntBrowR.setPos(7.5F, -14.0F, -7.5F);
//        head.addChild(EntBrowR);
//        setRotationAngle(EntBrowR, -0.0873F, 0.0F, 0.0873F);
//        EntBrowR.texOffs(194, 13).addBox(-6.0F, 0.0F, -1.5F, 6.0F, 2.0F, 1.0F, 0.0F, false);
//
//        EntBrowL = new ModelRenderer(this);
//        EntBrowL.setPos(-7.5F, -14.0F, -7.5F);
//        head.addChild(EntBrowL);
//        setRotationAngle(EntBrowL, -0.0873F, 0.0F, -0.0873F);
//        EntBrowL.texOffs(194, 13).addBox(0.0F, 0.0F, -1.5F, 6.0F, 2.0F, 1.0F, 0.0F, false);
//
//        EntHairMain = new ModelRenderer(this);
//        EntHairMain.setPos(8.1F, -14.8F, -6.1F);
//        head.addChild(EntHairMain);
//        EntHairMain.texOffs(200, 24).addBox(-16.0F, 0.0F, -1.5F, 16.0F, 1.0F, 16.0F, 0.0F, false);
//
//        EntHair1 = new ModelRenderer(this);
//        EntHair1.setPos(-0.7F, 0.5F, -1.0F);
//        EntHairMain.addChild(EntHair1);
//        setRotationAngle(EntHair1, 0.1745F, -0.1745F, -0.1745F);
//        EntHair1.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2 = new ModelRenderer(this);
//        EntHair2.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1.addChild(EntHair2);
//        setRotationAngle(EntHair2, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3 = new ModelRenderer(this);
//        EntHair3.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1.addChild(EntHair3);
//        setRotationAngle(EntHair3, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4 = new ModelRenderer(this);
//        EntHair4.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1.addChild(EntHair4);
//        setRotationAngle(EntHair4, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5 = new ModelRenderer(this);
//        EntHair5.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1.addChild(EntHair5);
//        setRotationAngle(EntHair5, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6 = new ModelRenderer(this);
//        EntHair6.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1.addChild(EntHair6);
//        setRotationAngle(EntHair6, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7 = new ModelRenderer(this);
//        EntHair7.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1.addChild(EntHair7);
//        setRotationAngle(EntHair7, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1 = new ModelRenderer(this);
//        EntBush1.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1.addChild(EntBush1);
//        setRotationAngle(EntBush1, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2 = new ModelRenderer(this);
//        EntBush2.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1.addChild(EntBush2);
//        setRotationAngle(EntBush2, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4 = new ModelRenderer(this);
//        EntBush4.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1.addChild(EntBush4);
//        setRotationAngle(EntBush4, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3 = new ModelRenderer(this);
//        EntBush3.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1.addChild(EntBush3);
//        setRotationAngle(EntBush3, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1a = new ModelRenderer(this);
//        EntHair1a.setPos(-5.4F, 0.5F, -1.0F);
//        EntHairMain.addChild(EntHair1a);
//        setRotationAngle(EntHair1a, 0.1012F, -0.6981F, 0.2618F);
//        EntHair1a.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2a = new ModelRenderer(this);
//        EntHair2a.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1a.addChild(EntHair2a);
//        setRotationAngle(EntHair2a, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2a.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3a = new ModelRenderer(this);
//        EntHair3a.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1a.addChild(EntHair3a);
//        setRotationAngle(EntHair3a, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3a.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4a = new ModelRenderer(this);
//        EntHair4a.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1a.addChild(EntHair4a);
//        setRotationAngle(EntHair4a, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4a.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5a = new ModelRenderer(this);
//        EntHair5a.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1a.addChild(EntHair5a);
//        setRotationAngle(EntHair5a, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5a.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6a = new ModelRenderer(this);
//        EntHair6a.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1a.addChild(EntHair6a);
//        setRotationAngle(EntHair6a, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6a.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7a = new ModelRenderer(this);
//        EntHair7a.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1a.addChild(EntHair7a);
//        setRotationAngle(EntHair7a, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7a.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1a = new ModelRenderer(this);
//        EntBush1a.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1a.addChild(EntBush1a);
//        setRotationAngle(EntBush1a, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1a.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2a = new ModelRenderer(this);
//        EntBush2a.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1a.addChild(EntBush2a);
//        setRotationAngle(EntBush2a, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2a.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4a = new ModelRenderer(this);
//        EntBush4a.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1a.addChild(EntBush4a);
//        setRotationAngle(EntBush4a, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4a.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3a = new ModelRenderer(this);
//        EntBush3a.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1a.addChild(EntBush3a);
//        setRotationAngle(EntBush3a, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3a.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1b = new ModelRenderer(this);
//        EntHair1b.setPos(-14.8F, 0.5F, -1.0F);
//        EntHairMain.addChild(EntHair1b);
//        setRotationAngle(EntHair1b, 0.1745F, -0.1745F, 0.4363F);
//        EntHair1b.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2b = new ModelRenderer(this);
//        EntHair2b.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1b.addChild(EntHair2b);
//        setRotationAngle(EntHair2b, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2b.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3b = new ModelRenderer(this);
//        EntHair3b.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1b.addChild(EntHair3b);
//        setRotationAngle(EntHair3b, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3b.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4b = new ModelRenderer(this);
//        EntHair4b.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1b.addChild(EntHair4b);
//        setRotationAngle(EntHair4b, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4b.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5b = new ModelRenderer(this);
//        EntHair5b.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1b.addChild(EntHair5b);
//        setRotationAngle(EntHair5b, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5b.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6b = new ModelRenderer(this);
//        EntHair6b.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1b.addChild(EntHair6b);
//        setRotationAngle(EntHair6b, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6b.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7b = new ModelRenderer(this);
//        EntHair7b.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1b.addChild(EntHair7b);
//        setRotationAngle(EntHair7b, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7b.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1b = new ModelRenderer(this);
//        EntBush1b.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1b.addChild(EntBush1b);
//        setRotationAngle(EntBush1b, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1b.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2b = new ModelRenderer(this);
//        EntBush2b.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1b.addChild(EntBush2b);
//        setRotationAngle(EntBush2b, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2b.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4b = new ModelRenderer(this);
//        EntBush4b.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1b.addChild(EntBush4b);
//        setRotationAngle(EntBush4b, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4b.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3b = new ModelRenderer(this);
//        EntBush3b.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1b.addChild(EntBush3b);
//        setRotationAngle(EntBush3b, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3b.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1c = new ModelRenderer(this);
//        EntHair1c.setPos(-0.4F, 0.0F, 7.0F);
//        EntHairMain.addChild(EntHair1c);
//        setRotationAngle(EntHair1c, -0.1745F, 0.1745F, -0.1745F);
//        EntHair1c.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2c = new ModelRenderer(this);
//        EntHair2c.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1c.addChild(EntHair2c);
//        setRotationAngle(EntHair2c, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2c.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3c = new ModelRenderer(this);
//        EntHair3c.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1c.addChild(EntHair3c);
//        setRotationAngle(EntHair3c, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3c.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4c = new ModelRenderer(this);
//        EntHair4c.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1c.addChild(EntHair4c);
//        setRotationAngle(EntHair4c, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4c.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5c = new ModelRenderer(this);
//        EntHair5c.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1c.addChild(EntHair5c);
//        setRotationAngle(EntHair5c, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5c.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6c = new ModelRenderer(this);
//        EntHair6c.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1c.addChild(EntHair6c);
//        setRotationAngle(EntHair6c, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6c.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7c = new ModelRenderer(this);
//        EntHair7c.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1c.addChild(EntHair7c);
//        setRotationAngle(EntHair7c, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7c.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1c = new ModelRenderer(this);
//        EntBush1c.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1c.addChild(EntBush1c);
//        setRotationAngle(EntBush1c, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1c.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2c = new ModelRenderer(this);
//        EntBush2c.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1c.addChild(EntBush2c);
//        setRotationAngle(EntBush2c, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2c.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4c = new ModelRenderer(this);
//        EntBush4c.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1c.addChild(EntBush4c);
//        setRotationAngle(EntBush4c, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4c.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3c = new ModelRenderer(this);
//        EntBush3c.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1c.addChild(EntBush3c);
//        setRotationAngle(EntBush3c, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3c.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1d = new ModelRenderer(this);
//        EntHair1d.setPos(-6.1F, 0.0F, 5.7F);
//        EntHairMain.addChild(EntHair1d);
//        setRotationAngle(EntHair1d, -0.2618F, 0.2618F, 0.2618F);
//        EntHair1d.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2d = new ModelRenderer(this);
//        EntHair2d.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1d.addChild(EntHair2d);
//        setRotationAngle(EntHair2d, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2d.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3d = new ModelRenderer(this);
//        EntHair3d.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1d.addChild(EntHair3d);
//        setRotationAngle(EntHair3d, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3d.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4d = new ModelRenderer(this);
//        EntHair4d.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1d.addChild(EntHair4d);
//        setRotationAngle(EntHair4d, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4d.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5d = new ModelRenderer(this);
//        EntHair5d.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1d.addChild(EntHair5d);
//        setRotationAngle(EntHair5d, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5d.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6d = new ModelRenderer(this);
//        EntHair6d.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1d.addChild(EntHair6d);
//        setRotationAngle(EntHair6d, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6d.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7d = new ModelRenderer(this);
//        EntHair7d.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1d.addChild(EntHair7d);
//        setRotationAngle(EntHair7d, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7d.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1d = new ModelRenderer(this);
//        EntBush1d.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1d.addChild(EntBush1d);
//        setRotationAngle(EntBush1d, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1d.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2d = new ModelRenderer(this);
//        EntBush2d.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1d.addChild(EntBush2d);
//        setRotationAngle(EntBush2d, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2d.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4d = new ModelRenderer(this);
//        EntBush4d.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1d.addChild(EntBush4d);
//        setRotationAngle(EntBush4d, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4d.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3d = new ModelRenderer(this);
//        EntBush3d.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1d.addChild(EntBush3d);
//        setRotationAngle(EntBush3d, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3d.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1e = new ModelRenderer(this);
//        EntHair1e.setPos(-15.2F, 0.5F, 5.4F);
//        EntHairMain.addChild(EntHair1e);
//        setRotationAngle(EntHair1e, -0.1745F, 0.1745F, 0.1745F);
//        EntHair1e.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2e = new ModelRenderer(this);
//        EntHair2e.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1e.addChild(EntHair2e);
//        setRotationAngle(EntHair2e, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2e.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3e = new ModelRenderer(this);
//        EntHair3e.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1e.addChild(EntHair3e);
//        setRotationAngle(EntHair3e, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3e.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4e = new ModelRenderer(this);
//        EntHair4e.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1e.addChild(EntHair4e);
//        setRotationAngle(EntHair4e, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4e.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5e = new ModelRenderer(this);
//        EntHair5e.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1e.addChild(EntHair5e);
//        setRotationAngle(EntHair5e, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5e.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6e = new ModelRenderer(this);
//        EntHair6e.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1e.addChild(EntHair6e);
//        setRotationAngle(EntHair6e, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6e.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7e = new ModelRenderer(this);
//        EntHair7e.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1e.addChild(EntHair7e);
//        setRotationAngle(EntHair7e, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7e.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1e = new ModelRenderer(this);
//        EntBush1e.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1e.addChild(EntBush1e);
//        setRotationAngle(EntBush1e, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1e.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2e = new ModelRenderer(this);
//        EntBush2e.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1e.addChild(EntBush2e);
//        setRotationAngle(EntBush2e, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2e.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4e = new ModelRenderer(this);
//        EntBush4e.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1e.addChild(EntBush4e);
//        setRotationAngle(EntBush4e, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4e.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3e = new ModelRenderer(this);
//        EntBush3e.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1e.addChild(EntBush3e);
//        setRotationAngle(EntBush3e, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3e.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1f = new ModelRenderer(this);
//        EntHair1f.setPos(-0.5F, 0.5F, 14.0F);
//        EntHairMain.addChild(EntHair1f);
//        setRotationAngle(EntHair1f, -0.1745F, -0.1745F, -0.3491F);
//        EntHair1f.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2f = new ModelRenderer(this);
//        EntHair2f.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1f.addChild(EntHair2f);
//        setRotationAngle(EntHair2f, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2f.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3f = new ModelRenderer(this);
//        EntHair3f.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1f.addChild(EntHair3f);
//        setRotationAngle(EntHair3f, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3f.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4f = new ModelRenderer(this);
//        EntHair4f.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1f.addChild(EntHair4f);
//        setRotationAngle(EntHair4f, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4f.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5f = new ModelRenderer(this);
//        EntHair5f.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1f.addChild(EntHair5f);
//        setRotationAngle(EntHair5f, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5f.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6f = new ModelRenderer(this);
//        EntHair6f.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1f.addChild(EntHair6f);
//        setRotationAngle(EntHair6f, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6f.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7f = new ModelRenderer(this);
//        EntHair7f.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1f.addChild(EntHair7f);
//        setRotationAngle(EntHair7f, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7f.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1f = new ModelRenderer(this);
//        EntBush1f.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1f.addChild(EntBush1f);
//        setRotationAngle(EntBush1f, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1f.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2f = new ModelRenderer(this);
//        EntBush2f.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1f.addChild(EntBush2f);
//        setRotationAngle(EntBush2f, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2f.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4f = new ModelRenderer(this);
//        EntBush4f.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1f.addChild(EntBush4f);
//        setRotationAngle(EntBush4f, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4f.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3f = new ModelRenderer(this);
//        EntBush3f.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1f.addChild(EntBush3f);
//        setRotationAngle(EntBush3f, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3f.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1g = new ModelRenderer(this);
//        EntHair1g.setPos(-8.0F, 0.5F, 13.9F);
//        EntHairMain.addChild(EntHair1g);
//        setRotationAngle(EntHair1g, -0.3491F, -0.1745F, -0.1745F);
//        EntHair1g.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2g = new ModelRenderer(this);
//        EntHair2g.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1g.addChild(EntHair2g);
//        setRotationAngle(EntHair2g, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2g.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3g = new ModelRenderer(this);
//        EntHair3g.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1g.addChild(EntHair3g);
//        setRotationAngle(EntHair3g, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3g.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4g = new ModelRenderer(this);
//        EntHair4g.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1g.addChild(EntHair4g);
//        setRotationAngle(EntHair4g, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4g.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5g = new ModelRenderer(this);
//        EntHair5g.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1g.addChild(EntHair5g);
//        setRotationAngle(EntHair5g, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5g.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6g = new ModelRenderer(this);
//        EntHair6g.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1g.addChild(EntHair6g);
//        setRotationAngle(EntHair6g, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6g.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7g = new ModelRenderer(this);
//        EntHair7g.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1g.addChild(EntHair7g);
//        setRotationAngle(EntHair7g, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7g.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1g = new ModelRenderer(this);
//        EntBush1g.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1g.addChild(EntBush1g);
//        setRotationAngle(EntBush1g, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1g.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2g = new ModelRenderer(this);
//        EntBush2g.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1g.addChild(EntBush2g);
//        setRotationAngle(EntBush2g, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2g.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4g = new ModelRenderer(this);
//        EntBush4g.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1g.addChild(EntBush4g);
//        setRotationAngle(EntBush4g, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4g.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3g = new ModelRenderer(this);
//        EntBush3g.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1g.addChild(EntBush3g);
//        setRotationAngle(EntBush3g, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3g.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        EntHair1h = new ModelRenderer(this);
//        EntHair1h.setPos(-15.0F, 0.5F, 14.0F);
//        EntHairMain.addChild(EntHair1h);
//        setRotationAngle(EntHair1h, -0.2618F, -0.1745F, 0.3491F);
//        EntHair1h.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair2h = new ModelRenderer(this);
//        EntHair2h.setPos(0.0F, -4.7F, 0.0F);
//        EntHair1h.addChild(EntHair2h);
//        setRotationAngle(EntHair2h, 0.1745F, -0.1745F, 0.1745F);
//        EntHair2h.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair3h = new ModelRenderer(this);
//        EntHair3h.setPos(-0.3F, -8.0F, -0.8F);
//        EntHair1h.addChild(EntHair3h);
//        setRotationAngle(EntHair3h, 0.1745F, -0.1745F, 1.2217F);
//        EntHair3h.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair4h = new ModelRenderer(this);
//        EntHair4h.setPos(-0.7F, -9.2F, -0.8F);
//        EntHair1h.addChild(EntHair4h);
//        setRotationAngle(EntHair4h, 0.1745F, -0.1745F, -0.1745F);
//        EntHair4h.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair5h = new ModelRenderer(this);
//        EntHair5h.setPos(-0.3F, -12.0F, -1.4F);
//        EntHair1h.addChild(EntHair5h);
//        setRotationAngle(EntHair5h, -0.7854F, -0.1745F, 1.2217F);
//        EntHair5h.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair6h = new ModelRenderer(this);
//        EntHair6h.setPos(0.1F, -13.6F, -1.6F);
//        EntHair1h.addChild(EntHair6h);
//        setRotationAngle(EntHair6h, 0.1745F, -0.1745F, -1.2217F);
//        EntHair6h.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntHair7h = new ModelRenderer(this);
//        EntHair7h.setPos(0.2F, -14.0F, -1.8F);
//        EntHair1h.addChild(EntHair7h);
//        setRotationAngle(EntHair7h, -0.1745F, -0.1745F, -0.1745F);
//        EntHair7h.texOffs(200, 24).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        EntBush1h = new ModelRenderer(this);
//        EntBush1h.setPos(-5.0F, -9.5F, -1.5F);
//        EntHair1h.addChild(EntBush1h);
//        setRotationAngle(EntBush1h, 0.1745F, -0.1745F, 1.2217F);
//        EntBush1h.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush2h = new ModelRenderer(this);
//        EntBush2h.setPos(-3.8F, -11.9F, 4.1F);
//        EntHair1h.addChild(EntBush2h);
//        setRotationAngle(EntBush2h, 0.7854F, -0.1745F, 1.2217F);
//        EntBush2h.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush4h = new ModelRenderer(this);
//        EntBush4h.setPos(4.5F, -15.2F, -2.5F);
//        EntHair1h.addChild(EntBush4h);
//        setRotationAngle(EntBush4h, 0.1745F, -0.1745F, -1.2217F);
//        EntBush4h.texOffs(79, 6).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
//
//        EntBush3h = new ModelRenderer(this);
//        EntBush3h.setPos(2.3F, -20.8F, -1.8F);
//        EntHair1h.addChild(EntBush3h);
//        setRotationAngle(EntBush3h, -0.1745F, -0.1745F, -0.1745F);
//        EntBush3h.texOffs(79, 6).addBox(-3.5F, -3.0F, -1.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
//
//        body = new ModelRenderer(this);
//        body.setPos(0.0F, -48.0F, 0.0F);
//        root.addChild(body);
//        body.texOffs(192, 0).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 32.0F, 16.0F, 0.0F, false);
//
//        rightArm = new ModelRenderer(this);
//        rightArm.setPos(8.0F, -45.0F, 0.0F);
//        root.addChild(rightArm);
//        rightArm.texOffs(153, 0).addBox(0.0F, -4.5F, -4.5F, 9.0F, 9.0F, 9.0F, 0.0F, false);
//
//        EntArmMidR = new ModelRenderer(this);
//        EntArmMidR.setPos(4.5F, 3.0F, 0.0F);
//        rightArm.addChild(EntArmMidR);
//        EntArmMidR.texOffs(162, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 19.0F, 5.0F, 0.0F, false);
//
//        EntArmWristR = new ModelRenderer(this);
//        EntArmWristR.setPos(0.0F, 19.0F, 0.0F);
//        EntArmMidR.addChild(EntArmWristR);
//        EntArmWristR.texOffs(163, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);
//
//        EntArmFingersR = new ModelRenderer(this);
//        EntArmFingersR.setPos(0.0F, 13.0F, 0.0F);
//        EntArmWristR.addChild(EntArmFingersR);
//        EntArmFingersR.texOffs(40, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);
//
//        leftArm = new ModelRenderer(this);
//        leftArm.setPos(-8.0F, -45.0F, 0.0F);
//        root.addChild(leftArm);
//        leftArm.texOffs(153, 0).addBox(-9.0F, -4.5F, -4.5F, 9.0F, 9.0F, 9.0F, 0.0F, true);
//
//        EntArmMidL = new ModelRenderer(this);
//        EntArmMidL.setPos(-4.5F, 3.0F, 0.0F);
//        leftArm.addChild(EntArmMidL);
//        EntArmMidL.texOffs(162, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 19.0F, 5.0F, 0.0F, true);
//
//        EntArmWristL = new ModelRenderer(this);
//        EntArmWristL.setPos(0.0F, 19.0F, 0.0F);
//        EntArmMidL.addChild(EntArmWristL);
//        EntArmWristL.texOffs(163, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, true);
//
//        EntArmFingersL = new ModelRenderer(this);
//        EntArmFingersL.setPos(0.0F, 13.0F, 0.0F);
//        EntArmWristL.addChild(EntArmFingersL);
//        EntArmFingersL.texOffs(40, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, true);
//
//        rightLeg = new ModelRenderer(this);
//        rightLeg.setPos(5.5F, -20.0F, 0.0F);
//        root.addChild(rightLeg);
//        rightLeg.texOffs(152, 24).addBox(-3.0F, 0.0F, -5.5F, 6.0F, 19.0F, 11.0F, 0.0F, false);
//
//        EntLegMidR = new ModelRenderer(this);
//        EntLegMidR.setPos(0.5F, 19.0F, -0.5F);
//        rightLeg.addChild(EntLegMidR);
//        EntLegMidR.texOffs(147, 24).addBox(-4.0F, 0.0F, -5.5F, 7.0F, 19.0F, 12.0F, 0.0F, false);
//
//        EntLegFootR = new ModelRenderer(this);
//        EntLegFootR.setPos(0.5F, 19.0F, -1.0F);
//        EntLegMidR.addChild(EntLegFootR);
//        EntLegFootR.texOffs(141, 24).addBox(-5.0F, 0.0F, -5.5F, 8.0F, 6.0F, 14.0F, 0.0F, false);
//
//        EntLegRootsR = new ModelRenderer(this);
//        EntLegRootsR.setPos(-0.5F, 3.8F, 1.5F);
//        EntLegFootR.addChild(EntLegRootsR);
//        EntLegRootsR.texOffs(0, 0).addBox(-5.5F, 0.0F, -9.0F, 11.0F, 2.0F, 18.0F, 0.0F, false);
//
//        leftLeg = new ModelRenderer(this);
//        leftLeg.setPos(-5.5F, -20.0F, 0.0F);
//        root.addChild(leftLeg);
//        leftLeg.texOffs(152, 24).addBox(-3.0F, 0.0F, -5.5F, 6.0F, 19.0F, 11.0F, 0.0F, false);
//
//        EntLegMidL = new ModelRenderer(this);
//        EntLegMidL.setPos(0.5F, 19.0F, -0.5F);
//        leftLeg.addChild(EntLegMidL);
//        EntLegMidL.texOffs(147, 24).addBox(-4.0F, 0.0F, -5.5F, 7.0F, 19.0F, 12.0F, 0.0F, false);
//
//        EntLegFootL = new ModelRenderer(this);
//        EntLegFootL.setPos(0.5F, 19.0F, -1.0F);
//        EntLegMidL.addChild(EntLegFootL);
//        EntLegFootL.texOffs(141, 24).addBox(-5.0F, 0.0F, -5.5F, 8.0F, 6.0F, 14.0F, 0.0F, false);
//
//        EntLegRootsL = new ModelRenderer(this);
//        EntLegRootsL.setPos(-1.5F, 3.8F, 1.5F);
//        EntLegFootL.addChild(EntLegRootsL);
//        EntLegRootsL.texOffs(0, 0).addBox(-5.5F, 0.0F, -9.0F, 11.0F, 2.0F, 18.0F, 0.0F, false);
//    }
//
//    @Override
//    public Iterable<ModelRenderer> parts() {
//        return ImmutableList.of(this.head, this.body, this.rightLeg, this.leftLeg, this.rightArm, this.leftArm);
//    }
//
//    @Override
//    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
//        int i = entityIn.getAttackAnimationTick();
//        if (i > 0) {
//            this.rightArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float)i - partialTick, 10.0F);
//            this.leftArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float)i - partialTick, 10.0F);
//        }
//    }
//
//    @Override
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
//        this.head.xRot = headPitch * ((float)Math.PI / 180F);
//        this.rightLeg.xRot = -1.5F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
//        this.leftLeg.xRot = 1.5F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
//        this.rightLeg.yRot = 0.0F;
//        this.leftLeg.yRot = 0.0F;
//    }
//
//    @Override
//    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//}