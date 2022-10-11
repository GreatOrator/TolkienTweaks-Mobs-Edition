package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.DeepClawEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/** DeepClaw - GreatOrator */
public class DeepClawModel<T extends DeepClawEntity> extends QuadrupedModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer body;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;
    private final ModelRenderer cube_r15;
    private final ModelRenderer cube_r16;
    private final ModelRenderer cube_r17;
    private final ModelRenderer leg0;
    private final ModelRenderer cube_r18;
    private final ModelRenderer cube_r19;
    private final ModelRenderer cube_r20;
    private final ModelRenderer cube_r21;
    private final ModelRenderer cube_r22;
    private final ModelRenderer cube_r23;
    private final ModelRenderer leg1;
    private final ModelRenderer cube_r24;
    private final ModelRenderer cube_r25;
    private final ModelRenderer cube_r26;
    private final ModelRenderer cube_r27;
    private final ModelRenderer cube_r28;
    private final ModelRenderer cube_r29;
    private final ModelRenderer leg2;
    private final ModelRenderer cube_r30;
    private final ModelRenderer cube_r31;
    private final ModelRenderer cube_r32;
    private final ModelRenderer cube_r33;
    private final ModelRenderer cube_r34;
    private final ModelRenderer cube_r35;
    private final ModelRenderer cube_r36;
    private final ModelRenderer leg3;
    private final ModelRenderer cube_r37;
    private final ModelRenderer cube_r38;
    private final ModelRenderer cube_r39;
    private final ModelRenderer cube_r40;
    private final ModelRenderer cube_r41;
    private final ModelRenderer cube_r42;
    private final ModelRenderer cube_r43;

    public DeepClawModel(){
        super(12, 0.0F, true, 16.0F, 4.0F, 2.25F, 2.0F, 24);
        texWidth = 64;
        texHeight = 64;

        head = new ModelRenderer(this);
        head.setPos(-0.0549F, 18.554F, -7.4816F);
        setRotationAngle(head, 0.0873F, 0.0F, 0.0F);
        head.texOffs(24, 50).addBox(-1.4451F, -1.3781F, 0.9476F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(-0.0436F, -0.2746F, -3.9398F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.0436F, 0.1745F, 0.0F);
        cube_r1.texOffs(56, 43).addBox(-0.4525F, -0.6101F, 0.3387F, 1.0F, 1.0F, 3.0F, 0.0F, true);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(-0.0436F, -0.2746F, -3.9398F);
        head.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.0436F, -0.1745F, 0.0F);
        cube_r2.texOffs(56, 43).addBox(-0.3535F, -0.6086F, 0.3046F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(-0.4451F, 1.3517F, -0.4406F);
        head.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.0436F, 0.0873F, 0.0F);
        cube_r3.texOffs(16, 31).addBox(0.4308F, -3.3643F, -1.3147F, 2.0F, 2.0F, 2.0F, -0.1F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(0.5549F, 1.3517F, -0.4406F);
        head.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.0436F, -0.0873F, 0.0F);
        cube_r4.texOffs(16, 31).addBox(-2.4308F, -3.3643F, -1.3147F, 2.0F, 2.0F, 2.0F, -0.1F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(0.0564F, -1.7805F, -4.4607F);
        head.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.1309F, 0.0F, 0.0F);
        cube_r5.texOffs(59, 40).addBox(-0.5015F, 0.4121F, 0.6104F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setPos(0.0564F, -1.7805F, -4.4607F);
        head.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0436F, 0.1745F, 0.0F);
        cube_r6.texOffs(56, 47).addBox(-0.5082F, 0.1723F, 0.6305F, 1.0F, 1.0F, 3.0F, 0.0F, true);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setPos(0.0564F, -1.7805F, -4.4607F);
        head.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0436F, -0.1745F, 0.0F);
        cube_r7.texOffs(56, 47).addBox(-0.4978F, 0.1708F, 0.5963F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setPos(0.5549F, 1.3517F, -1.1406F);
        head.addChild(cube_r8);
        setRotationAngle(cube_r8, -0.0436F, 0.0F, 0.0F);
        cube_r8.texOffs(0, 29).addBox(-2.5F, -3.3648F, 0.097F, 4.0F, 3.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0383F, 18.5285F, -2.1905F);
        setRotationAngle(body, -0.0873F, 0.0F, 0.0F);
        body.texOffs(0, 40).addBox(-3.0383F, -2.4987F, -1.7213F, 6.0F, 5.0F, 6.0F, -0.2F, false);
        body.texOffs(0, 51).addBox(-0.8383F, -2.0987F, -2.7213F, 2.0F, 3.0F, 3.0F, 0.0F, false);
        body.texOffs(0, 40).addBox(-3.0383F, -2.2987F, 2.2787F, 6.0F, 5.0F, 6.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setPos(1.4233F, -2.5F, -3.5F);
        body.addChild(cube_r9);
        setRotationAngle(cube_r9, -0.7854F, 0.7854F, -0.7854F);
        cube_r9.texOffs(48, 62).addBox(-5.5486F, -5.4747F, 4.0045F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r9.texOffs(48, 62).addBox(-9.5486F, -7.1747F, 5.0045F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r9.texOffs(48, 62).addBox(-7.5486F, -6.2747F, 3.0045F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r9.texOffs(48, 62).addBox(-9.5486F, -6.9747F, 3.0045F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r9.texOffs(44, 62).addBox(-4.5486F, -3.4747F, 1.0045F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r9.texOffs(44, 62).addBox(-2.5486F, -1.4747F, 1.0045F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setPos(-1.5F, -2.5F, -3.5F);
        body.addChild(cube_r10);
        setRotationAngle(cube_r10, -0.7854F, -0.7854F, 0.7854F);
        cube_r10.texOffs(44, 62).addBox(1.5486F, -1.6747F, 1.0045F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setPos(11.6063F, 0.4626F, -6.8095F);
        body.addChild(cube_r11);
        setRotationAngle(cube_r11, -0.1745F, 0.0F, 0.0F);
        cube_r11.texOffs(0, 40).addBox(-14.6446F, -5.7935F, 10.9944F, 6.0F, 5.0F, 6.0F, -0.2F, false);

        cube_r12 = new ModelRenderer(this);
        cube_r12.setPos(11.6063F, 0.4626F, -6.8095F);
        body.addChild(cube_r12);
        setRotationAngle(cube_r12, -0.1309F, 0.0F, 0.0F);
        cube_r12.texOffs(0, 40).addBox(-14.6446F, -5.6621F, 10.0265F, 6.0F, 5.0F, 6.0F, -0.1F, false);

        cube_r13 = new ModelRenderer(this);
        cube_r13.setPos(0.0617F, 0.2715F, 2.1905F);
        body.addChild(cube_r13);
        setRotationAngle(cube_r13, -0.0436F, 0.0F, 0.0F);
        cube_r13.texOffs(0, 40).addBox(-3.1F, -3.7046F, 0.0735F, 6.0F, 5.0F, 6.0F, 0.0F, false);

        cube_r14 = new ModelRenderer(this);
        cube_r14.setPos(0.0617F, 0.2715F, -5.5095F);
        body.addChild(cube_r14);
        setRotationAngle(cube_r14, 0.0873F, 0.0F, 0.0F);
        cube_r14.texOffs(0, 40).addBox(-3.1F, -2.5F, 5.8F, 6.0F, 5.0F, 6.0F, -0.1F, false);

        cube_r15 = new ModelRenderer(this);
        cube_r15.setPos(-6.1383F, 2.6715F, -6.8095F);
        body.addChild(cube_r15);
        setRotationAngle(cube_r15, 0.0F, 0.2182F, 0.0F);
        cube_r15.texOffs(0, 51).addBox(5.2316F, -4.7702F, 5.615F, 2.0F, 3.0F, 3.0F, -0.1F, false);

        cube_r16 = new ModelRenderer(this);
        cube_r16.setPos(6.0617F, 2.6715F, -8.0095F);
        body.addChild(cube_r16);
        setRotationAngle(cube_r16, 0.3927F, 0.0F, 0.0F);
        cube_r16.texOffs(36, 49).addBox(-8.1F, -0.1678F, 5.7565F, 4.0F, 2.0F, 3.0F, 0.0F, false);

        cube_r17 = new ModelRenderer(this);
        cube_r17.setPos(6.0617F, 2.6715F, -6.8095F);
        body.addChild(cube_r17);
        setRotationAngle(cube_r17, 0.0F, -0.2182F, 0.0F);
        cube_r17.texOffs(0, 51).addBox(-7.2316F, -4.7702F, 5.515F, 2.0F, 3.0F, 3.0F, -0.1F, false);

        leg0 = new ModelRenderer(this);
        leg0.setPos(-2.0F, 18.0F, -6.0F);
        leg0.texOffs(52, 60).addBox(-2.5F, 5.0F, 2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        leg0.texOffs(16, 62).addBox(-2.5F, 5.0F, 4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leg0.texOffs(60, 61).addBox(-1.9F, 5.0F, 0.3F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        cube_r18 = new ModelRenderer(this);
        cube_r18.setPos(5.0F, 0.0F, 4.5F);
        leg0.addChild(cube_r18);
        setRotationAngle(cube_r18, 0.0F, -0.2618F, 0.0F);
        cube_r18.texOffs(60, 61).addBox(-6.4F, 5.0F, -2.2F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r18.texOffs(52, 60).addBox(-6.9F, 5.0F, -0.4F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r19 = new ModelRenderer(this);
        cube_r19.setPos(-1.0F, 0.0F, 4.5F);
        leg0.addChild(cube_r19);
        setRotationAngle(cube_r19, 0.0F, 0.2618F, 0.0F);
        cube_r19.texOffs(60, 61).addBox(-1.3F, 5.0F, -4.2F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r19.texOffs(52, 60).addBox(-1.8F, 5.0F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r20 = new ModelRenderer(this);
        cube_r20.setPos(-1.166F, 0.8442F, 4.2F);
        leg0.addChild(cube_r20);
        setRotationAngle(cube_r20, -0.1745F, 0.0F, 0.0873F);
        cube_r20.texOffs(0, 60).addBox(-1.0F, 2.6F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r21 = new ModelRenderer(this);
        cube_r21.setPos(-1.166F, 0.8442F, 3.4F);
        leg0.addChild(cube_r21);
        setRotationAngle(cube_r21, 0.7854F, 0.0F, 0.0873F);
        cube_r21.texOffs(36, 61).addBox(-1.0F, 1.5F, -1.6F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        cube_r22 = new ModelRenderer(this);
        cube_r22.setPos(-1.166F, 0.8442F, 3.4F);
        leg0.addChild(cube_r22);
        setRotationAngle(cube_r22, 0.5672F, 0.0F, 0.0873F);
        cube_r22.texOffs(30, 59).addBox(-1.0F, 0.7F, -1.6F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        cube_r23 = new ModelRenderer(this);
        cube_r23.setPos(-1.166F, 0.8442F, 3.4F);
        leg0.addChild(cube_r23);
        setRotationAngle(cube_r23, 0.0F, 0.0F, 0.0873F);
        cube_r23.texOffs(20, 58).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(3.0F, 18.0F, -6.0F);
        leg1.texOffs(52, 60).addBox(0.5F, 5.0F, 2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        leg1.texOffs(16, 62).addBox(0.5F, 5.0F, 4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leg1.texOffs(60, 61).addBox(1.0F, 5.0F, 0.4F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        cube_r24 = new ModelRenderer(this);
        cube_r24.setPos(-6.0F, 0.0F, 4.5F);
        leg1.addChild(cube_r24);
        setRotationAngle(cube_r24, 0.0F, 0.2618F, 0.0F);
        cube_r24.texOffs(60, 61).addBox(6.5F, 5.0F, -2.1F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r24.texOffs(52, 60).addBox(5.9F, 5.0F, -0.4F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r25 = new ModelRenderer(this);
        cube_r25.setPos(0.0F, 0.0F, 4.5F);
        leg1.addChild(cube_r25);
        setRotationAngle(cube_r25, 0.0F, -0.2618F, 0.0F);
        cube_r25.texOffs(60, 61).addBox(1.3F, 5.0F, -4.2F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r25.texOffs(52, 60).addBox(0.8F, 5.0F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r26 = new ModelRenderer(this);
        cube_r26.setPos(0.166F, 0.8442F, 4.2F);
        leg1.addChild(cube_r26);
        setRotationAngle(cube_r26, -0.1745F, 0.0F, -0.0873F);
        cube_r26.texOffs(0, 60).addBox(0.0F, 2.6F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r27 = new ModelRenderer(this);
        cube_r27.setPos(0.166F, 0.8442F, 3.4F);
        leg1.addChild(cube_r27);
        setRotationAngle(cube_r27, 0.7854F, 0.0F, -0.0873F);
        cube_r27.texOffs(36, 61).addBox(0.0F, 1.5F, -1.6F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        cube_r28 = new ModelRenderer(this);
        cube_r28.setPos(0.166F, 0.8442F, 3.4F);
        leg1.addChild(cube_r28);
        setRotationAngle(cube_r28, 0.5672F, 0.0F, -0.0873F);
        cube_r28.texOffs(30, 59).addBox(0.0F, 0.7F, -1.6F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        cube_r29 = new ModelRenderer(this);
        cube_r29.setPos(0.166F, 0.8442F, 3.4F);
        leg1.addChild(cube_r29);
        setRotationAngle(cube_r29, 0.0F, 0.0F, -0.0873F);
        cube_r29.texOffs(20, 58).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(1.8787F, 18.8997F, 4.1941F);
        leg2.texOffs(16, 62).addBox(-5.9787F, 4.1003F, 2.0059F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r30 = new ModelRenderer(this);
        cube_r30.setPos(-4.6787F, -0.8997F, -5.6941F);
        leg2.addChild(cube_r30);
        setRotationAngle(cube_r30, 0.0F, 0.1745F, 0.0F);
        cube_r30.texOffs(60, 61).addBox(4.9F, 5.0F, 4.9F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r30.texOffs(52, 60).addBox(4.3F, 5.0F, 6.6F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r31 = new ModelRenderer(this);
        cube_r31.setPos(1.3213F, -0.8997F, -5.6941F);
        leg2.addChild(cube_r31);
        setRotationAngle(cube_r31, 0.0F, -0.1745F, 0.0F);
        cube_r31.texOffs(60, 61).addBox(1.9F, 5.0F, 3.7F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r31.texOffs(52, 60).addBox(1.4F, 5.0F, 5.4F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r32 = new ModelRenderer(this);
        cube_r32.setPos(1.3213F, -0.8997F, -5.6941F);
        leg2.addChild(cube_r32);
        setRotationAngle(cube_r32, 0.0F, -0.3491F, 0.0F);
        cube_r32.texOffs(60, 61).addBox(3.5F, 5.0F, 3.2F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r32.texOffs(52, 60).addBox(3.0F, 5.0F, 4.9F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r33 = new ModelRenderer(this);
        cube_r33.setPos(-4.6787F, -0.8997F, -5.6941F);
        leg2.addChild(cube_r33);
        setRotationAngle(cube_r33, 0.0F, 0.3491F, 0.0F);
        cube_r33.texOffs(52, 60).addBox(2.2F, 5.0F, 7.2F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        cube_r33.texOffs(60, 61).addBox(2.7F, 5.0F, 5.7F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        cube_r34 = new ModelRenderer(this);
        cube_r34.setPos(-1.8787F, 4.1003F, -1.0941F);
        leg2.addChild(cube_r34);
        setRotationAngle(cube_r34, -0.2182F, 0.0F, -0.0873F);
        cube_r34.texOffs(0, 60).addBox(3.0F, -1.6F, 1.9F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r35 = new ModelRenderer(this);
        cube_r35.setPos(-1.8787F, 4.1003F, -1.0941F);
        leg2.addChild(cube_r35);
        setRotationAngle(cube_r35, -0.5672F, 0.0F, -0.0873F);
        cube_r35.texOffs(0, 60).addBox(3.0F, -4.0F, 1.3F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r36 = new ModelRenderer(this);
        cube_r36.setPos(-1.8787F, 4.1003F, -1.0941F);
        leg2.addChild(cube_r36);
        setRotationAngle(cube_r36, -0.1745F, 0.0F, -0.0873F);
        cube_r36.texOffs(6, 57).addBox(2.0F, -6.2F, 1.6F, 2.0F, 4.0F, 3.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setPos(-1.8954F, 18.8997F, 2.1941F);
        leg3.texOffs(16, 62).addBox(4.7954F, 4.1003F, 4.0059F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r37 = new ModelRenderer(this);
        cube_r37.setPos(4.6954F, -0.8997F, -3.6941F);
        leg3.addChild(cube_r37);
        setRotationAngle(cube_r37, 0.0F, -0.1745F, 0.0F);
        cube_r37.texOffs(60, 61).addBox(-4.8F, 5.0F, 4.9F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r37.texOffs(52, 60).addBox(-5.3F, 5.0F, 6.6F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r38 = new ModelRenderer(this);
        cube_r38.setPos(-1.3046F, -0.8997F, -3.6941F);
        leg3.addChild(cube_r38);
        setRotationAngle(cube_r38, 0.0F, 0.1745F, 0.0F);
        cube_r38.texOffs(60, 61).addBox(-1.9F, 5.0F, 3.7F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r38.texOffs(52, 60).addBox(-2.4F, 5.0F, 5.4F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r39 = new ModelRenderer(this);
        cube_r39.setPos(-1.3046F, -0.8997F, -3.6941F);
        leg3.addChild(cube_r39);
        setRotationAngle(cube_r39, 0.0F, 0.3491F, 0.0F);
        cube_r39.texOffs(60, 61).addBox(-3.5F, 5.0F, 3.2F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r39.texOffs(52, 60).addBox(-4.0F, 5.0F, 4.9F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        cube_r40 = new ModelRenderer(this);
        cube_r40.setPos(4.6954F, -0.8997F, -3.6941F);
        leg3.addChild(cube_r40);
        setRotationAngle(cube_r40, 0.0F, -0.3491F, 0.0F);
        cube_r40.texOffs(52, 60).addBox(-3.2F, 5.0F, 7.2F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        cube_r40.texOffs(60, 61).addBox(-2.7F, 5.0F, 5.4F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        cube_r41 = new ModelRenderer(this);
        cube_r41.setPos(1.8954F, 4.1003F, 0.9059F);
        leg3.addChild(cube_r41);
        setRotationAngle(cube_r41, -0.2182F, 0.0F, 0.0873F);
        cube_r41.texOffs(0, 60).addBox(-4.0F, -1.6F, 1.9F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r42 = new ModelRenderer(this);
        cube_r42.setPos(1.8954F, 4.1003F, 0.9059F);
        leg3.addChild(cube_r42);
        setRotationAngle(cube_r42, -0.5672F, 0.0F, 0.0873F);
        cube_r42.texOffs(0, 60).addBox(-4.0F, -4.0F, 1.3F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r43 = new ModelRenderer(this);
        cube_r43.setPos(1.8954F, 4.1003F, 0.9059F);
        leg3.addChild(cube_r43);
        setRotationAngle(cube_r43, -0.1745F, 0.0F, 0.0873F);
        cube_r43.texOffs(6, 57).addBox(-4.0F, -6.2F, 1.6F, 2.0F, 4.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.head.xRot = p_225597_6_ * 0.017453292F;
        this.head.yRot = p_225597_5_ * 0.017453292F;
        this.leg0.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.leg1.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + 3.1415927F) * 1.4F * p_225597_3_;
        this.leg2.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + 3.1415927F) * 1.4F * p_225597_3_;
        this.leg3.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
