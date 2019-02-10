package com.greatorator.tolkienmobs.client.render.model.boss;

import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import com.greatorator.tolkienmobs.entity.boss.EntityBalrog;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Balrog - GreatOrator
 */
public class ModelBalrog extends ModelTTM {
    public ModelRenderer BalrogBody;
    public ModelRenderer BalrogNeck;
    public ModelRenderer BalrogLegR;
    public ModelRenderer BalrogLegL;
    public ModelRenderer bipedRightArmUpper;
    public ModelRenderer bipedLeftArmUpper;
    public ModelRenderer BalrogWingR;
    public ModelRenderer BalrogWingL;
    public ModelRenderer BalrogChest;
    public ModelRenderer BalrogSkull;
    public ModelRenderer BalrogHornR1;
    public ModelRenderer BalrogHornL1;
    public ModelRenderer BalrogMouth;
    public ModelRenderer BalrogHornR2;
    public ModelRenderer BalrogHornR3;
    public ModelRenderer BalrogHornR4;
    public ModelRenderer BalrogHornR4_1;
    public ModelRenderer BalrogHornL2;
    public ModelRenderer BalrogHornL3;
    public ModelRenderer BalrogHornL4;
    public ModelRenderer BalrogHornL4_1;
    public ModelRenderer BalrogTooth1;
    public ModelRenderer BalrogTooth2;
    public ModelRenderer BalrogTooth3;
    public ModelRenderer BalrogTooth4;
    public ModelRenderer BalrogLegUpperR;
    public ModelRenderer BalrogLegLower1R;
    public ModelRenderer BalrogLegLower2R;
    public ModelRenderer BalrogLegFootR;
    public ModelRenderer BalrogLegUpperL;
    public ModelRenderer BalrogLegLower1L;
    public ModelRenderer BalrogLegLower2L;
    public ModelRenderer BalrogLegFootL;
    public ModelRenderer BalrogWingSkin2;
    public ModelRenderer BalrogWingBone2R;
    public ModelRenderer BalrogWingBone3R;
    public ModelRenderer BalrogWingBone4R;
    public ModelRenderer BalrogWingBone5R;
    public ModelRenderer BalrogWingBone6R;
    public ModelRenderer BalrogWingSkin1;
    public ModelRenderer BalrogWingBone2L;
    public ModelRenderer BalrogWingBone3L;
    public ModelRenderer BalrogWingBone4L;
    public ModelRenderer BalrogWingBone5L;
    public ModelRenderer BalrogWingBone6L;

