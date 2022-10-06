package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.GwahirEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.utils.TTMRand.degToRad;

public class GwahirModel extends SegmentedModel<GwahirEntity> {
	private final ModelRenderer torso;
	private final ModelRenderer body_r1;
	private final ModelRenderer body_r2;
	private final ModelRenderer body_r3;
	private final ModelRenderer body_r4;
	private final ModelRenderer body_r5;
	private final ModelRenderer body_r6;
	private final ModelRenderer head;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cowl_r1;
	private final ModelRenderer beak_bottom_r1;
	private final ModelRenderer beak_top_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer neck_r1;
	private final ModelRenderer left_leg;
	private final ModelRenderer talon_r1;
	private final ModelRenderer talon_r2;
	private final ModelRenderer talon_r3;
	private final ModelRenderer talon_r4;
	private final ModelRenderer left_toe5_r1;
	private final ModelRenderer left_toe4_r1;
	private final ModelRenderer left_toe3_r1;
	private final ModelRenderer left_toe2_r1;
	private final ModelRenderer left_leg_r1;
	private final ModelRenderer left_leg_r2;
	private final ModelRenderer left_thigh2_r1;
	private final ModelRenderer right_leg;
	private final ModelRenderer talon_r5;
	private final ModelRenderer talon_r6;
	private final ModelRenderer talon_r7;
	private final ModelRenderer talon_r8;
	private final ModelRenderer right_toe4_r1;
	private final ModelRenderer right_toe3_r1;
	private final ModelRenderer right_toe2_r1;
	private final ModelRenderer right_toe1_r1;
	private final ModelRenderer right_leg_r1;
	private final ModelRenderer right_leg_r2;
	private final ModelRenderer right_thigh1_r1;
	private final ModelRenderer left_wing;
	private final ModelRenderer left_wing_inner;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer left_wing_mid;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer left_wing_outer;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer right_wing;
	private final ModelRenderer right_wing_inner;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer cube_r31;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer right_wing_mid;
	private final ModelRenderer cube_r34;
	private final ModelRenderer cube_r35;
	private final ModelRenderer cube_r36;
	private final ModelRenderer cube_r37;
	private final ModelRenderer cube_r38;
	private final ModelRenderer cube_r39;
	private final ModelRenderer cube_r40;
	private final ModelRenderer right_wing_outer;
	private final ModelRenderer cube_r41;
	private final ModelRenderer cube_r42;
	private final ModelRenderer cube_r43;
	private final ModelRenderer cube_r44;
	private final ModelRenderer cube_r45;
	private final ModelRenderer cube_r46;
	private final ModelRenderer cube_r47;
	private final ModelRenderer back;
	private final ModelRenderer tail;
	private final ModelRenderer left_tail_outer;
	private final ModelRenderer cube_r48;
	private final ModelRenderer cube_r49;
	private final ModelRenderer cube_r50;
	private final ModelRenderer cube_r51;
	private final ModelRenderer right_tail_outer;
	private final ModelRenderer cube_r52;
	private final ModelRenderer cube_r53;
	private final ModelRenderer cube_r54;
	private final ModelRenderer cube_r55;
	private final ModelRenderer left_tail_inner;
	private final ModelRenderer cube_r56;
	private final ModelRenderer cube_r57;
	private final ModelRenderer cube_r58;
	private final ModelRenderer cube_r59;
	private final ModelRenderer right_tail_inner;
	private final ModelRenderer cube_r60;
	private final ModelRenderer cube_r61;
	private final ModelRenderer cube_r62;
	private final ModelRenderer cube_r63;

	public GwahirModel() {
		texWidth = 64;
		texHeight = 64;

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, 16.0F, 2.0F);
		torso.texOffs(2, 31).addBox(-5.0F, -3.3F, 5.8F, 10.0F, 5.0F, 5.0F, 0.0F, false);

		body_r1 = new ModelRenderer(this);
		body_r1.setPos(-2.0F, -3.7F, -6.8F);
		torso.addChild(body_r1);
		setRotationAngle(body_r1, -0.5672F, 0.0F, 0.0F);
		body_r1.texOffs(36, 0).addBox(-2.0F, -1.0F, -1.0F, 8.0F, 2.0F, 6.0F, 0.0F, false);

