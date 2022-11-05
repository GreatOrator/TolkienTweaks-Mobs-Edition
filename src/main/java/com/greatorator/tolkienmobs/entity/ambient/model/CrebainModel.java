package com.greatorator.tolkienmobs.entity.ambient.model;

/* Crebain - GreatOrator */
//
//public class CrebainModel extends SegmentedModel<CrebainEntity> {
//    private final ModelRenderer body;
//    private final ModelRenderer tail;
//    private final ModelRenderer wingLeft;
//    private final ModelRenderer wingRight;
//    private final ModelRenderer head;
//    private final ModelRenderer head2;
//    private final ModelRenderer beak1;
//    private final ModelRenderer beak2;
//    private final ModelRenderer feather;
//    private final ModelRenderer legLeft;
//    private final ModelRenderer legRight;
//
//    public CrebainModel() {
//        this.texWidth = 32;
//        this.texHeight = 32;
//        this.body = new ModelRenderer(this, 2, 8);
//        this.body.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F);
//        this.body.setPos(0.0F, 16.5F, -3.0F);
//        this.tail = new ModelRenderer(this, 22, 1);
//        this.tail.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 1.0F);
//        this.tail.setPos(0.0F, 21.07F, 1.16F);
//        this.wingLeft = new ModelRenderer(this, 19, 8);
//        this.wingLeft.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F);
//        this.wingLeft.setPos(1.5F, 16.94F, -2.76F);
//        this.wingRight = new ModelRenderer(this, 19, 8);
//        this.wingRight.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F);
//        this.wingRight.setPos(-1.5F, 16.94F, -2.76F);
//        this.head = new ModelRenderer(this, 2, 2);
//        this.head.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F);
//        this.head.setPos(0.0F, 15.69F, -2.76F);
//        this.head2 = new ModelRenderer(this, 10, 0);
//        this.head2.addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F);
//        this.head2.setPos(0.0F, -2.0F, -1.0F);
//        this.head.addChild(this.head2);
//        this.beak1 = new ModelRenderer(this, 11, 7);
//        this.beak1.addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F);
//        this.beak1.setPos(0.0F, -0.5F, -1.5F);
//        this.head.addChild(this.beak1);
//        this.beak2 = new ModelRenderer(this, 16, 7);
//        this.beak2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
//        this.beak2.setPos(0.0F, -1.75F, -2.45F);
//        this.head.addChild(this.beak2);
//        this.feather = new ModelRenderer(this, 2, 18);
//        this.feather.addBox(0.0F, -4.0F, -2.0F, 0.0F, 5.0F, 4.0F);
//        this.feather.setPos(0.0F, -2.15F, 0.15F);
//        this.head.addChild(this.feather);
//        this.legLeft = new ModelRenderer(this, 14, 18);
//        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
//        this.legLeft.setPos(1.0F, 22.0F, -1.05F);
//        this.legRight = new ModelRenderer(this, 14, 18);
//        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
//        this.legRight.setPos(-1.0F, 22.0F, -1.05F);
//    }
//
//    @Override
//    public Iterable<ModelRenderer> parts() {
//        return ImmutableList.of(this.body, this.wingLeft, this.wingRight, this.tail, this.head, this.legLeft, this.legRight);
//    }
//
//    @Override
//    public void setupAnim(CrebainEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
//        this.setupAnim(getState(p_225597_1_), p_225597_1_.tickCount, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
//    }
//
//    @Override
//    public void prepareMobModel(CrebainEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
//        this.prepare(getState(p_212843_1_));
//    }
//
//    public void renderOnShoulder(MatrixStack p_228284_1_, IVertexBuilder p_228284_2_, int p_228284_3_, int p_228284_4_, float p_228284_5_, float p_228284_6_, float p_228284_7_, float p_228284_8_, int p_228284_9_) {
//        this.prepare(CrebainModel.State.ON_SHOULDER);
//        this.setupAnim(CrebainModel.State.ON_SHOULDER, p_228284_9_, p_228284_5_, p_228284_6_, 0.0F, p_228284_7_, p_228284_8_);
//        this.parts().forEach((p_228285_4_) -> {
//            p_228285_4_.render(p_228284_1_, p_228284_2_, p_228284_3_, p_228284_4_);
//        });
//    }
//
//    private void setupAnim(CrebainModel.State p_217162_1_, int p_217162_2_, float p_217162_3_, float p_217162_4_, float p_217162_5_, float p_217162_6_, float p_217162_7_) {
//        this.head.xRot = p_217162_7_ * 0.017453292F;
//        this.head.yRot = p_217162_6_ * 0.017453292F;
//        this.head.zRot = 0.0F;
//        this.head.x = 0.0F;
//        this.body.x = 0.0F;
//        this.tail.x = 0.0F;
//        this.wingRight.x = -1.5F;
//        this.wingLeft.x = 1.5F;
//        switch(p_217162_1_) {
//            case SITTING:
//                break;
//            case PARTY:
//                float lvt_8_1_ = MathHelper.cos((float)p_217162_2_);
//                float lvt_9_1_ = MathHelper.sin((float)p_217162_2_);
//                this.head.x = lvt_8_1_;
//                this.head.y = 15.69F + lvt_9_1_;
//                this.head.xRot = 0.0F;
//                this.head.yRot = 0.0F;
//                this.head.zRot = MathHelper.sin((float)p_217162_2_) * 0.4F;
//                this.body.x = lvt_8_1_;
//                this.body.y = 16.5F + lvt_9_1_;
//                this.wingLeft.zRot = -0.0873F - p_217162_5_;
//                this.wingLeft.x = 1.5F + lvt_8_1_;
//                this.wingLeft.y = 16.94F + lvt_9_1_;
//                this.wingRight.zRot = 0.0873F + p_217162_5_;
//                this.wingRight.x = -1.5F + lvt_8_1_;
//                this.wingRight.y = 16.94F + lvt_9_1_;
//                this.tail.x = lvt_8_1_;
//                this.tail.y = 21.07F + lvt_9_1_;
//                break;
//            case STANDING:
//                ModelRenderer var10000 = this.legLeft;
//                var10000.xRot += MathHelper.cos(p_217162_3_ * 0.6662F) * 1.4F * p_217162_4_;
//                var10000 = this.legRight;
//                var10000.xRot += MathHelper.cos(p_217162_3_ * 0.6662F + 3.1415927F) * 1.4F * p_217162_4_;
//            case FLYING:
//            case ON_SHOULDER:
//            default:
//                float lvt_10_1_ = p_217162_5_ * 0.3F;
//                this.head.y = 15.69F + lvt_10_1_;
//                this.tail.xRot = 1.015F + MathHelper.cos(p_217162_3_ * 0.6662F) * 0.3F * p_217162_4_;
//                this.tail.y = 21.07F + lvt_10_1_;
//                this.body.y = 16.5F + lvt_10_1_;
//                this.wingLeft.zRot = -0.0873F - p_217162_5_;
//                this.wingLeft.y = 16.94F + lvt_10_1_;
//                this.wingRight.zRot = 0.0873F + p_217162_5_;
//                this.wingRight.y = 16.94F + lvt_10_1_;
//                this.legLeft.y = 22.0F + lvt_10_1_;
//                this.legRight.y = 22.0F + lvt_10_1_;
//        }
//
//    }
//
//    private void prepare(CrebainModel.State p_217160_1_) {
//        this.feather.xRot = -0.2214F;
//        this.body.xRot = 0.4937F;
//        this.wingLeft.xRot = -0.6981F;
//        this.wingLeft.yRot = -3.1415927F;
//        this.wingRight.xRot = -0.6981F;
//        this.wingRight.yRot = -3.1415927F;
//        this.legLeft.xRot = -0.0299F;
//        this.legRight.xRot = -0.0299F;
//        this.legLeft.y = 22.0F;
//        this.legRight.y = 22.0F;
//        this.legLeft.zRot = 0.0F;
//        this.legRight.zRot = 0.0F;
//        switch(p_217160_1_) {
//            case SITTING:
//                float lvt_2_1_ = 1.9F;
//                this.head.y = 17.59F;
//                this.tail.xRot = 1.5388988F;
//                this.tail.y = 22.97F;
//                this.body.y = 18.4F;
//                this.wingLeft.zRot = -0.0873F;
//                this.wingLeft.y = 18.84F;
//                this.wingRight.zRot = 0.0873F;
//                this.wingRight.y = 18.84F;
//                ++this.legLeft.y;
//                ++this.legRight.y;
//                ++this.legLeft.xRot;
//                ++this.legRight.xRot;
//                break;
//            case PARTY:
//                this.legLeft.zRot = -0.34906584F;
//                this.legRight.zRot = 0.34906584F;
//            case STANDING:
//            case ON_SHOULDER:
//            default:
//                break;
//            case FLYING:
//                ModelRenderer var10000 = this.legLeft;
//                var10000.xRot += 0.6981317F;
//                var10000 = this.legRight;
//                var10000.xRot += 0.6981317F;
//        }
//
//    }
//
//    private static CrebainModel.State getState(CrebainEntity p_217158_0_) {
//        if (p_217158_0_.isPartyParrot()) {
//            return CrebainModel.State.PARTY;
//        } else if (p_217158_0_.isInSittingPose()) {
//            return CrebainModel.State.SITTING;
//        } else {
//            return p_217158_0_.isFlying() ? CrebainModel.State.FLYING : CrebainModel.State.STANDING;
//        }
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public static enum State {
//        FLYING,
//        STANDING,
//        SITTING,
//        PARTY,
//        ON_SHOULDER;
//
//        private State() {
//        }
//    }
//}