    public ModelBalrog() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.BalrogHornR4_1 = new ModelRenderer(this, 114, 2);
        this.BalrogHornR4_1.setRotationPoint(-4.4F, 0.5F, 0.4F);
        this.BalrogHornR4_1.addBox(-5.0F, -1.5F, -1.6F, 5, 1, 1, 0.0F);
        this.setRotateAngle(BalrogHornR4_1, 0.0F, -0.17453292519943295F, 0.0F);
        this.BalrogWingBone5R = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone5R.mirror = true;
        this.BalrogWingBone5R.setRotationPoint(6.1F, 0.5F, 0.5F);
        this.BalrogWingBone5R.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
        this.BalrogTooth4 = new ModelRenderer(this, 0, 28);
        this.BalrogTooth4.setRotationPoint(-2.0F, -2.2F, -4.5F);
        this.BalrogTooth4.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(BalrogTooth4, -0.08726646259971647F, 0.0F, 0.0F);
        this.BalrogTooth2 = new ModelRenderer(this, 0, 28);
        this.BalrogTooth2.setRotationPoint(-2.0F, 2.2F, -4.5F);
        this.BalrogTooth2.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(BalrogTooth2, 0.08726646259971647F, 0.0F, 0.0F);
        this.BalrogNeck = new ModelRenderer(this, 106, 15);
        this.BalrogNeck.setRotationPoint(0.0F, -22.5F, -1.0F);
        this.BalrogNeck.addBox(-2.5F, -4.5F, -2.5F, 5, 6, 5, 0.0F);
        this.setRotateAngle(BalrogNeck, 0.17453292519943295F, 0.0F, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 0, 48);
        this.bipedRightArm.setRotationPoint(-8.0F, -20.0F, 0.5F);
        this.bipedRightArm.addBox(-5.5F, -1.5F, -3.0F, 5, 10, 6, 0.0F);
        this.BalrogWingSkin2 = new ModelRenderer(this, 0, 0);
        this.BalrogWingSkin2.setRotationPoint(18.5F, -4.3F, 0.0F);
        this.BalrogWingSkin2.addBox(-8.5F, 0.0F, 0.0F, 53, 26, 0, 0.0F);
        this.setRotateAngle(BalrogWingSkin2, 0.0F, 0.0F, 0.7853981633974483F);
        this.BalrogWingBone3L = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone3L.setRotationPoint(16.2F, -0.4F, 0.0F);
        this.BalrogWingBone3L.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingBone3L, 0.0F, 0.0F, 0.6981317007977318F);
        this.BalrogWingR = new ModelRenderer(this, 38, 26);
        this.BalrogWingR.mirror = true;
        this.BalrogWingR.setRotationPoint(-4.5F, -18.0F, 3.0F);
        this.BalrogWingR.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingR, 0.0F, -2.356194490192345F, 0.7853981633974483F);
        this.BalrogLegLower1R = new ModelRenderer(this, 50, 48);
        this.BalrogLegLower1R.setRotationPoint(0.5F, 8.0F, -1.5F);
        this.BalrogLegLower1R.addBox(-2.5F, 0.0F, -3.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(BalrogLegLower1R, 1.3962634015954636F, 0.0F, 0.0F);
        this.BalrogLegUpperL = new ModelRenderer(this, 46, 47);
        this.BalrogLegUpperL.mirror = true;
        this.BalrogLegUpperL.setRotationPoint(-0.5F, 1.0F, 1.0F);
        this.BalrogLegUpperL.addBox(-1.5F, 0.0F, -3.0F, 5, 11, 6, 0.0F);
        this.setRotateAngle(BalrogLegUpperL, -0.6981317007977318F, 0.0F, 0.0F);
        this.bipedRightArmUpper = new ModelRenderer(this, 0, 31);
        this.bipedRightArmUpper.setRotationPoint(-2.5F, 8.0F, 1.0F);
        this.bipedRightArmUpper.addBox(-2.5F, 0.0F, -3.0F, 4, 13, 4, 0.0F);
        this.setRotateAngle(bipedRightArmUpper, -0.17453292519943295F, 0.0F, 0.0F);
        this.BalrogLegFootR = new ModelRenderer(this, 16, 41);
        this.BalrogLegFootR.setRotationPoint(-0.5F, 8.5F, 0.0F);
        this.BalrogLegFootR.addBox(-1.5F, 0.0F, -4.5F, 4, 1, 6, 0.0F);
        this.setRotateAngle(BalrogLegFootR, 0.3490658503988659F, 0.0F, 0.0F);
        this.BalrogLegFootL = new ModelRenderer(this, 16, 41);
        this.BalrogLegFootL.mirror = true;
        this.BalrogLegFootL.setRotationPoint(-0.5F, 8.5F, 0.0F);
        this.BalrogLegFootL.addBox(-1.5F, 0.0F, -4.5F, 4, 1, 6, 0.0F);
        this.setRotateAngle(BalrogLegFootL, 0.3490658503988659F, 0.0F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 0, 48);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(8.5F, -20.0F, 0.5F);
        this.bipedLeftArm.addBox(0.0F, -1.5F, -3.0F, 5, 10, 6, 0.0F);
        this.BalrogWingBone6R = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone6R.mirror = true;
        this.BalrogWingBone6R.setRotationPoint(16.2F, -0.2F, 0.0F);
        this.BalrogWingBone6R.addBox(0.0F, -1.0F, -1.0F, 21, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingBone6R, 0.0F, 0.0F, 0.6981317007977318F);
        this.BalrogWingBone2L = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone2L.setRotationPoint(6.5F, 0.5F, 0.5F);
        this.BalrogWingBone2L.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
        this.BalrogBody = new ModelRenderer(this, 78, 43);
        this.BalrogBody.setRotationPoint(0.0F, -22.0F, 0.0F);
        this.BalrogBody.addBox(-8.5F, 0.0F, -4.0F, 17, 13, 8, 0.0F);
        this.BalrogHornL3 = new ModelRenderer(this, 114, 1);
        this.BalrogHornL3.setRotationPoint(4.0F, -0.2F, 0.0F);
        this.BalrogHornL3.addBox(0.0F, -1.5F, -1.6F, 5, 2, 2, 0.0F);
        this.setRotateAngle(BalrogHornL3, 0.0F, 0.3490658503988659F, 0.8726646259971648F);
        this.BalrogWingBone4L = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone4L.setRotationPoint(16.2F, -0.2F, 0.0F);
        this.BalrogWingBone4L.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingBone4L, 0.0F, 0.0F, 0.3490658503988659F);
        this.BalrogLegR = new ModelRenderer(this, 22, 48);
        this.BalrogLegR.setRotationPoint(-6.0F, 0.0F, -1.0F);
        this.BalrogLegR.addBox(-2.5F, -3.0F, -3.0F, 4, 8, 8, 0.0F);
        this.BalrogHornL2 = new ModelRenderer(this, 114, 1);
        this.BalrogHornL2.setRotationPoint(4.5F, 0.3F, 0.5F);
        this.BalrogHornL2.addBox(0.0F, -1.5F, -1.5F, 5, 2, 2, 0.0F);
        this.setRotateAngle(BalrogHornL2, 0.0F, 0.17453292519943295F, 0.3490658503988659F);
        this.BalrogLegLower2R = new ModelRenderer(this, 68, 52);
        this.BalrogLegLower2R.setRotationPoint(-0.5F, 10.5F, -1.2F);
        this.BalrogLegLower2R.addBox(-1.5F, 0.0F, -1.5F, 3, 9, 3, 0.0F);
        this.setRotateAngle(BalrogLegLower2R, -1.0471975511965976F, 0.0F, 0.0F);
        this.BalrogWingBone2R = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone2R.mirror = true;
        this.BalrogWingBone2R.setRotationPoint(6.5F, 0.5F, 0.5F);
        this.BalrogWingBone2R.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
        this.BalrogHornR3 = new ModelRenderer(this, 114, 1);
        this.BalrogHornR3.setRotationPoint(-4.0F, -0.2F, 0.0F);
        this.BalrogHornR3.addBox(-5.0F, -1.5F, -1.6F, 5, 2, 2, 0.0F);
        this.setRotateAngle(BalrogHornR3, 0.0F, -0.3490658503988659F, -0.8726646259971648F);
        this.BalrogLegLower2L = new ModelRenderer(this, 68, 52);
        this.BalrogLegLower2L.mirror = true;
        this.BalrogLegLower2L.setRotationPoint(0.5F, 10.5F, -1.2F);
        this.BalrogLegLower2L.addBox(-1.5F, 0.0F, -1.5F, 3, 9, 3, 0.0F);
        this.setRotateAngle(BalrogLegLower2L, -1.0471975511965976F, 0.0F, 0.0F);
        this.BalrogWingBone3R = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone3R.mirror = true;
        this.BalrogWingBone3R.setRotationPoint(16.2F, -0.4F, 0.0F);
        this.BalrogWingBone3R.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingBone3R, 0.0F, 0.0F, 0.6981317007977318F);
        this.BalrogLegLower1L = new ModelRenderer(this, 50, 48);
        this.BalrogLegLower1L.mirror = true;
        this.BalrogLegLower1L.setRotationPoint(0.5F, 8.0F, -1.5F);
        this.BalrogLegLower1L.addBox(-1.5F, 0.0F, -3.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(BalrogLegLower1L, 1.3962634015954636F, 0.0F, 0.0F);
        this.BalrogWingBone5L = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone5L.setRotationPoint(6.1F, 0.5F, 0.5F);
        this.BalrogWingBone5L.addBox(0.0F, -1.0F, -1.0F, 17, 1, 1, 0.0F);
        this.BalrogHornL1 = new ModelRenderer(this, 112, 0);
        this.BalrogHornL1.setRotationPoint(3.5F, -2.8F, 0.0F);
        this.BalrogHornL1.addBox(0.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
        this.setRotateAngle(BalrogHornL1, -0.17453292519943295F, -0.17453292519943295F, -0.5235987755982988F);
        this.BalrogHornR2 = new ModelRenderer(this, 114, 1);
        this.BalrogHornR2.setRotationPoint(-4.5F, 0.3F, 0.5F);
        this.BalrogHornR2.addBox(-5.0F, -1.5F, -1.5F, 5, 2, 2, 0.0F);
        this.setRotateAngle(BalrogHornR2, 0.0F, -0.17453292519943295F, -0.3490658503988659F);
        this.BalrogChest = new ModelRenderer(this, 82, 26);
        this.BalrogChest.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.BalrogChest.addBox(-8.0F, 0.0F, -3.5F, 16, 10, 7, 0.0F);
        this.BalrogMouth = new ModelRenderer(this, 16, 28);
        this.BalrogMouth.setRotationPoint(0.0F, 3.9F, -1.8F);
        this.BalrogMouth.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 8, 0.0F);
        this.bipedLeftArmUpper = new ModelRenderer(this, 0, 31);
        this.bipedLeftArmUpper.mirror = true;
        this.bipedLeftArmUpper.setRotationPoint(3.0F, 8.0F, 1.0F);
        this.bipedLeftArmUpper.addBox(-2.5F, 0.0F, -3.0F, 4, 13, 4, 0.0F);
        this.setRotateAngle(bipedLeftArmUpper, -0.17453292519943295F, 0.0F, 0.0F);
        this.BalrogTooth3 = new ModelRenderer(this, 0, 28);
        this.BalrogTooth3.setRotationPoint(2.0F, -2.2F, -4.5F);
        this.BalrogTooth3.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(BalrogTooth3, -0.08726646259971647F, 0.0F, 0.0F);
        this.BalrogHornL4 = new ModelRenderer(this, 114, 1);
        this.BalrogHornL4.setRotationPoint(4.0F, -0.2F, 0.0F);
        this.BalrogHornL4.addBox(0.0F, -1.5F, -1.6F, 5, 2, 2, 0.0F);
        this.setRotateAngle(BalrogHornL4, 0.0F, 0.3490658503988659F, 0.8726646259971648F);
        this.BalrogHornL4_1 = new ModelRenderer(this, 115, 2);
        this.BalrogHornL4_1.setRotationPoint(4.4F, 0.5F, 0.4F);
        this.BalrogHornL4_1.addBox(0.0F, -1.5F, -1.6F, 5, 1, 1, 0.0F);
        this.setRotateAngle(BalrogHornL4_1, 0.0F, 0.17453292519943295F, 0.0F);
        this.BalrogWingBone4R = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone4R.mirror = true;
        this.BalrogWingBone4R.setRotationPoint(16.2F, -0.2F, 0.0F);
        this.BalrogWingBone4R.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingBone4R, 0.0F, 0.0F, 0.3490658503988659F);
        this.BalrogWingL = new ModelRenderer(this, 38, 26);
        this.BalrogWingL.setRotationPoint(4.5F, -18.0F, 3.0F);
        this.BalrogWingL.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingL, 0.0F, -0.7853981633974483F, -0.7853981633974483F);
        this.BalrogTooth1 = new ModelRenderer(this, 0, 28);
        this.BalrogTooth1.setRotationPoint(2.0F, 2.2F, -4.4F);
        this.BalrogTooth1.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(BalrogTooth1, 0.08726646259971647F, 0.0F, 0.0F);
        this.BalrogLegL = new ModelRenderer(this, 22, 48);
        this.BalrogLegL.mirror = true;
        this.BalrogLegL.setRotationPoint(6.0F, 0.0F, -1.0F);
        this.BalrogLegL.addBox(-1.5F, -3.0F, -3.0F, 4, 8, 8, 0.0F);
        this.BalrogWingSkin1 = new ModelRenderer(this, 0, 0);
        this.BalrogWingSkin1.setRotationPoint(18.5F, -4.3F, 0.0F);
        this.BalrogWingSkin1.addBox(-8.5F, 0.0F, 0.0F, 53, 26, 0, 0.0F);
        this.setRotateAngle(BalrogWingSkin1, 0.0F, 0.0F, 0.7853981633974483F);
        this.BalrogSkull = new ModelRenderer(this, 45, 30);
        this.BalrogSkull.setRotationPoint(0.0F, -6.5F, -1.8F);
        this.BalrogSkull.addBox(-4.5F, -4.5F, -4.5F, 9, 9, 9, 0.0F);
        this.setRotateAngle(BalrogSkull, 0.17453292519943295F, 0.0F, 0.0F);
        this.BalrogHornR1 = new ModelRenderer(this, 114, 0);
        this.BalrogHornR1.setRotationPoint(-3.5F, -2.8F, 0.0F);
        this.BalrogHornR1.addBox(-5.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
        this.setRotateAngle(BalrogHornR1, -0.17453292519943295F, 0.17453292519943295F, 0.5235987755982988F);
        this.BalrogWingBone6L = new ModelRenderer(this, 38, 26);
        this.BalrogWingBone6L.setRotationPoint(16.2F, -0.2F, 0.0F);
        this.BalrogWingBone6L.addBox(0.0F, -1.0F, -1.0F, 21, 2, 2, 0.0F);
        this.setRotateAngle(BalrogWingBone6L, 0.0F, 0.0F, 0.6981317007977318F);
        this.BalrogHornR4 = new ModelRenderer(this, 114, 1);
        this.BalrogHornR4.setRotationPoint(-3.7F, -0.2F, 0.0F);
        this.BalrogHornR4.addBox(-5.0F, -1.5F, -1.6F, 5, 2, 2, 0.0F);
        this.setRotateAngle(BalrogHornR4, 0.0F, -0.3490658503988659F, -0.8726646259971648F);
        this.BalrogLegUpperR = new ModelRenderer(this, 46, 47);
        this.BalrogLegUpperR.setRotationPoint(-0.5F, 1.0F, 1.0F);
        this.BalrogLegUpperR.addBox(-2.5F, 0.0F, -3.0F, 5, 11, 6, 0.0F);
        this.setRotateAngle(BalrogLegUpperR, -0.6981317007977318F, 0.0F, 0.0F);
        this.BalrogHornR4.addChild(this.BalrogHornR4_1);
        this.BalrogWingBone4R.addChild(this.BalrogWingBone5R);
        this.BalrogMouth.addChild(this.BalrogTooth4);
        this.BalrogMouth.addChild(this.BalrogTooth2);
        this.BalrogWingR.addChild(this.BalrogWingSkin2);
        this.BalrogWingL.addChild(this.BalrogWingBone3L);
        this.BalrogLegUpperR.addChild(this.BalrogLegLower1R);
        this.BalrogLegL.addChild(this.BalrogLegUpperL);
        this.bipedRightArm.addChild(this.bipedRightArmUpper);
        this.BalrogLegLower2R.addChild(this.BalrogLegFootR);
        this.BalrogLegLower2L.addChild(this.BalrogLegFootL);
        this.BalrogWingBone4R.addChild(this.BalrogWingBone6R);
        this.BalrogWingL.addChild(this.BalrogWingBone2L);
        this.BalrogHornL2.addChild(this.BalrogHornL3);
        this.BalrogWingBone3L.addChild(this.BalrogWingBone4L);
        this.BalrogHornL1.addChild(this.BalrogHornL2);
        this.BalrogLegLower1R.addChild(this.BalrogLegLower2R);
        this.BalrogWingR.addChild(this.BalrogWingBone2R);
        this.BalrogHornR2.addChild(this.BalrogHornR3);
        this.BalrogLegLower1L.addChild(this.BalrogLegLower2L);
        this.BalrogWingR.addChild(this.BalrogWingBone3R);
        this.BalrogLegUpperL.addChild(this.BalrogLegLower1L);
        this.BalrogWingBone4L.addChild(this.BalrogWingBone5L);
        this.BalrogSkull.addChild(this.BalrogHornL1);
        this.BalrogHornR1.addChild(this.BalrogHornR2);
        this.BalrogBody.addChild(this.BalrogChest);
        this.BalrogSkull.addChild(this.BalrogMouth);
        this.bipedLeftArm.addChild(this.bipedLeftArmUpper);
        this.BalrogMouth.addChild(this.BalrogTooth3);
        this.BalrogHornL3.addChild(this.BalrogHornL4);
        this.BalrogHornL4.addChild(this.BalrogHornL4_1);
        this.BalrogWingBone3R.addChild(this.BalrogWingBone4R);
        this.BalrogMouth.addChild(this.BalrogTooth1);
        this.BalrogWingL.addChild(this.BalrogWingSkin1);
        this.BalrogNeck.addChild(this.BalrogSkull);
        this.BalrogSkull.addChild(this.BalrogHornR1);
        this.BalrogWingBone4L.addChild(this.BalrogWingBone6L);
        this.BalrogHornR3.addChild(this.BalrogHornR4);
        this.BalrogLegR.addChild(this.BalrogLegUpperR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float anim = (entity.ticksExisted + f5) / 20F;

        BalrogWingL.rotateAngleX = (float) (Math.cos(anim) / 4D);
        BalrogWingL.rotateAngleY = -0.2858F + (float) (Math.sin(anim) / 4D);

        BalrogWingR.rotateAngleX = -(float) (Math.cos(anim) / 4D);
        BalrogWingR.rotateAngleY = (-2.8561945f) - (float) (Math.sin(anim) / 4D);

        this.BalrogNeck.render(f5);
        this.BalrogBody.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.BalrogLegR.render(f5);
        this.BalrogLegL.render(f5);
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        this.rightArmPose = ModelBiped.ArmPose.EMPTY;
        this.leftArmPose = ModelBiped.ArmPose.EMPTY;
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);

        if (itemstack.getItem() == Items.BOW && ((EntityBalrog)entitylivingbaseIn).isSwingingArms()) {
            if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT) {
                this.rightArmPose = ModelBiped.ArmPose.ITEM;
            }
            else {
                this.leftArmPose = ModelBiped.ArmPose.ITEM;
            }
        }

        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        this.BalrogLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.BalrogLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.BalrogNeck.rotateAngleY = netHeadYaw * 0.017453292F;
        this.BalrogNeck.rotateAngleX = headPitch * 0.017453292F;

        this.bipedLeftArm.rotationPointX = 8.5F;
        this.bipedRightArm.rotationPointX = -8.0F;
    }
}