		body_r2 = new ModelRenderer(this);
		body_r2.setPos(-1.0F, -3.8F, -7.0F);
		torso.addChild(body_r2);
		setRotationAngle(body_r2, -0.3491F, 0.0F, 0.0F);
		body_r2.texOffs(3, 16).addBox(-4.0F, -4.0F, -1.0F, 10.0F, 5.0F, 8.0F, 0.0F, false);

		body_r3 = new ModelRenderer(this);
		body_r3.setPos(3.7002F, -4.4417F, -3.0944F);
		torso.addChild(body_r3);
		setRotationAngle(body_r3, -0.3447F, 0.1726F, -0.0401F);
		body_r3.texOffs(12, 2).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		body_r4 = new ModelRenderer(this);
		body_r4.setPos(-3.6998F, -4.4417F, -3.0944F);
		torso.addChild(body_r4);
		setRotationAngle(body_r4, -0.3523F, -0.1704F, 0.0381F);
		body_r4.texOffs(12, 2).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		body_r5 = new ModelRenderer(this);
		body_r5.setPos(-1.0F, -0.2F, 5.3F);
		torso.addChild(body_r5);
		setRotationAngle(body_r5, -0.1745F, 0.0F, 0.0F);
		body_r5.texOffs(0, 29).addBox(-4.0F, -4.0F, -1.0F, 10.0F, 5.0F, 7.0F, 0.0F, false);

