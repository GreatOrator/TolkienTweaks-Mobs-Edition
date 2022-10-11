package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Huron - GreatOrator
 */
public class HuronModel<T extends MonsterEntity> extends SegmentedModel<T> {
    private final ModelRenderer root;
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer head;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftArm;

    public HuronModel() {
        this(0.0F);
    }

    public HuronModel(float sizeIn) {
        texWidth = 128;
        texHeight = 64;

        root = new ModelRenderer(this);
        root.setPos(0.0F, 0.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        root.addChild(body);
        body.texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 18.0F, 4.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setPos(-3.0F, 18.0F, -4.0F);
        root.addChild(leg0);
        leg0.texOffs(40, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(3.0F, 18.0F, -4.0F);
        root.addChild(leg1);
        leg1.texOffs(56, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(-3.0F, 18.0F, 4.0F);
        root.addChild(leg2);
        leg2.texOffs(24, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setPos(3.0F, 18.0F, 4.0F);
        root.addChild(leg3);
        leg3.texOffs(104, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.texOffs(42, 16).addBox(-7.0F, -9.5F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        head.texOffs(84, 16).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);
        head.texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.texOffs(22, 0).addBox(-0.3F, -3.2F, -4.9F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setPos(-4.0F, -0.1F, -2.0F);
        root.addChild(rightArm);
        rightArm.texOffs(40, 10).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setPos(8.0F, -0.1F, -2.0F);
        root.addChild(leftArm);
        leftArm.texOffs(24, 10).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.leg2, this.leg3, this.rightArm, this.leftArm);
    }

    @Override
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.head.yRot = p_225597_5_ * ((float)Math.PI / 180F);
        this.head.xRot = p_225597_6_ * ((float)Math.PI / 180F);
        this.leg0.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.leg1.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_;
        this.leg2.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_;
        this.leg3.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;

        this.rightArm.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.leftArm.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}