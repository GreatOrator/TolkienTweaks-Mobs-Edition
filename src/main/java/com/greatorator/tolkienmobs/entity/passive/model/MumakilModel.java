package com.greatorator.tolkienmobs.entity.passive.model;

/**
 * Mumakil - GreatOrator
 */
//
//public class MumakilModel<T extends Entity> extends AgeableModel<T> {
//    private final ModelRenderer root;
//    private final ModelRenderer MumuLegFL;
//    private final ModelRenderer MumuLegFR;
//    private final ModelRenderer MumuLegRR;
//    private final ModelRenderer MumuLegRL;
//    private final ModelRenderer MumuBody;
//    private final ModelRenderer tail_r1;
//    private final ModelRenderer MumuHump1;
//    private final ModelRenderer MumuBottom;
//    private final ModelRenderer MumuHump2;
//    private final ModelRenderer MumuNeck;
//    private final ModelRenderer MumuSkull;
//    private final ModelRenderer MumuEarL;
//    private final ModelRenderer MumuEarR;
//    private final ModelRenderer MumuTuskFR1;
//    private final ModelRenderer MumuTuskFR2;
//    private final ModelRenderer MumuTuskFR3;
//    private final ModelRenderer MumuTuskRR1;
//    private final ModelRenderer MumuTuskRR2;
//    private final ModelRenderer MumuTuskFL1;
//    private final ModelRenderer MumuTuskFL2;
//    private final ModelRenderer MumuTuskFL3;
//    private final ModelRenderer MumuTuskRL1;
//    private final ModelRenderer MumuTuskRL2;
//    private final ModelRenderer MumuTrunk1;
//    private final ModelRenderer MumuTrunk2;
//    private final ModelRenderer MumuTrunk3;
//
//
//    /** Allows customization of baby animal */
//    public ModelRenderer childSkull;
//    public ModelRenderer childNeck;
//    public ModelRenderer childEarL;
//    public ModelRenderer childEarR;
//    public ModelRenderer childTrunk1;
//
//    public MumakilModel() {
//        texWidth = 256;
//        texHeight = 256;
//
//        root = new ModelRenderer(this);
//        root.setPos(0.0F, 0.0F, 0.0F);
//
//
//        MumuLegFL = new ModelRenderer(this);
//        MumuLegFL.setPos(2.0F, -8.0F, -18.5F);
//        root.addChild(MumuLegFL);
//        MumuLegFL.texOffs(212, 213).addBox(6.0F, 0.0F, -5.5F, 11.0F, 32.0F, 11.0F, 0.0F, true);
//
//        MumuLegFR = new ModelRenderer(this);
//        MumuLegFR.setPos(-8.0F, -8.0F, -18.5F);
//        root.addChild(MumuLegFR);
//        MumuLegFR.texOffs(212, 213).addBox(-11.0F, 0.0F, -5.5F, 11.0F, 32.0F, 11.0F, 0.0F, false);
//
//        MumuLegRR = new ModelRenderer(this);
//        MumuLegRR.setPos(-8.0F, -8.0F, 18.5F);
//        root.addChild(MumuLegRR);
//        MumuLegRR.texOffs(212, 170).addBox(-11.0F, 0.0F, -5.5F, 11.0F, 32.0F, 11.0F, 0.0F, false);
//
//        MumuLegRL = new ModelRenderer(this);
//        MumuLegRL.setPos(2.0F, -8.0F, 18.5F);
//        root.addChild(MumuLegRL);
//        MumuLegRL.texOffs(212, 170).addBox(6.0F, 0.0F, -5.5F, 11.0F, 32.0F, 11.0F, 0.0F, true);
//
//        MumuBody = new ModelRenderer(this);
//        MumuBody.setPos(1.5F, -15.0F, -23.2F);
//        root.addChild(MumuBody);
//        setRotationAngle(MumuBody, -0.1134F, 0.0F, 0.0F);
//        MumuBody.texOffs(0, 0).addBox(-20.0F, -12.5F, 0.0F, 37.0F, 25.0F, 47.0F, 0.0F, false);
//
//        tail_r1 = new ModelRenderer(this);
//        tail_r1.setPos(-2.0F, 2.1F, 46.2F);
//        MumuBody.addChild(tail_r1);
//        setRotationAngle(tail_r1, 0.1745F, 0.0F, 0.0F);
//        tail_r1.texOffs(26, 23).addBox(-3.0F, -1.0906F, 0.8665F, 6.0F, 20.0F, 0.0F, 0.0F, false);
//
//        MumuHump1 = new ModelRenderer(this);
//        MumuHump1.setPos(-2.0F, -2.1F, 15.5F);
//        MumuBody.addChild(MumuHump1);
//        setRotationAngle(MumuHump1, -0.0942F, 0.0F, 0.0F);
//        MumuHump1.texOffs(0, 229).addBox(-16.0F, -12.5F, 0.0F, 33.0F, 5.0F, 22.0F, 0.0F, false);
//
//        MumuBottom = new ModelRenderer(this);
//        MumuBottom.setPos(0.0F, 10.5F, 3.5F);
//        MumuBody.addChild(MumuBottom);
//        setRotationAngle(MumuBottom, 0.0873F, 0.0F, 0.0F);
//        MumuBottom.texOffs(0, 188).addBox(-12.0F, 0.0F, 0.0F, 23.0F, 5.0F, 35.0F, 0.0F, false);
//
//        MumuHump2 = new ModelRenderer(this);
//        MumuHump2.setPos(-2.0F, -1.7F, 0.4F);
//        MumuBody.addChild(MumuHump2);
//        setRotationAngle(MumuHump2, 0.0227F, 0.0F, 0.0F);
//        MumuHump2.texOffs(5, 234).addBox(-16.0F, -12.5F, -0.2F, 33.0F, 5.0F, 17.0F, 0.0F, false);
//
//        MumuNeck = new ModelRenderer(this);
//        MumuNeck.setPos(0.0F, -17.5F, -21.5F);
//        root.addChild(MumuNeck);
//        setRotationAngle(MumuNeck, 0.0873F, 0.0F, 0.0F);
//        MumuNeck.texOffs(0, 0).addBox(-5.5F, -5.5F, -10.5F, 11.0F, 11.0F, 11.0F, 0.0F, false);
//
//        MumuSkull = new ModelRenderer(this);
//        MumuSkull.setPos(3.0F, -3.0F, -13.5F);
//        MumuNeck.addChild(MumuSkull);
//        MumuSkull.texOffs(146, 224).addBox(-11.5F, -5.5F, -10.5F, 17.0F, 17.0F, 15.0F, 0.0F, false);
//
//        MumuEarL = new ModelRenderer(this);
//        MumuEarL.setPos(-8.6F, -2.0F, -16.0F);
//        MumuNeck.addChild(MumuEarL);
//        setRotationAngle(MumuEarL, 0.0F, 0.7854F, -0.7854F);
//        MumuEarL.texOffs(117, 244).addBox(-3.5F, -6.0F, -0.5F, 11.0F, 11.0F, 1.0F, 0.0F, false);
//
//        MumuEarR = new ModelRenderer(this);
//        MumuEarR.setPos(8.5F, -2.0F, -17.0F);
//        MumuNeck.addChild(MumuEarR);
//        setRotationAngle(MumuEarR, 0.0F, -0.7854F, 0.7854F);
//        MumuEarR.texOffs(117, 244).addBox(-7.5F, -6.1F, -0.5F, 11.0F, 11.0F, 1.0F, 0.0F, true);
//
//        MumuTuskFR1 = new ModelRenderer(this);
//        MumuTuskFR1.setPos(7.0F, 5.0F, -21.1F);
//        MumuNeck.addChild(MumuTuskFR1);
//        setRotationAngle(MumuTuskFR1, 1.0472F, -0.2618F, 0.0F);
//        MumuTuskFR1.texOffs(0, 201).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 9.0F, 0.0F, true);
//
//        MumuTuskFR2 = new ModelRenderer(this);
//        MumuTuskFR2.setPos(9.1F, 18.3F, -29.0F);
//        MumuNeck.addChild(MumuTuskFR2);
//        setRotationAngle(MumuTuskFR2, 0.925F, -0.2618F, 0.0F);
//        MumuTuskFR2.texOffs(0, 201).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, 0.0F, true);
//
//        MumuTuskFR3 = new ModelRenderer(this);
//        MumuTuskFR3.setPos(10.4F, 24.9F, -33.9F);
//        MumuNeck.addChild(MumuTuskFR3);
//        setRotationAngle(MumuTuskFR3, 0.925F, -0.2618F, 0.0F);
//        MumuTuskFR3.texOffs(0, 201).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, 0.0F, true);
//
//        MumuTuskRR1 = new ModelRenderer(this);
//        MumuTuskRR1.setPos(6.5F, 5.1F, -17.0F);
//        MumuNeck.addChild(MumuTuskRR1);
//        setRotationAngle(MumuTuskRR1, 1.0472F, -0.6109F, 0.0F);
//        MumuTuskRR1.texOffs(0, 201).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 9.0F, 0.0F, true);
//
//        MumuTuskRR2 = new ModelRenderer(this);
//        MumuTuskRR2.setPos(10.9F, 17.4F, -23.3F);
//        MumuNeck.addChild(MumuTuskRR2);
//        setRotationAngle(MumuTuskRR2, 0.925F, -0.6109F, 0.0F);
//        MumuTuskRR2.texOffs(0, 201).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, 0.0F, true);
//
//        MumuTuskFL1 = new ModelRenderer(this);
//        MumuTuskFL1.setPos(-7.0F, 5.0F, -21.1F);
//        MumuNeck.addChild(MumuTuskFL1);
//        setRotationAngle(MumuTuskFL1, 1.0472F, 0.2618F, 0.0F);
//        MumuTuskFL1.texOffs(0, 201).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
//
//        MumuTuskFL2 = new ModelRenderer(this);
//        MumuTuskFL2.setPos(-9.1F, 18.3F, -29.0F);
//        MumuNeck.addChild(MumuTuskFL2);
//        setRotationAngle(MumuTuskFL2, 0.925F, 0.2618F, 0.0F);
//        MumuTuskFL2.texOffs(0, 201).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
//
//        MumuTuskFL3 = new ModelRenderer(this);
//        MumuTuskFL3.setPos(-10.4F, 24.9F, -33.9F);
//        MumuNeck.addChild(MumuTuskFL3);
//        setRotationAngle(MumuTuskFL3, 0.925F, 0.2618F, 0.0F);
//        MumuTuskFL3.texOffs(0, 201).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
//
//        MumuTuskRL1 = new ModelRenderer(this);
//        MumuTuskRL1.setPos(-6.5F, 5.1F, -17.0F);
//        MumuNeck.addChild(MumuTuskRL1);
//        setRotationAngle(MumuTuskRL1, 1.0472F, 0.6109F, 0.0F);
//        MumuTuskRL1.texOffs(0, 201).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
//
//        MumuTuskRL2 = new ModelRenderer(this);
//        MumuTuskRL2.setPos(-10.9F, 17.4F, -23.3F);
//        MumuNeck.addChild(MumuTuskRL2);
//        setRotationAngle(MumuTuskRL2, 0.925F, 0.6109F, 0.0F);
//        MumuTuskRL2.texOffs(0, 201).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
//
//        MumuTrunk1 = new ModelRenderer(this);
//        MumuTrunk1.setPos(0.0F, 1.6F, -22.5F);
//        MumuNeck.addChild(MumuTrunk1);
//        setRotationAngle(MumuTrunk1, -0.1745F, 0.0F, 0.0F);
//        MumuTrunk1.texOffs(232, 0).addBox(-3.5F, 0.0F, -2.5F, 7.0F, 13.0F, 5.0F, 0.0F, false);
//
//        MumuTrunk2 = new ModelRenderer(this);
//        MumuTrunk2.setPos(0.0F, 12.5F, 0.0F);
//        MumuTrunk1.addChild(MumuTrunk2);
//        setRotationAngle(MumuTrunk2, -0.1745F, 0.0F, 0.0F);
//        MumuTrunk2.texOffs(236, 20).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 9.0F, 4.0F, 0.0F, false);
//
//        MumuTrunk3 = new ModelRenderer(this);
//        MumuTrunk3.setPos(0.0F, 8.5F, 0.0F);
//        MumuTrunk2.addChild(MumuTrunk3);
//        setRotationAngle(MumuTrunk3, -0.2618F, 0.0F, 0.0F);
//        MumuTrunk3.texOffs(240, 34).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 9.0F, 3.0F, 0.0F, false);
//
//        /* Parts for the child version */
//        this.childSkull = new ModelRenderer(this, 100, 120);
//        this.childSkull.setPos(-3.0F, -3.0F, -13.5F);
//        this.childSkull.addBox(-5.5F, -5.5F, -10.5F, 17, 17, 15, 0.0F);
//        this.childNeck = new ModelRenderer(this, 176, 0);
//        this.childNeck.addBox(-5.5F, -5.5F, -10.5F, 11, 11, 11, 0.0F);
//        this.childEarR = new ModelRenderer(this, 220, 12);
//        this.childEarR.addBox(-3.5F, -6.1F, -0.5F, 11, 11, 1, 0.0F);
//        this.childEarR.setPos(-8.5F, -1.8F, -15.0F);
//        this.childEarL = new ModelRenderer(this, 220, 0);
//        this.childEarL.addBox(-7.5F, -6.0F, -0.5F, 11, 11, 1, 0.0F);
//        this.childEarL.setPos(8.6F, -1.8F, -15.0F);
//        this.childEarL.mirror = true;
//        this.childTrunk1 = new ModelRenderer(this, 0, 43);
//        this.childTrunk1.addBox(-3.5F, 0.0F, -2.5F, 7, 13, 5, 0.0F);
//        this.childTrunk1.setPos(0.0F, 1.6F, -22.5F);
//        this.childNeck.addChild(this.childSkull);
//        this.childNeck.addChild(this.childEarR);
//        this.childNeck.addChild(this.childEarL);
//        this.childNeck.addChild(this.childTrunk1);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> headParts() {
//        return ImmutableList.of(this.MumuNeck);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> bodyParts() {
//        return ImmutableList.of(this.MumuBody, this.MumuLegFL, this.MumuLegFR, this.MumuLegRL, this.MumuLegRR);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//
//    @Override
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        MumuNeck.xRot = (headPitch);
//        MumuNeck.yRot = degToRad(netHeadYaw);
//
//        childNeck.xRot = degToRad(headPitch);
//        childNeck.yRot = degToRad(netHeadYaw);
//
//        /* swingSuppress goes to 0 when still so gates the movement */
//        MumuLegRL.xRot = MathHelper.cos(ageInTicks * 0.6662F) * 1.4F * limbSwingAmount;
//        MumuLegRR.xRot = MathHelper.cos(ageInTicks * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        MumuLegFL.xRot = MathHelper.cos(ageInTicks * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        MumuLegFR.xRot = MathHelper.cos(ageInTicks * 0.6662F) * 1.4F * limbSwingAmount;
//        MumuTrunk1.xRot = MathHelper.cos(degToRad(entityIn.tickCount*7)) * degToRad(10);
//        MumuTrunk2.xRot = MumuTrunk1.xRot * 3;
//        MumuTrunk3.xRot = MumuTrunk2.xRot * 1;
//        tail_r1.xRot = MathHelper.cos(ageInTicks * 0.6662F) * 1.4F * limbSwingAmount;
//
//        childTrunk1.xRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(15);
//
//        /* flick ears */
//        MumuEarL.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(15);
//        MumuEarR.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(-15);
//
//        childEarL.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(15);
//        childEarR.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(-15);
//
//        /* raise trunk if in water */
//        if (entityIn.isInWater())
//        {
//            MumuTrunk1.xRot = degToRad(-150);
//            MumuTrunk2.xRot = degToRad(-20);
//            MumuTrunk3.xRot = degToRad(110);
//        }
//    }
//}