		body_r6 = new ModelRenderer(this);
		body_r6.setPos(-0.5F, -1.0F, -2.0F);
		torso.addChild(body_r6);
		setRotationAngle(body_r6, -0.2182F, 0.0F, 0.0F);
		body_r6.texOffs(0, 0).addBox(-5.0F, -5.0F, -1.0F, 11.0F, 6.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(-0.5F, -6.25F, -6.75F);
		torso.addChild(head);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.0436F, 0.0F, 0.0F);
		cube_r1.texOffs(48, 48).addBox(-2.0F, -4.4F, -6.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		cowl_r1 = new ModelRenderer(this);
		cowl_r1.setPos(0.5F, 6.25F, 6.75F);
		head.addChild(cowl_r1);
		setRotationAngle(cowl_r1, -0.1309F, 0.0F, 0.0F);
		cowl_r1.texOffs(0, 49).addBox(-2.5F, -7.5F, -10.5F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		beak_bottom_r1 = new ModelRenderer(this);
		beak_bottom_r1.setPos(0.0F, 0.9F, -9.45F);
		head.addChild(beak_bottom_r1);
		setRotationAngle(beak_bottom_r1, 0.0767F, -0.1278F, 0.8139F);
		beak_bottom_r1.texOffs(38, 34).addBox(-2.1719F, -1.8313F, -2.2612F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		beak_top_r1 = new ModelRenderer(this);
		beak_top_r1.setPos(-0.1F, -1.9F, -9.15F);
		head.addChild(beak_top_r1);
		setRotationAngle(beak_top_r1, 0.0804F, 0.1278F, -0.7569F);
		beak_top_r1.texOffs(38, 28).addBox(-1.6565F, -0.1528F, -3.2676F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-1.1F, 1.25F, -7.95F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.0504F, -0.523F, 0.0252F);
		cube_r2.texOffs(56, 28).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(1.1F, 1.25F, -7.95F);
		head.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.0504F, 0.523F, -0.0252F);
		cube_r3.texOffs(56, 28).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(1.5F, 1.25F, -8.35F);
		head.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.0436F, 0.0F, 0.0F);
		cube_r4.texOffs(54, 34).addBox(-3.0F, -4.0F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(1.5F, 1.25F, -6.65F);
		head.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.0436F, 0.0F, 0.0F);
		cube_r5.texOffs(42, 18).addBox(-4.0F, -4.0F, -1.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);

		neck_r1 = new ModelRenderer(this);
		neck_r1.setPos(0.5F, 1.25F, -2.25F);
		head.addChild(neck_r1);
		setRotationAngle(neck_r1, -0.1309F, 0.0F, 0.0F);
		neck_r1.texOffs(47, 10).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, 0.0F, 2.0F);
		torso.addChild(left_leg);
		left_leg.texOffs(0, 60).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		talon_r1 = new ModelRenderer(this);
		talon_r1.setPos(2.6F, 8.0F, -3.3F);
		left_leg.addChild(talon_r1);
		setRotationAngle(talon_r1, 0.0F, 2.5744F, 0.0F);
		talon_r1.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		talon_r2 = new ModelRenderer(this);
		talon_r2.setPos(-0.7F, 8.0F, -4.4F);
		left_leg.addChild(talon_r2);
		setRotationAngle(talon_r2, 0.0F, -2.618F, 0.0F);
		talon_r2.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		talon_r3 = new ModelRenderer(this);
		talon_r3.setPos(1.0F, 8.0F, -4.3F);
		left_leg.addChild(talon_r3);
		setRotationAngle(talon_r3, 0.0F, 3.1416F, 0.0F);
		talon_r3.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		talon_r4 = new ModelRenderer(this);
		talon_r4.setPos(-1.0F, 8.1F, 1.2F);
		left_leg.addChild(talon_r4);
		setRotationAngle(talon_r4, -0.1309F, 0.0F, 0.0F);
		talon_r4.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		left_toe5_r1 = new ModelRenderer(this);
		left_toe5_r1.setPos(-0.014F, 7.1984F, -0.381F);
		left_leg.addChild(left_toe5_r1);
		setRotationAngle(left_toe5_r1, 1.8326F, 0.0F, 3.1416F);
		left_toe5_r1.texOffs(0, 60).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		left_toe4_r1 = new ModelRenderer(this);
		left_toe4_r1.setPos(0.886F, 7.2984F, -2.381F);
		left_leg.addChild(left_toe4_r1);
		setRotationAngle(left_toe4_r1, -1.4159F, -0.5618F, -0.083F);
		left_toe4_r1.texOffs(0, 60).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		left_toe3_r1 = new ModelRenderer(this);
		left_toe3_r1.setPos(-0.714F, 7.2984F, -2.381F);
		left_leg.addChild(left_toe3_r1);
		setRotationAngle(left_toe3_r1, -1.4159F, 0.5618F, 0.083F);
		left_toe3_r1.texOffs(0, 60).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		left_toe2_r1 = new ModelRenderer(this);
		left_toe2_r1.setPos(-0.5F, 7.95F, -4.0F);
		left_leg.addChild(left_toe2_r1);
		setRotationAngle(left_toe2_r1, -1.4399F, 0.0F, 0.0F);
		left_toe2_r1.texOffs(0, 60).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		left_leg_r1 = new ModelRenderer(this);
		left_leg_r1.setPos(0.0F, 5.7F, -1.8F);
		left_leg.addChild(left_leg_r1);
		setRotationAngle(left_leg_r1, -0.6545F, 0.0F, 0.0F);
		left_leg_r1.texOffs(8, 59).addBox(-1.0F, -3.75F, -1.25F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		left_leg_r2 = new ModelRenderer(this);
		left_leg_r2.setPos(0.0F, 7.0F, -1.8F);
		left_leg.addChild(left_leg_r2);
		setRotationAngle(left_leg_r2, -0.2531F, 0.0F, 0.0F);
		left_leg_r2.texOffs(16, 58).addBox(-1.0F, -4.75F, -1.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		left_thigh2_r1 = new ModelRenderer(this);
		left_thigh2_r1.setPos(0.0F, 3.0F, -0.9F);
		left_leg.addChild(left_thigh2_r1);
		setRotationAngle(left_thigh2_r1, -0.0873F, 0.0F, 0.0F);
		left_thigh2_r1.texOffs(24, 57).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, 0.0F, 2.0F);
		torso.addChild(right_leg);
		right_leg.texOffs(0, 60).addBox(-0.5F, 5.25F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		talon_r5 = new ModelRenderer(this);
		talon_r5.setPos(2.6F, 8.0F, -3.3F);
		right_leg.addChild(talon_r5);
		setRotationAngle(talon_r5, 0.0F, 2.5744F, 0.0F);
		talon_r5.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		talon_r6 = new ModelRenderer(this);
		talon_r6.setPos(-0.7F, 8.0F, -4.4F);
		right_leg.addChild(talon_r6);
		setRotationAngle(talon_r6, 0.0F, -2.618F, 0.0F);
		talon_r6.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		talon_r7 = new ModelRenderer(this);
		talon_r7.setPos(1.0F, 8.0F, -4.3F);
		right_leg.addChild(talon_r7);
		setRotationAngle(talon_r7, 0.0F, 3.1416F, 0.0F);
		talon_r7.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		talon_r8 = new ModelRenderer(this);
		talon_r8.setPos(-1.0F, 8.1F, 1.2F);
		right_leg.addChild(talon_r8);
		setRotationAngle(talon_r8, -0.1309F, 0.0F, 0.0F);
		talon_r8.texOffs(4, 61).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

		right_toe4_r1 = new ModelRenderer(this);
		right_toe4_r1.setPos(-0.014F, 7.1984F, -0.381F);
		right_leg.addChild(right_toe4_r1);
		setRotationAngle(right_toe4_r1, 1.8326F, 0.0F, 3.1416F);
		right_toe4_r1.texOffs(0, 60).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		right_toe3_r1 = new ModelRenderer(this);
		right_toe3_r1.setPos(0.886F, 7.2984F, -2.381F);
		right_leg.addChild(right_toe3_r1);
		setRotationAngle(right_toe3_r1, -1.4159F, -0.5618F, -0.083F);
		right_toe3_r1.texOffs(0, 60).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		right_toe2_r1 = new ModelRenderer(this);
		right_toe2_r1.setPos(-0.714F, 7.2984F, -2.381F);
		right_leg.addChild(right_toe2_r1);
		setRotationAngle(right_toe2_r1, -1.4159F, 0.5618F, 0.083F);
		right_toe2_r1.texOffs(0, 60).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		right_toe1_r1 = new ModelRenderer(this);
		right_toe1_r1.setPos(-0.5F, 7.95F, -4.0F);
		right_leg.addChild(right_toe1_r1);
		setRotationAngle(right_toe1_r1, -1.4399F, 0.0F, 0.0F);
		right_toe1_r1.texOffs(0, 60).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		right_leg_r1 = new ModelRenderer(this);
		right_leg_r1.setPos(0.0F, 5.7F, -1.8F);
		right_leg.addChild(right_leg_r1);
		setRotationAngle(right_leg_r1, -0.6545F, 0.0F, 0.0F);
		right_leg_r1.texOffs(8, 59).addBox(-1.0F, -3.75F, -1.25F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		right_leg_r2 = new ModelRenderer(this);
		right_leg_r2.setPos(0.0F, 7.0F, -1.8F);
		right_leg.addChild(right_leg_r2);
		setRotationAngle(right_leg_r2, -0.2531F, 0.0F, 0.0F);
		right_leg_r2.texOffs(16, 58).addBox(-1.0F, -4.75F, -1.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		right_thigh1_r1 = new ModelRenderer(this);
		right_thigh1_r1.setPos(0.0F, 3.0F, -0.9F);
		right_leg.addChild(right_thigh1_r1);
		setRotationAngle(right_thigh1_r1, -0.0873F, 0.0F, 0.0F);
		right_thigh1_r1.texOffs(24, 57).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		left_wing = new ModelRenderer(this);
		left_wing.setPos(5.0F, -6.0F, -5.75F);
		torso.addChild(left_wing);


		left_wing_inner = new ModelRenderer(this);
		left_wing_inner.setPos(-5.0F, 14.0F, 3.75F);
		left_wing.addChild(left_wing_inner);


		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(8.6F, -14.5F, 1.4F);
		left_wing_inner.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0922F, -0.3401F, -0.1184F);
		cube_r6.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(10.6F, -14.5F, -0.6F);
		left_wing_inner.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0922F, -0.3401F, -0.1184F);
		cube_r7.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(12.6F, -14.5F, -2.6F);
		left_wing_inner.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0922F, -0.3401F, -0.1184F);
		cube_r8.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(5.6F, -14.0F, 0.0F);
		left_wing_inner.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0898F, -0.2532F, -0.1102F);
		cube_r9.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(7.6F, -14.0F, -2.0F);
		left_wing_inner.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0898F, -0.2532F, -0.1102F);
		cube_r10.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(9.6F, -14.0F, -4.0F);
		left_wing_inner.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0898F, -0.2532F, -0.1102F);
		cube_r11.texOffs(16, 41).addBox(-5.0F, 0.0F, -0.8F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(4.0F, -13.0F, -4.0F);
		left_wing_inner.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.2851F, 1.2182F, -0.4605F);
		cube_r12.texOffs(44, 54).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);

		left_wing_mid = new ModelRenderer(this);
		left_wing_mid.setPos(0.0F, 0.0F, 0.0F);
		left_wing_inner.addChild(left_wing_mid);


		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(15.3F, -15.4F, 7.8F);
		left_wing_mid.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.1126F, -0.6874F, -0.1592F);
		cube_r13.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(17.3F, -15.4F, 5.8F);
		left_wing_mid.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.1126F, -0.6874F, -0.1592F);
		cube_r14.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(19.3F, -15.4F, 3.8F);
		left_wing_mid.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.1126F, -0.6874F, -0.1592F);
		cube_r15.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(12.3F, -14.8F, 4.8F);
		left_wing_mid.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.1126F, -0.6874F, -0.1592F);
		cube_r16.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(14.3F, -14.8F, 2.8F);
		left_wing_mid.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.1126F, -0.6874F, -0.1592F);
		cube_r17.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(16.3F, -14.8F, 0.8F);
		left_wing_mid.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.1126F, -0.6874F, -0.1592F);
		cube_r18.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(10.9F, -14.2F, -1.9F);
		left_wing_mid.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.1524F, 0.8765F, -0.3095F);
		cube_r19.texOffs(44, 54).addBox(-0.1591F, -0.9072F, 0.4022F, 1.0F, 1.0F, 9.0F, 0.0F, false);

		left_wing_outer = new ModelRenderer(this);
		left_wing_outer.setPos(0.0F, 0.0F, 0.0F);
		left_wing_mid.addChild(left_wing_outer);


		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(22.1F, -16.3F, 14.6F);
		left_wing_outer.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0831F, -0.7785F, -0.1123F);
		cube_r20.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(24.1F, -16.3F, 12.6F);
		left_wing_outer.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0831F, -0.7785F, -0.1123F);
		cube_r21.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(26.1F, -16.3F, 10.6F);
		left_wing_outer.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0831F, -0.7785F, -0.1123F);
		cube_r22.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(19.1F, -16.3F, 11.6F);
		left_wing_outer.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0831F, -0.7785F, -0.1123F);
		cube_r23.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(21.1F, -16.3F, 9.6F);
		left_wing_outer.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0831F, -0.7785F, -0.1123F);
		cube_r24.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(23.1F, -16.3F, 7.6F);
		left_wing_outer.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0831F, -0.7785F, -0.1123F);
		cube_r25.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(18.5F, -16.0F, 3.6F);
		left_wing_outer.addChild(cube_r26);
		setRotationAngle(cube_r26, -0.1328F, 0.747F, -0.2825F);
		cube_r26.texOffs(44, 54).addBox(-0.8815F, -0.1109F, -0.1922F, 1.0F, 1.0F, 9.0F, 0.0F, false);

		right_wing = new ModelRenderer(this);
		right_wing.setPos(-5.0F, -6.0F, -5.75F);
		torso.addChild(right_wing);


		right_wing_inner = new ModelRenderer(this);
		right_wing_inner.setPos(5.0F, 14.0F, 3.75F);
		right_wing.addChild(right_wing_inner);


		cube_r27 = new ModelRenderer(this);
		cube_r27.setPos(-8.6F, -14.5F, 1.4F);
		right_wing_inner.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0922F, 0.3401F, 0.1184F);
		cube_r27.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setPos(-10.6F, -14.5F, -0.6F);
		right_wing_inner.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0922F, 0.3401F, 0.1184F);
		cube_r28.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setPos(-5.6F, -14.0F, 0.0F);
		right_wing_inner.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0898F, 0.2532F, 0.1102F);
		cube_r29.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setPos(-7.6F, -14.0F, -2.0F);
		right_wing_inner.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.0898F, 0.2532F, 0.1102F);
		cube_r30.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setPos(-4.3F, -13.9F, -4.6F);
		right_wing_inner.addChild(cube_r31);
		setRotationAngle(cube_r31, -0.2851F, -1.2182F, 0.4605F);
		cube_r31.texOffs(44, 54).addBox(-0.206F, -0.236F, -1.2141F, 1.0F, 1.0F, 9.0F, 0.0F, true);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setPos(-9.6F, -14.0F, -4.0F);
		right_wing_inner.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.0898F, 0.2532F, 0.1102F);
		cube_r32.texOffs(16, 41).addBox(-1.0F, 0.0F, -0.7F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setPos(-12.6F, -14.5F, -2.6F);
		right_wing_inner.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.0922F, 0.3401F, 0.1184F);
		cube_r33.texOffs(16, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		right_wing_mid = new ModelRenderer(this);
		right_wing_mid.setPos(0.0F, 0.0F, 0.0F);
		right_wing_inner.addChild(right_wing_mid);


		cube_r34 = new ModelRenderer(this);
		cube_r34.setPos(-15.3F, -15.4F, 7.8F);
		right_wing_mid.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.1126F, 0.6874F, 0.1592F);
		cube_r34.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r35 = new ModelRenderer(this);
		cube_r35.setPos(-17.3F, -15.4F, 5.8F);
		right_wing_mid.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.1126F, 0.6874F, 0.1592F);
		cube_r35.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r36 = new ModelRenderer(this);
		cube_r36.setPos(-12.3F, -14.8F, 4.8F);
		right_wing_mid.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.1126F, 0.6874F, 0.1592F);
		cube_r36.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r37 = new ModelRenderer(this);
		cube_r37.setPos(-14.3F, -14.8F, 2.8F);
		right_wing_mid.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.1126F, 0.6874F, 0.1592F);
		cube_r37.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r38 = new ModelRenderer(this);
		cube_r38.setPos(-19.3F, -15.4F, 3.8F);
		right_wing_mid.addChild(cube_r38);
		setRotationAngle(cube_r38, 0.1126F, 0.6874F, 0.1592F);
		cube_r38.texOffs(16, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r39 = new ModelRenderer(this);
		cube_r39.setPos(-16.3F, -14.8F, 0.8F);
		right_wing_mid.addChild(cube_r39);
		setRotationAngle(cube_r39, 0.1126F, 0.6874F, 0.1592F);
		cube_r39.texOffs(16, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r40 = new ModelRenderer(this);
		cube_r40.setPos(-11.6F, -15.0F, -2.0F);
		right_wing_mid.addChild(cube_r40);
		setRotationAngle(cube_r40, -0.1524F, -0.8765F, 0.3095F);
		cube_r40.texOffs(44, 54).addBox(-0.1815F, -0.2683F, -0.1429F, 1.0F, 1.0F, 9.0F, 0.0F, true);

		right_wing_outer = new ModelRenderer(this);
		right_wing_outer.setPos(0.0F, 0.0F, 0.0F);
		right_wing_mid.addChild(right_wing_outer);


		cube_r41 = new ModelRenderer(this);
		cube_r41.setPos(-21.1F, -16.3F, 9.6F);
		right_wing_outer.addChild(cube_r41);
		setRotationAngle(cube_r41, 0.0831F, 0.7785F, 0.1123F);
		cube_r41.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r42 = new ModelRenderer(this);
		cube_r42.setPos(-19.1F, -16.3F, 11.6F);
		right_wing_outer.addChild(cube_r42);
		setRotationAngle(cube_r42, 0.0831F, 0.7785F, 0.1123F);
		cube_r42.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r43 = new ModelRenderer(this);
		cube_r43.setPos(-24.1F, -16.3F, 12.6F);
		right_wing_outer.addChild(cube_r43);
		setRotationAngle(cube_r43, 0.0831F, 0.7785F, 0.1123F);
		cube_r43.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r44 = new ModelRenderer(this);
		cube_r44.setPos(-22.1F, -16.3F, 14.6F);
		right_wing_outer.addChild(cube_r44);
		setRotationAngle(cube_r44, 0.0831F, 0.7785F, 0.1123F);
		cube_r44.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r45 = new ModelRenderer(this);
		cube_r45.setPos(-23.1F, -16.3F, 7.6F);
		right_wing_outer.addChild(cube_r45);
		setRotationAngle(cube_r45, 0.0831F, 0.7785F, 0.1123F);
		cube_r45.texOffs(16, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r46 = new ModelRenderer(this);
		cube_r46.setPos(-26.1F, -16.3F, 10.6F);
		right_wing_outer.addChild(cube_r46);
		setRotationAngle(cube_r46, 0.0831F, 0.7785F, 0.1123F);
		cube_r46.texOffs(16, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r47 = new ModelRenderer(this);
		cube_r47.setPos(-18.4F, -16.0F, 3.5F);
		right_wing_outer.addChild(cube_r47);
		setRotationAngle(cube_r47, -0.1328F, -0.747F, 0.2825F);
		cube_r47.texOffs(44, 54).addBox(-0.1211F, -0.1016F, -0.0511F, 1.0F, 1.0F, 9.0F, 0.0F, true);

		back = new ModelRenderer(this);
		back.setPos(0.0F, 24.0F, 0.0F);


		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -8.5F, 13.0F);
		back.addChild(tail);


		left_tail_outer = new ModelRenderer(this);
		left_tail_outer.setPos(0.0F, 8.5F, -13.0F);
		tail.addChild(left_tail_outer);


		cube_r48 = new ModelRenderer(this);
		cube_r48.setPos(-3.9F, -8.8F, 18.7F);
		left_tail_outer.addChild(cube_r48);
		setRotationAngle(cube_r48, 0.1483F, -0.4275F, -0.0883F);
		cube_r48.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r49 = new ModelRenderer(this);
		cube_r49.setPos(-2.9F, -8.8F, 16.7F);
		left_tail_outer.addChild(cube_r49);
		setRotationAngle(cube_r49, 0.1483F, -0.4275F, -0.0883F);
		cube_r49.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r50 = new ModelRenderer(this);
		cube_r50.setPos(-1.4F, -8.5F, 13.0F);
		left_tail_outer.addChild(cube_r50);
		setRotationAngle(cube_r50, 0.0873F, 0.0F, 0.0F);
		cube_r50.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r51 = new ModelRenderer(this);
		cube_r51.setPos(3.0F, -8.0F, 14.0F);
		left_tail_outer.addChild(cube_r51);
		setRotationAngle(cube_r51, 0.0873F, 0.4363F, 0.0F);
		cube_r51.texOffs(44, 54).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);

		right_tail_outer = new ModelRenderer(this);
		right_tail_outer.setPos(0.0F, 8.5F, -13.0F);
		tail.addChild(right_tail_outer);


		cube_r52 = new ModelRenderer(this);
		cube_r52.setPos(2.9F, -8.8F, 16.7F);
		right_tail_outer.addChild(cube_r52);
		setRotationAngle(cube_r52, 0.1483F, 0.4275F, 0.0883F);
		cube_r52.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r53 = new ModelRenderer(this);
		cube_r53.setPos(3.9F, -8.8F, 18.7F);
		right_tail_outer.addChild(cube_r53);
		setRotationAngle(cube_r53, 0.1483F, 0.4275F, 0.0883F);
		cube_r53.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r54 = new ModelRenderer(this);
		cube_r54.setPos(5.6F, -8.5F, 13.0F);
		right_tail_outer.addChild(cube_r54);
		setRotationAngle(cube_r54, 0.0873F, 0.0F, 0.0F);
		cube_r54.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r55 = new ModelRenderer(this);
		cube_r55.setPos(-3.0F, -8.0F, 14.0F);
		right_tail_outer.addChild(cube_r55);
		setRotationAngle(cube_r55, 0.0873F, -0.4363F, 0.0F);
		cube_r55.texOffs(44, 54).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 9.0F, 0.0F, true);

		left_tail_inner = new ModelRenderer(this);
		left_tail_inner.setPos(-0.4F, 8.5F, -14.0F);
		tail.addChild(left_tail_inner);


		cube_r56 = new ModelRenderer(this);
		cube_r56.setPos(0.7F, -8.8F, 17.0F);
		left_tail_inner.addChild(cube_r56);
		setRotationAngle(cube_r56, 0.1483F, -0.4275F, -0.0883F);
		cube_r56.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r57 = new ModelRenderer(this);
		cube_r57.setPos(-0.3F, -8.8F, 19.0F);
		left_tail_inner.addChild(cube_r57);
		setRotationAngle(cube_r57, 0.1483F, -0.4275F, -0.0883F);
		cube_r57.texOffs(1, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r58 = new ModelRenderer(this);
		cube_r58.setPos(0.0F, -8.0F, 14.0F);
		left_tail_inner.addChild(cube_r58);
		setRotationAngle(cube_r58, 0.0873F, 0.4363F, 0.0F);
		cube_r58.texOffs(44, 54).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);

		cube_r59 = new ModelRenderer(this);
		cube_r59.setPos(3.0F, -8.5F, 14.0F);
		left_tail_inner.addChild(cube_r59);
		setRotationAngle(cube_r59, 0.0873F, 0.0F, 0.0F);
		cube_r59.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		right_tail_inner = new ModelRenderer(this);
		right_tail_inner.setPos(0.0F, 8.5F, -13.0F);
		tail.addChild(right_tail_inner);


		cube_r60 = new ModelRenderer(this);
		cube_r60.setPos(0.7F, -8.8F, 18.1F);
		right_tail_inner.addChild(cube_r60);
		setRotationAngle(cube_r60, 0.1483F, 0.4275F, 0.0883F);
		cube_r60.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r61 = new ModelRenderer(this);
		cube_r61.setPos(-0.3F, -8.8F, 16.1F);
		right_tail_inner.addChild(cube_r61);
		setRotationAngle(cube_r61, 0.1483F, 0.4275F, 0.0883F);
		cube_r61.texOffs(1, 41).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);

		cube_r62 = new ModelRenderer(this);
		cube_r62.setPos(0.6F, -8.5F, 13.0F);
		right_tail_inner.addChild(cube_r62);
		setRotationAngle(cube_r62, 0.0873F, 0.0F, 0.0F);
		cube_r62.texOffs(16, 41).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);

		cube_r63 = new ModelRenderer(this);
		cube_r63.setPos(0.2F, -8.0F, 13.0F);
		right_tail_inner.addChild(cube_r63);
		setRotationAngle(cube_r63, 0.0873F, -0.4363F, 0.0F);
		cube_r63.texOffs(44, 54).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 9.0F, 0.0F, true);
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return null;
	}

	@Override
	public void setupAnim(GwahirEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setupAnim(getState(entityIn), entityIn.tickCount, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		beak_bottom_r1.xRot = MathHelper.cos(degToRad(entityIn.tickCount*7)) * degToRad(10);
	}

	@Override
	public void prepareMobModel(GwahirEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		this.prepare(getState(p_212843_1_));
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	private void setupAnim(GwahirModel.State p_217162_1_, int p_217162_2_, float p_217162_3_, float p_217162_4_, float p_217162_5_, float p_217162_6_, float p_217162_7_) {
		this.head.xRot = p_217162_7_ * ((float)Math.PI / 180F);
		this.head.yRot = p_217162_6_ * ((float)Math.PI / 180F);
		this.head.zRot = 0.0F;
		float f = (3 + p_217162_5_) * 0.13F;
		float f1 = 16.0F;

		switch(p_217162_1_) {
			case SITTING:
				break;
			case STANDING:
				this.left_leg.xRot += MathHelper.cos(p_217162_3_ * 0.6662F) * 1.4F * p_217162_4_;
				this.right_leg.xRot += MathHelper.cos(p_217162_3_ * 0.6662F + (float)Math.PI) * 1.4F * p_217162_4_;
			case FLYING:
			default:
				this.tail.xRot = -(5.0F + MathHelper.cos(f * 2.0F) * 5.0F) * ((float)Math.PI / 180F);
				this.left_wing.zRot = MathHelper.cos(f) * f1 * ((float)Math.PI / 180F);
				this.right_wing.zRot = -this.left_wing.zRot;
		}
	}

	private void prepare(State p_217160_1_) {
		this.left_leg.xRot = -0.0299F;
		this.right_leg.xRot = -0.0299F;
		this.left_leg.zRot = 0.0F;
		this.right_leg.zRot = 0.0F;
		switch(p_217160_1_) {
			case SITTING:
				this.tail.xRot = 1.5388988F;
				this.left_wing.zRot = -0.0873F;
				this.right_wing.zRot = 0.0873F;
				++this.left_leg.xRot;
				++this.right_leg.xRot;
				break;
			case STANDING:
			default:
				break;
			case FLYING:
				this.left_leg.xRot += 0.6981317F;
				this.right_leg.xRot += 0.6981317F;
		}

	}

	private static GwahirModel.State getState(GwahirEntity p_217158_0_) {
		if (p_217158_0_.isInSittingPose()) {
			return GwahirModel.State.SITTING;
		} else {
			return p_217158_0_.isFlying() ? GwahirModel.State.FLYING : GwahirModel.State.STANDING;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public enum State {
		FLYING,
		STANDING,
		SITTING;
	}
}