package com.greatorator.tolkienmobs.client.render.model.boss;

import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import com.greatorator.tolkienmobs.entity.entityai.AIStates;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelTMGwaihir extends ModelTTM {
    public ModelRenderer Body;
    public ModelRenderer BodyWingless;
    public ModelRenderer Tail;
    public ModelRenderer Head;
    public ModelRenderer WingL;
    public ModelRenderer WingR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer BeakUpper;
    public ModelRenderer BeakLower;
    public ModelRenderer neck;
    public ModelRenderer BeakUpper2;
    public ModelRenderer BeakUpper3;
    public ModelRenderer WingboneL;
    public ModelRenderer WingboneL_1;
    public ModelRenderer WingboneL_2;
    public ModelRenderer WingboneR;
    public ModelRenderer WingboneR_1;
    public ModelRenderer WingboneR_2;
    public ModelRenderer Leg1L;
    public ModelRenderer Leg2L;
    public ModelRenderer FootL;
    public ModelRenderer Toe1L;
    public ModelRenderer Toe2L;
    public ModelRenderer Toe3L;
    public ModelRenderer Toe4L;
    public ModelRenderer Toe1L_1;
    public ModelRenderer Toe2L_1;
    public ModelRenderer Toe3L_1;
    public ModelRenderer Toe4L_1;
    public ModelRenderer Leg1R;
    public ModelRenderer Leg2R;
    public ModelRenderer FootR;
    public ModelRenderer Toe1R;
    public ModelRenderer Toe2R;
    public ModelRenderer Toe3R;
    public ModelRenderer Toe4R;
    public ModelRenderer Toe1R_1;
    public ModelRenderer Toe2R_1;
    public ModelRenderer Toe3R_1;
    public ModelRenderer Toe4R_1;

    protected float[][] perchedCycle = new float[][] {
            // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
            { -70F, 70F, 70F, 0F, 0F, 0F, 90F, 0F }
    };

    protected float[][] takingOffCycle = new float[][] {
            // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
            { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
            { -4F, 4F, 70F, -10F, 0F, 0F, 15F, -15F },
            { -2F, 2F, 70F, -10F, 0F, 0F, 10F, -10F },
            { -1F, 1F, 70F, -10F, 0F, 0F, 5F, -5F },
            { 0F, 0F, 70F, -10F, 0F, 0F, 0F, 0F },
            { 1F, -1F, 70F, -10F, 0F, 0F, -5F, 5F },
            { 2F, -2F, 70F, -10F, 0F, 0F, -10F, 10F },
            { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
            { 5F, -5F, 70F, -10F, 0F, 0F, -20F, 20F },
            { 6F, -6F, 70F, -10F, 0F, 0F, -25F, 20F },
            { 7F, -7F, 70F, -10F, 0F, 0F, -30F, 20F },
            { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
            { 0F, 0F, 70F, -10F, 0F, 0F, -0F, 0F },
            { -3F, 3F, 70F, -10F, 0F, 0F, 10F, -10F },
            { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
            { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
            { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
            { -12F, 12F, 70F, -10F, 0F, 0F, 50F, -20F },
            { -10F, 10F, 70F, -10F, 0F, 0F, 45F, -20F },
            { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
            { -8F, 8F, 70F, -10F, 0F, 0F, 35F, -20F },
            { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
            { -6F, 6F, 70F, -10F, 0F, 0F, 25F, -20F }
    };

    protected float[][] soaringCycle = new float[][] {
            // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
            { 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F }
    };

    protected float[][] divingCycle = new float[][] {
            // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
            { 90F, 0F, 0F, 0F, 0F, -50F, 0F, 0F }
    };

    protected float[][] attackingCycle = new float[][] {
            // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
            { 90F, 0F, 0F, 0F, 0F, -50F, 0F, 0F }
    };

    protected float[][] landingCycle = new float[][] {
            // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
            { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
    };

    protected float[][] travellingCycle = new float[][] {
            // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleY, wing1AngleZ, wing2AngleZ
            { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
            { -4F, 4F, 70F, -10F, 0F, 0F, 15F, -15F },
            { -2F, 2F, 70F, -10F, 0F, 0F, 10F, -10F },
            { -1F, 1F, 70F, -10F, 0F, 0F, 5F, -5F },
            { 0F, 0F, 70F, -10F, 0F, 0F, 0F, 0F },
            { 1F, -1F, 70F, -10F, 0F, 0F, -5F, 5F },
            { 2F, -2F, 70F, -10F, 0F, 0F, -10F, 10F },
            { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
            { 5F, -5F, 70F, -10F, 0F, 0F, -20F, 20F },
            { 6F, -6F, 70F, -10F, 0F, 0F, -25F, 20F },
            { 7F, -7F, 70F, -10F, 0F, 0F, -30F, 20F },
            { 4F, -4F, 70F, -10F, 0F, 0F, -15F, 15F },
            { 0F, 0F, 70F, -10F, 0F, 0F, -0F, 0F },
            { -3F, 3F, 70F, -10F, 0F, 0F, 10F, -10F },
            { -5F, 5F, 70F, -10F, 0F, 0F, 20F, -20F },
            { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
            { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
            { -12F, 12F, 70F, -10F, 0F, 0F, 50F, -20F },
            { -10F, 10F, 70F, -10F, 0F, 0F, 45F, -20F },
            { -10F, 10F, 70F, -10F, 0F, 0F, 40F, -20F },
            { -8F, 8F, 70F, -10F, 0F, 0F, 35F, -20F },
            { -7F, 7F, 70F, -10F, 0F, 0F, 30F, -20F },
            { -6F, 6F, 70F, -10F, 0F, 0F, 25F, -20F }
    };

    public ModelTMGwaihir() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.WingboneL = new ModelRenderer(this, 55, 86);
        this.WingboneL.setRotationPoint(0.699999988079071F, -0.5F, 0.20000000298023224F);
        this.WingboneL.addBox(0.0F, -0.10000000149011612F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(WingboneL, 0.0F, 0.7853981633974483F, 0.0F);
        this.Toe2R = new ModelRenderer(this, 50, 105);
        this.Toe2R.setRotationPoint(-0.6000000238418579F, 0.5F, -2.9000000953674316F);
        this.Toe2R.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Toe2R, 0.0F, 0.7853981633974483F, 0.0F);
        this.WingL = new ModelRenderer(this, -31, 55);
        this.WingL.setRotationPoint(4.199999809265137F, -4.5F, -6.0F);
        this.WingL.addBox(0.0F, 0.0F, -7.5F, 55, 0, 31, 0.0F);
        this.Toe4L_1 = new ModelRenderer(this, 55, 109);
        this.Toe4L_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe4L_1.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe4L_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.Leg2R = new ModelRenderer(this, 49, 100);
        this.Leg2R.setRotationPoint(0.5F, 5.400000095367432F, 0.5F);
        this.Leg2R.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3, 0.0F);
        this.FootR = new ModelRenderer(this, 49, 100);
        this.FootR.setRotationPoint(0.0F, 4.699999809265137F, -0.6000000238418579F);
        this.FootR.addBox(-2.0F, 0.0F, -2.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(FootR, 0.7853981633974483F, 0.0F, 0.0F);
        this.Toe2L_1 = new ModelRenderer(this, 55, 109);
        this.Toe2L_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe2L_1.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe2L_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.Leg2L = new ModelRenderer(this, 49, 100);
        this.Leg2L.setRotationPoint(0.5F, 5.400000095367432F, 0.5F);
        this.Leg2L.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3, 0.0F);
        this.Toe1L = new ModelRenderer(this, 50, 105);
        this.Toe1L.setRotationPoint(1.0F, 0.5F, -2.4000000953674316F);
        this.Toe1L.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.BeakUpper2 = new ModelRenderer(this, 14, 123);
        this.BeakUpper2.setRotationPoint(0.5F, -0.9F, -2.0F);
        this.BeakUpper2.addBox(-1.5F, -1.5F, -7.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(BeakUpper2, 0.2617993877991494F, 0.0F, 0.0F);
        this.WingboneR = new ModelRenderer(this, 55, 86);
        this.WingboneR.setRotationPoint(-0.699999988079071F, -0.5F, 0.20000000298023224F);
        this.WingboneR.addBox(0.0F, -0.10000000149011612F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(WingboneR, 0.0F, 2.356194490192345F, 0.0F);
        this.WingboneR_1 = new ModelRenderer(this, 55, 86);
        this.WingboneR_1.setRotationPoint(5.199999809265137F, -0.10000000149011612F, -0.20000000298023224F);
        this.WingboneR_1.addBox(0.0F, 0.0F, 0.0F, 33, 1, 1, 0.0F);
        this.setRotateAngle(WingboneR_1, 0.0F, 0.7853981633974483F, 0.0F);
        this.WingboneL_1 = new ModelRenderer(this, 55, 86);
        this.WingboneL_1.setRotationPoint(6.0F, -0.10000000149011612F, -0.5F);
        this.WingboneL_1.addBox(0.0F, 0.0F, 0.0F, 33, 1, 1, 0.0F);
        this.setRotateAngle(WingboneL_1, 0.0F, -0.7853981633974483F, 0.0F);
        this.Toe4L = new ModelRenderer(this, 50, 105);
        this.Toe4L.setRotationPoint(-0.5F, 0.0F, 2.0F);
        this.Toe4L.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Toe4L, 0.7853981633974483F, 3.141592653589793F, 0.0F);
        this.Tail = new ModelRenderer(this, -14, 86);
        this.Tail.setRotationPoint(0.0F, -4.5F, 11.5F);
        this.Tail.addBox(-12.5F, -0.20000000298023224F, 0.0F, 25, 0, 14, 0.0F);
        this.setRotateAngle(Tail, 0.2617993877991494F, 0.0F, 0.0F);
        this.WingboneL_2 = new ModelRenderer(this, 55, 86);
        this.WingboneL_2.setRotationPoint(33.0F, -0.10000000149011612F, 0.0F);
        this.WingboneL_2.addBox(0.0F, 0.0F, 0.0F, 14, 1, 1, 0.0F);
        this.setRotateAngle(WingboneL_2, 0.0F, -0.4607669158686003F, 0.0F);
        this.Toe3L_1 = new ModelRenderer(this, 55, 109);
        this.Toe3L_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe3L_1.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe3L_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.Toe1R_1 = new ModelRenderer(this, 55, 109);
        this.Toe1R_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe1R_1.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe1R_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 58, 114);
        this.neck.setRotationPoint(-3.0F, -1.0F, -3.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 6, 4, 8, 0.0F);
        this.setRotateAngle(neck, -1.0754718664367782F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 100);
        this.Head.setRotationPoint(0.0F, -5.0F, -10.5F);
        this.Head.addBox(-3.5F, -3.5F, -11.0F, 7, 7, 8, 0.0F);
        this.setRotateAngle(Head, 0.5235987755982988F, 0.0F, 0.0F);
        this.BeakUpper3 = new ModelRenderer(this, 24, 123);
        this.BeakUpper3.setRotationPoint(0.5F, 1.8F, -0.7F);
        this.BeakUpper3.addBox(-1.5F, -1.5F, -7.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(BeakUpper3, -0.2617993877991494F, 0.0F, 0.0F);
        this.Toe2R_1 = new ModelRenderer(this, 55, 109);
        this.Toe2R_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe2R_1.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe2R_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.FootL = new ModelRenderer(this, 49, 100);
        this.FootL.setRotationPoint(0.0F, 4.699999809265137F, -0.6000000238418579F);
        this.FootL.addBox(-2.0F, 0.0F, -2.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(FootL, 0.7853981633974483F, 0.0F, 0.0F);
        this.Leg1L = new ModelRenderer(this, 65, 100);
        this.Leg1L.setRotationPoint(0.5F, 5.400000095367432F, 1.0F);
        this.Leg1L.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(Leg1L, -0.2617993877991494F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 38, 95);
        this.Body.setRotationPoint(0.0F, 5.599999904632568F, 8.0F);
        this.Body.addBox(-6.0F, -5.0F, -11.5F, 12, 10, 23, 0.0F);
        this.setRotateAngle(Body, -0.5235987755982988F, 0.0F, 0.0F);
        this.Toe2L = new ModelRenderer(this, 50, 105);
        this.Toe2L.setRotationPoint(-0.6000000238418579F, 0.5F, -2.9000000953674316F);
        this.Toe2L.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Toe2L, 0.0F, 0.7853981633974483F, 0.0F);
        this.Toe4R_1 = new ModelRenderer(this, 55, 109);
        this.Toe4R_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe4R_1.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe4R_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.Toe3R = new ModelRenderer(this, 50, 105);
        this.Toe3R.setRotationPoint(2.200000047683716F, 0.5F, -1.0F);
        this.Toe3R.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Toe3R, 0.0F, -0.7853981633974483F, 0.0F);
        this.Toe4R = new ModelRenderer(this, 50, 105);
        this.Toe4R.setRotationPoint(-0.5F, 0.0F, 2.0F);
        this.Toe4R.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Toe4R, 0.7853981633974483F, 3.141592653589793F, 0.0F);
        this.WingboneR_2 = new ModelRenderer(this, 55, 86);
        this.WingboneR_2.setRotationPoint(33.0F, -0.10000000149011612F, 0.0F);
        this.WingboneR_2.addBox(0.0F, 0.0F, 0.0F, 14, 1, 1, 0.0F);
        this.setRotateAngle(WingboneR_2, 0.0F, 0.4607669158686003F, 0.0F);
        this.Toe1R = new ModelRenderer(this, 50, 105);
        this.Toe1R.setRotationPoint(1.0F, 0.5F, -2.4000000953674316F);
        this.Toe1R.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.Leg1R = new ModelRenderer(this, 65, 100);
        this.Leg1R.setRotationPoint(-1.5F, 5.400000095367432F, 1.0F);
        this.Leg1R.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(Leg1R, -0.2617993877991494F, 0.0F, 0.0F);
        this.BeakUpper = new ModelRenderer(this, 0, 121);
        this.BeakUpper.setRotationPoint(0.0F, -0.6F, -7.0F);
        this.BeakUpper.addBox(-1.5F, -1.5F, -7.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(BeakUpper, 0.2617993877991494F, 0.0F, 0.0F);
        this.BeakLower = new ModelRenderer(this, 0, 116);
        this.BeakLower.setRotationPoint(0.5F, 2.8F, -7.9F);
        this.BeakLower.addBox(-1.5F, -1.5F, -6.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(BeakLower, -0.05235987755982988F, 0.0F, 0.0F);
        this.Toe3L = new ModelRenderer(this, 50, 105);
        this.Toe3L.setRotationPoint(2.200000047683716F, 0.5F, -1.0F);
        this.Toe3L.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Toe3L, 0.0F, -0.7853981633974483F, 0.0F);
        this.LegL = new ModelRenderer(this, 65, 100);
        this.LegL.setRotationPoint(3.700000047683716F, 2.0F, 5.0F);
        this.LegL.addBox(-2.0F, 0.0F, -2.0F, 5, 7, 5, 0.0F);
        this.Toe1L_1 = new ModelRenderer(this, 55, 109);
        this.Toe1L_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe1L_1.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe1L_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.Toe3R_1 = new ModelRenderer(this, 55, 109);
        this.Toe3R_1.setRotationPoint(0.0F, 0.0F, -1.899999976158142F);
        this.Toe3R_1.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe3R_1, 0.3159046012688764F, 0.0F, 0.0F);
        this.WingR = new ModelRenderer(this, -31, 55);
        this.WingR.mirror = true;
        this.WingR.setRotationPoint(-4.199999809265137F, -4.5F, -6.0F);
        this.WingR.addBox(-55.0F, 0.0F, -7.5F, 55, 0, 31, 0.0F);
        this.LegR = new ModelRenderer(this, 65, 100);
        this.LegR.setRotationPoint(-2.700000047683716F, 2.0F, 5.0F);
        this.LegR.addBox(-4.0F, 0.0F, -2.0F, 5, 7, 5, 0.0F);
        this.WingL.addChild(this.WingboneL);
        this.FootR.addChild(this.Toe2R);
        this.Body.addChild(this.WingL);
        this.Toe4L.addChild(this.Toe4L_1);
        this.Leg1R.addChild(this.Leg2R);
        this.Leg2R.addChild(this.FootR);
        this.Toe2L.addChild(this.Toe2L_1);
        this.Leg1L.addChild(this.Leg2L);
        this.FootL.addChild(this.Toe1L);
        this.BeakUpper.addChild(this.BeakUpper2);
        this.WingR.addChild(this.WingboneR);
        this.WingboneR.addChild(this.WingboneR_1);
        this.WingboneL.addChild(this.WingboneL_1);
        this.FootL.addChild(this.Toe4L);
        this.Body.addChild(this.Tail);
        this.WingboneL_1.addChild(this.WingboneL_2);
        this.Toe3L.addChild(this.Toe3L_1);
        this.Toe1R.addChild(this.Toe1R_1);
        this.Head.addChild(this.neck);
        this.Body.addChild(this.Head);
        this.BeakUpper2.addChild(this.BeakUpper3);
        this.Toe2R.addChild(this.Toe2R_1);
        this.Leg2L.addChild(this.FootL);
        this.LegL.addChild(this.Leg1L);
        this.FootL.addChild(this.Toe2L);
        this.Toe4R.addChild(this.Toe4R_1);
        this.FootR.addChild(this.Toe3R);
        this.FootR.addChild(this.Toe4R);
        this.WingboneR_1.addChild(this.WingboneR_2);
        this.FootR.addChild(this.Toe1R);
        this.LegR.addChild(this.Leg1R);
        this.Head.addChild(this.BeakUpper);
        this.Head.addChild(this.BeakLower);
        this.FootL.addChild(this.Toe3L);
        this.Body.addChild(this.LegL);
        this.Toe1L.addChild(this.Toe1L_1);
        this.Toe3R.addChild(this.Toe3R_1);
        this.Body.addChild(this.WingR);
        this.Body.addChild(this.LegR);

        this.BodyWingless = new ModelRenderer(this, 38, 95);
        this.BodyWingless.setRotationPoint(0.0F, 5.6F, 8.0F);
        this.BodyWingless.addBox(-6.0F, -5.0F, -11.5F, 12, 10, 23, 0.0F);
        this.setRotateAngle(BodyWingless, -0.5235987755982988F, 0.0F, 0.0F);
        this.BodyWingless.setTextureSize(textureWidth, textureHeight);
        this.BodyWingless.mirror = true;
        this.BodyWingless.addChild(Head);
        this.BodyWingless.addChild(LegL);
        this.BodyWingless.addChild(LegR);
        this.BodyWingless.addChild(Tail);
    }

    @Override
    public void render(Entity parEntity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        // best to cast to actual expected entity, to allow access to custom fields related to animation
        renderBirds((EntityTMBirds) parEntity, f5);
    }

    public void renderBirds(EntityTMBirds parBird, float parRenderFloat)
    {
        setRotationAngles(parBird);

        // scale the whole thing for big or small entities
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*parBird.getScaleFactor(), 0F);
        // translate a bit extra if perched, as legs don't quite reach ground otherwise
        if (parBird.getState() == AIStates.STATE_PERCHED
                || parBird.getState() == AIStates.STATE_PERCHED_TAMED)
        {
            GL11.glTranslatef(0F, 0.2F*parBird.getScaleFactor(), 0F);
        }

        GL11.glScalef(parBird.getScaleFactor(), parBird.getScaleFactor(), parBird.getScaleFactor());

        // should only need to render body because all rest are children
        if (parBird.getState() == AIStates.STATE_PERCHED
                || parBird.getState() == AIStates.STATE_PERCHED_TAMED)
        { BodyWingless.render(parRenderFloat);
        }
        else
        {
            Body.render(parRenderFloat);
        }

        // don't forget to pop the matrix for overall scaling
        GL11.glPopMatrix();
    }

    public void setRotationAngles(EntityTMBirds parEntity)
    {
        if (parEntity.getState() == AIStates.STATE_TAKING_OFF)
        {
            doAnimate(parEntity, takingOffCycle);
        }
        else if (parEntity.getState() == AIStates.STATE_DIVING)
        {
            doAnimate(parEntity, soaringCycle);
        }
        else if (parEntity.getState() == AIStates.STATE_LANDING)
        {
            doAnimate(parEntity, landingCycle);
        }
        else if (parEntity.getState() == AIStates.STATE_PERCHED
                || parEntity.getState() == AIStates.STATE_PERCHED_TAMED)
        {
            doAnimate(parEntity, perchedCycle);
        }
        else if (parEntity.getState() == AIStates.STATE_SOARING
                || parEntity.getState() == AIStates.STATE_SOARING_TAMED)
        {
            doAnimate(parEntity, soaringCycle);
        }
        else if (parEntity.getState() == AIStates.STATE_TRAVELLING
                || parEntity.getState() == AIStates.STATE_SEEKING)
        {
            doAnimate(parEntity, travellingCycle);
        }
        else if (parEntity.getState() == AIStates.STATE_ATTACKING)
        {
            doAnimate(parEntity, attackingCycle);
        }

    }

    public void doAnimate(EntityTMBirds parEntity, float[][] parCycleArray)
    {
        cycleIndex = (int)Math.floor((parEntity.ticksExisted+parEntity.getRandFactor()*2)%parCycleArray.length)/2;
        // will need to set based on entity state
        // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleZ, wing2AngleZ
        setRotation(Body, parCycleArray[cycleIndex][0], 0, 0);
        setRotation(BodyWingless, parCycleArray[cycleIndex][0], 0, 0);
        setRotation(Head, parCycleArray[cycleIndex][1], 0, 0);
        // both legs have same angle
        setRotation(LegL, parCycleArray[cycleIndex][2], 0, 0);
        setRotation(LegR, parCycleArray[cycleIndex][2], 0, 0);
        setRotation(Tail, parCycleArray[cycleIndex][3], 0, 0);
        // both legs have same (well negative) angle
        setRotation(WingL, parCycleArray[cycleIndex][4], parCycleArray[cycleIndex][5], parCycleArray[cycleIndex][6]);
        setRotation(WingR, parCycleArray[cycleIndex][4], -parCycleArray[cycleIndex][5], -parCycleArray[cycleIndex][6]);
        setRotation(WingL, 0, -21F, -parCycleArray[cycleIndex][7]);
        setRotation(WingR, 0, 21F, parCycleArray[cycleIndex][7]);
    }
}