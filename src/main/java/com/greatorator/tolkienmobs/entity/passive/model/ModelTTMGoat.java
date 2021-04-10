package com.greatorator.tolkienmobs.entity.passive.model;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.entity.passive.EntityTTMGoat;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Goat - GreatOrator
 */
public class ModelTTMGoat<T extends AbstractHorseEntity> extends AgeableModel<EntityTTMGoat> {
        public ModelRenderer GoatLegRight;
        public ModelRenderer GoatLegRearRight;
        public ModelRenderer GoatLegRearLeft;
        public ModelRenderer GoatLegLeft;
        public ModelRenderer GoatBody;
        public ModelRenderer GoatNeck;
        public ModelRenderer Saddle;
        public ModelRenderer Reign;
        public ModelRenderer GoatLeftChest;
        public ModelRenderer GoatRightChest;
        public ModelRenderer GoatCalfRight;
        public ModelRenderer GoatHoofRight;
        public ModelRenderer GoatLegHair;
        public ModelRenderer GoatCalfRearRight;
        public ModelRenderer GoatHoofRearRight;
        public ModelRenderer GoatLegHair_1;
        public ModelRenderer GoatCalfRearLeft;
        public ModelRenderer GoatHoofRearLeft;
        public ModelRenderer GoatLegHair_2;
        public ModelRenderer GoatCalfLeft;
        public ModelRenderer GoatHoofLeft;
        public ModelRenderer GoatLegHair_3;
        public ModelRenderer GoatTail;
        public ModelRenderer GoatBodyHair;
        public ModelRenderer head;
        public ModelRenderer upperMouth;
        public ModelRenderer lowerMouth;
        public ModelRenderer GoatHornRight;
        public ModelRenderer GoatHornLeft;
        public ModelRenderer GoatHornRight_1;
        public ModelRenderer GoatHornRight_2;
        public ModelRenderer GoatHornRight_3;
        public ModelRenderer GoatHornRight_4;
        public ModelRenderer GoatHornRight_5;
        public ModelRenderer GoatHornRight_6;
        public ModelRenderer GoatHornRight_7;
        public ModelRenderer GoatHornLeft_1;
        public ModelRenderer GoatHornLeft_2;
        public ModelRenderer GoatHornLeft_3;
        public ModelRenderer GoatHornLeft_4;
        public ModelRenderer GoatHornLeft_5;
        public ModelRenderer GoatHornLeft_6;
        public ModelRenderer GoatHornLeft_7;
        public ModelRenderer Saddle_1;
        public ModelRenderer Saddle_2;
        public ModelRenderer Saddle_3;
        public ModelRenderer Saddle_4;
        public ModelRenderer Saddle_5;
        public ModelRenderer Saddle_6;
        public ModelRenderer Reign_1;
        public ModelRenderer Reign_2;
        public ModelRenderer Reign_3;

        public ModelTTMGoat() {
                this.textureWidth = 128;
                this.textureHeight = 128;
                this.Saddle_3 = new ModelRenderer(this, 15, 54);
                this.Saddle_3.setRotationPoint(-5.0F, 6.5F, 0.0F);
                this.Saddle_3.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
                this.GoatBodyHair = new ModelRenderer(this, 0, 37);
                this.GoatBodyHair.setRotationPoint(0.5F, 9.0F, 13.5F);
                this.GoatBodyHair.addBox(-4.5F, 0.0F, -13.0F, 8, 4, 25, 0.0F);
                this.Saddle_1 = new ModelRenderer(this, 0, 48);
                this.Saddle_1.setRotationPoint(0.0F, 0.5F, -0.2F);
                this.Saddle_1.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2, 0.0F);
                this.GoatHornLeft_1 = new ModelRenderer(this, 14, 32);
                this.GoatHornLeft_1.setRotationPoint(-0.7F, -0.9F, 0.0F);
                this.GoatHornLeft_1.addBox(-4.5F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornLeft_1, 0.0F, 0.0F, -0.7853981633974483F);
                this.GoatLegHair_2 = new ModelRenderer(this, 48, 12);
                this.GoatLegHair_2.setRotationPoint(-1.5F, 8.0F, 0.5F);
                this.GoatLegHair_2.addBox(2.0F, -1.0F, -2.0F, 3, 3, 3, 0.0F);
                this.GoatHoofLeft = new ModelRenderer(this, 90, 12);
                this.GoatHoofLeft.setRotationPoint(-2.0F, 13.0F, 0.0F);
                this.GoatHoofLeft.addBox(2.5F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
                this.GoatCalfRearLeft = new ModelRenderer(this, 28, 12);
                this.GoatCalfRearLeft.setRotationPoint(-1.5F, 8.0F, 0.0F);
                this.GoatCalfRearLeft.addBox(2.5F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
                this.GoatRightChest = new ModelRenderer(this, 50, 47);
                this.GoatRightChest.setRotationPoint(-7.5F, 3.0F, 8.0F);
                this.GoatRightChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, 0.0F);
                this.setRotateAngle(GoatRightChest, 0.0F, 1.5707963267948966F, 0.0F);
                this.GoatHornLeft_7 = new ModelRenderer(this, 0, 17);
                this.GoatHornLeft_7.setRotationPoint(-4.4F, 0.5F, 0.7F);
                this.GoatHornLeft_7.addBox(-5.0F, -1.0F, -1.0F, 5, 1, 1, 0.0F);
                this.setRotateAngle(GoatHornLeft_7, 0.0F, 0.7853981633974483F, 0.0F);
                this.Reign_2 = new ModelRenderer(this, 0, 91);
                this.Reign_2.setRotationPoint(0.0F, 0.0F, 0.0F);
                this.Reign_2.addBox(-4.5F, -8.0F, -4.0F, 1, 2, 2, 0.0F);
                this.setRotateAngle(Reign_2, 0.5235987755982988F, 0.0F, 0.0F);
                this.GoatLegRight = new ModelRenderer(this, 0, 0);
                this.GoatLegRight.setRotationPoint(-1.0F, 9.0F, -10.0F);
                this.GoatLegRight.addBox(-4.0F, -1.0F, -2.0F, 4, 8, 4, 0.0F);
                this.Saddle = new ModelRenderer(this, 0, 73);
                this.Saddle.setRotationPoint(0.0F, 2.0F, -1.0F);
                this.Saddle.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8, 0.0F);
                this.GoatHornRight_4 = new ModelRenderer(this, 108, 30);
                this.GoatHornRight_4.setRotationPoint(-4.4F, 0.0F, 0.2F);
                this.GoatHornRight_4.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornRight_4, 0.0F, -0.7853981633974483F, 0.0F);
                this.GoatHornLeft_4 = new ModelRenderer(this, 44, 37);
                this.GoatHornLeft_4.setRotationPoint(-4.4F, 0.0F, -0.2F);
                this.GoatHornLeft_4.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornLeft_4, 0.0F, 0.7853981633974483F, 0.0F);
                this.GoatLegLeft = new ModelRenderer(this, 48, 0);
                this.GoatLegLeft.setRotationPoint(1.0F, 9.0F, -10.0F);
                this.GoatLegLeft.addBox(0.0F, -1.0F, -2.0F, 4, 8, 4, 0.0F);
                this.GoatHoofRearRight = new ModelRenderer(this, 0, 12);
                this.GoatHoofRearRight.mirror = true;
                this.GoatHoofRearRight.setRotationPoint(-2.0F, 13.0F, 0.0F);
                this.GoatHoofRearRight.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
                this.GoatHornRight_7 = new ModelRenderer(this, 112, 12);
                this.GoatHornRight_7.setRotationPoint(-4.4F, 0.5F, 0.7F);
                this.GoatHornRight_7.addBox(-5.0F, -1.0F, -1.0F, 5, 1, 1, 0.0F);
                this.setRotateAngle(GoatHornRight_7, 0.0F, -0.7853981633974483F, 0.0F);
                this.GoatHoofRearLeft = new ModelRenderer(this, 36, 12);
                this.GoatHoofRearLeft.setRotationPoint(-2.0F, 13.0F, 0.0F);
                this.GoatHoofRearLeft.addBox(2.5F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
                this.lowerMouth = new ModelRenderer(this, 108, 21);
                this.lowerMouth.setRotationPoint(0.0F, -8.0F, -2.6F);
                this.lowerMouth.addBox(-2.5F, -3.0F, -1.0F, 5, 8, 1, 0.0F);
                this.Saddle_5 = new ModelRenderer(this, 0, 54);
                this.Saddle_5.setRotationPoint(5.0F, 0.5F, 0.0F);
                this.Saddle_5.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
                this.GoatHornRight_2 = new ModelRenderer(this, 82, 20);
                this.GoatHornRight_2.setRotationPoint(-3.7F, -0.2F, 0.0F);
                this.GoatHornRight_2.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornRight_2, 0.0F, 0.0F, -0.7853981633974483F);
                this.Saddle_2 = new ModelRenderer(this, 0, 42);
                this.Saddle_2.setRotationPoint(0.0F, 0.5F, -1.0F);
                this.Saddle_2.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2, 0.0F);
                this.upperMouth = new ModelRenderer(this, 33, 17);
                this.upperMouth.setRotationPoint(0.0F, -8.5F, -2.0F);
                this.upperMouth.addBox(-2.5F, -3.0F, -1.0F, 5, 3, 3, 0.0F);
                this.Reign = new ModelRenderer(this, 0, 68);
                this.Reign.setRotationPoint(0.0F, 5.0F, -10.0F);
                this.Reign.addBox(-4.1F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
                this.GoatHornLeft_6 = new ModelRenderer(this, 58, 37);
                this.GoatHornLeft_6.setRotationPoint(-4.4F, 0.0F, -0.2F);
                this.GoatHornLeft_6.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornLeft_6, 0.0F, 0.7853981633974483F, 0.0F);
                this.GoatTail = new ModelRenderer(this, 98, 15);
                this.GoatTail.setRotationPoint(0.0F, 2.5F, 25.0F);
                this.GoatTail.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 4, 0.0F);
                this.setRotateAngle(GoatTail, -0.7853981633974483F, 0.0F, 0.0F);
                this.GoatHornLeft_5 = new ModelRenderer(this, 58, 37);
                this.GoatHornLeft_5.setRotationPoint(-4.4F, 0.0F, -0.2F);
                this.GoatHornLeft_5.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornLeft_5, 0.0F, 0.7853981633974483F, 0.0F);
                this.GoatHornLeft = new ModelRenderer(this, 28, 0);
                this.GoatHornLeft.setRotationPoint(2.5F, -4.5F, 2.0F);
                this.GoatHornLeft.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornLeft, -1.5707963267948966F, 0.7853981633974483F, 3.141592653589793F);
                this.GoatLegRearLeft = new ModelRenderer(this, 32, 0);
                this.GoatLegRearLeft.setRotationPoint(1.0F, 9.0F, 10.0F);
                this.GoatLegRearLeft.addBox(0.0F, -1.0F, -2.0F, 4, 8, 4, 0.0F);
                this.Saddle_4 = new ModelRenderer(this, 0, 54);
                this.Saddle_4.setRotationPoint(-5.0F, 0.5F, 0.0F);
                this.Saddle_4.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
                this.GoatHornLeft_3 = new ModelRenderer(this, 0, 36);
                this.GoatHornLeft_3.setRotationPoint(-4.4F, 0.0F, -0.2F);
                this.GoatHornLeft_3.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornLeft_3, 0.0F, 0.7853981633974483F, 0.0F);
                this.GoatLegHair_3 = new ModelRenderer(this, 108, 14);
                this.GoatLegHair_3.setRotationPoint(-1.5F, 8.0F, 0.5F);
                this.GoatLegHair_3.addBox(2.0F, -1.0F, -2.0F, 3, 3, 3, 0.0F);
                this.GoatHoofRight = new ModelRenderer(this, 114, 0);
                this.GoatHoofRight.mirror = true;
                this.GoatHoofRight.setRotationPoint(-2.0F, 13.0F, 0.0F);
                this.GoatHoofRight.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
                this.GoatHornLeft_2 = new ModelRenderer(this, 108, 34);
                this.GoatHornLeft_2.setRotationPoint(-3.8F, -0.3F, 0.0F);
                this.GoatHornLeft_2.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornLeft_2, 0.0F, 0.0F, -0.7853981633974483F);
                this.GoatNeck = new ModelRenderer(this, 82, 0);
                this.GoatNeck.setRotationPoint(0.0F, 5.5F, -11.0F);
                this.GoatNeck.addBox(-3.5F, -7.0F, -2.5F, 7, 7, 5, 0.0F);
                this.setRotateAngle(GoatNeck, 0.7853981633974483F, 0.0F, 0.0F);
                this.GoatBody = new ModelRenderer(this, 38, 0);
                this.GoatBody.setRotationPoint(0.0F, 3.0F, -13.5F);
                this.GoatBody.addBox(-4.5F, 0.0F, 0.0F, 9, 11, 26, 0.0F);
                this.GoatLegRearRight = new ModelRenderer(this, 16, 0);
                this.GoatLegRearRight.setRotationPoint(-1.0F, 9.0F, 10.0F);
                this.GoatLegRearRight.addBox(-4.0F, -1.0F, -2.0F, 4, 8, 4, 0.0F);
                this.GoatHornRight_6 = new ModelRenderer(this, 0, 32);
                this.GoatHornRight_6.setRotationPoint(-4.4F, 0.0F, 0.2F);
                this.GoatHornRight_6.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornRight_6, 0.0F, -0.7853981633974483F, 0.0F);
                this.GoatHornRight = new ModelRenderer(this, 12, 0);
                this.GoatHornRight.setRotationPoint(-2.5F, -4.5F, 2.0F);
                this.GoatHornRight.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornRight, -1.5707963267948966F, 0.7853981633974483F, 0.0F);
                this.GoatCalfRight = new ModelRenderer(this, 106, 0);
                this.GoatCalfRight.setRotationPoint(-1.5F, 8.0F, 0.0F);
                this.GoatCalfRight.addBox(-1.5F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
                this.GoatLeftChest = new ModelRenderer(this, 50, 47);
                this.GoatLeftChest.mirror = true;
                this.GoatLeftChest.setRotationPoint(4.5F, 3.0F, 8.0F);
                this.GoatLeftChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, 0.0F);
                this.setRotateAngle(GoatLeftChest, 0.0F, 1.5707963267948966F, 0.0F);
                this.GoatLegHair = new ModelRenderer(this, 110, 5);
                this.GoatLegHair.mirror = true;
                this.GoatLegHair.setRotationPoint(-1.5F, 8.0F, 0.5F);
                this.GoatLegHair.addBox(-2.0F, -1.0F, -2.0F, 3, 3, 3, 0.0F);
                this.Reign_1 = new ModelRenderer(this, 0, 68);
                this.Reign_1.setRotationPoint(0.0F, 0.0F, 0.0F);
                this.Reign_1.addBox(4.1F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
                this.GoatHornRight_1 = new ModelRenderer(this, 49, 19);
                this.GoatHornRight_1.setRotationPoint(-0.7F, -0.9F, 0.0F);
                this.GoatHornRight_1.addBox(-4.5F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornRight_1, 0.0F, 0.0F, -0.7853981633974483F);
                this.head = new ModelRenderer(this, 0, 19);
                this.head.setRotationPoint(0.0F, -4.0F, 3.0F);
                this.head.addBox(-4.0F, -9.0F, -3.0F, 8, 7, 6, 0.0F);
                this.setRotateAngle(head, 0.7853981633974483F, 0.0F, 0.0F);
                this.Saddle_6 = new ModelRenderer(this, 15, 54);
                this.Saddle_6.setRotationPoint(5.0F, 6.5F, 0.0F);
                this.Saddle_6.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
                this.GoatCalfLeft = new ModelRenderer(this, 82, 12);
                this.GoatCalfLeft.setRotationPoint(-1.5F, 8.0F, 0.0F);
                this.GoatCalfLeft.addBox(2.5F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
                this.GoatHornRight_5 = new ModelRenderer(this, 0, 32);
                this.GoatHornRight_5.setRotationPoint(-4.4F, 0.0F, 0.2F);
                this.GoatHornRight_5.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornRight_5, 0.0F, -0.7853981633974483F, 0.0F);
                this.GoatCalfRearRight = new ModelRenderer(this, 104, 10);
                this.GoatCalfRearRight.setRotationPoint(-1.5F, 8.0F, 0.0F);
                this.GoatCalfRearRight.addBox(-1.5F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
                this.Reign_3 = new ModelRenderer(this, 0, 91);
                this.Reign_3.setRotationPoint(0.0F, 0.0F, 0.0F);
                this.Reign_3.addBox(3.4F, -8.0F, -4.0F, 1, 2, 2, 0.0F);
                this.setRotateAngle(Reign_3, 0.5235987755982988F, 0.0F, 0.0F);
                this.GoatLegHair_1 = new ModelRenderer(this, 12, 12);
                this.GoatLegHair_1.mirror = true;
                this.GoatLegHair_1.setRotationPoint(-1.5F, 8.0F, 0.5F);
                this.GoatLegHair_1.addBox(-2.0F, -1.0F, -2.0F, 3, 3, 3, 0.0F);
                this.GoatHornRight_3 = new ModelRenderer(this, 94, 22);
                this.GoatHornRight_3.setRotationPoint(-4.4F, 0.0F, 0.2F);
                this.GoatHornRight_3.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
                this.setRotateAngle(GoatHornRight_3, 0.0F, -0.7853981633974483F, 0.0F);
                this.Saddle.addChild(this.Saddle_3);
                this.GoatBody.addChild(this.GoatBodyHair);
                this.Saddle.addChild(this.Saddle_1);
                this.GoatHornLeft.addChild(this.GoatHornLeft_1);
                this.GoatLegRearLeft.addChild(this.GoatLegHair_2);
                this.GoatLegLeft.addChild(this.GoatHoofLeft);
                this.GoatLegRearLeft.addChild(this.GoatCalfRearLeft);
                this.GoatHornLeft_6.addChild(this.GoatHornLeft_7);
                this.Reign.addChild(this.Reign_2);
                this.GoatHornRight_3.addChild(this.GoatHornRight_4);
                this.GoatHornLeft_3.addChild(this.GoatHornLeft_4);
                this.GoatLegRearRight.addChild(this.GoatHoofRearRight);
                this.GoatHornRight_6.addChild(this.GoatHornRight_7);
                this.GoatLegRearLeft.addChild(this.GoatHoofRearLeft);
                this.head.addChild(this.lowerMouth);
                this.Saddle.addChild(this.Saddle_5);
                this.GoatHornRight_1.addChild(this.GoatHornRight_2);
                this.Saddle.addChild(this.Saddle_2);
                this.head.addChild(this.upperMouth);
                this.GoatHornLeft_5.addChild(this.GoatHornLeft_6);
                this.GoatBody.addChild(this.GoatTail);
                this.GoatHornLeft_4.addChild(this.GoatHornLeft_5);
                this.head.addChild(this.GoatHornLeft);
                this.Saddle.addChild(this.Saddle_4);
                this.GoatHornLeft_2.addChild(this.GoatHornLeft_3);
                this.GoatLegLeft.addChild(this.GoatLegHair_3);
                this.GoatLegRight.addChild(this.GoatHoofRight);
                this.GoatHornLeft_1.addChild(this.GoatHornLeft_2);
                this.GoatHornRight_5.addChild(this.GoatHornRight_6);
                this.head.addChild(this.GoatHornRight);
                this.GoatLegRight.addChild(this.GoatCalfRight);
                this.GoatLegRight.addChild(this.GoatLegHair);
                this.Reign.addChild(this.Reign_1);
                this.GoatHornRight.addChild(this.GoatHornRight_1);
                this.GoatNeck.addChild(this.head);
                this.Saddle.addChild(this.Saddle_6);
                this.GoatLegLeft.addChild(this.GoatCalfLeft);
                this.GoatHornRight_4.addChild(this.GoatHornRight_5);
                this.GoatLegRearRight.addChild(this.GoatCalfRearRight);
                this.Reign.addChild(this.Reign_3);
                this.GoatLegRearRight.addChild(this.GoatLegHair_1);
                this.GoatHornRight_2.addChild(this.GoatHornRight_3);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.GoatNeck);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.GoatBody, this.GoatLegRight, this.GoatLegLeft, this.GoatLegRearRight, this.GoatLegRearLeft, this.GoatLeftChest, this.GoatRightChest, this.GoatRightChest);
    }

    public void setRotationAngles(EntityTTMGoat entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float baseHeadRotation = 0.7853981633974483F; // needs to be the original value of GoatNeck.rotateAngleX
            if (entityIn.isHorseSaddled()){
                    ModelRenderer modelrenderer = this.Saddle;
                    modelrenderer.showModel = true;
            }

            if (entityIn.isBeingRidden()) {
                    ModelRenderer modelrenderer = this.Reign;
                    modelrenderer.showModel = true;
            }
            if (entityIn.hasChest()) {
                    ModelRenderer chestRenderRight = this.GoatRightChest;
                    ModelRenderer chestRenderLeft = this.GoatLeftChest;
                    chestRenderRight.showModel = true;
                    chestRenderLeft.showModel = true;
            } else {
                    ModelRenderer chestRenderRight = this.GoatRightChest;
                    ModelRenderer chestRenderLeft = this.GoatLeftChest;
                    chestRenderRight.showModel = false;
                    chestRenderLeft.showModel = false;
            }

        this.GoatLegRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.GoatLegRearLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.GoatLegLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.GoatLegRearRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.GoatNeck.rotateAngleY = netHeadYaw * 0.017453292F;
        this.GoatNeck.rotateAngleX = baseHeadRotation + (headPitch * 0.017453292F);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}