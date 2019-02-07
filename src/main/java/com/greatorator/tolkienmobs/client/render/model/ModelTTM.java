package com.greatorator.tolkienmobs.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;

/** Borrowed from Jabelar https://github.com/jabelar */
public class ModelTTM extends ModelBiped
{
    protected double distanceMovedTotal = 0.0D;

    protected static final double CYCLES_PER_BLOCK = 3.0D; 
    protected int cycleIndex = 0;

	protected void updateDistanceMovedTotal(Entity parEntity) 
    {
        distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, 
              parEntity.prevPosZ);
    }

    protected double getDistanceMovedTotal(Entity parEntity) 
    {
        return (distanceMovedTotal);
    }

    protected float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
    
    protected void setRotation(ModelRenderer model, float rotX, float rotY, float rotZ)
    {
        model.rotateAngleX = degToRad(rotX);
        model.rotateAngleY = degToRad(rotY);
        model.rotateAngleZ = degToRad(rotZ);        
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void postRenderArm(float scale, EnumHandSide side)
    {
        float f = side == EnumHandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArmForSide(side);
        modelrenderer.rotationPointX += f;
        modelrenderer.postRender(scale);
        modelrenderer.rotationPointX -= f;
    }

    protected void spinX(ModelRenderer model)
    {
        model.rotateAngleX += degToRad(0.5F);
    }

    protected void spinY(ModelRenderer model)
    {
        model.rotateAngleY += degToRad(0.5F);
    }

    protected void spinZ(ModelRenderer model)
    {
        model.rotateAngleZ += degToRad(0.5F);
    }
    
    protected void convertToChildOf(ModelRenderer parChild, ModelRenderer parParent)
    {
    	// move child rotation point to be relative to parent
    	parChild.rotationPointX -= parParent.rotationPointX;
    	parChild.rotationPointY -= parParent.rotationPointY;
    	parChild.rotationPointZ -= parParent.rotationPointZ;
    	// make rotations relative to parent
    	parChild.rotateAngleX -= parParent.rotateAngleX;
    	parChild.rotateAngleY -= parParent.rotateAngleY;
    	parChild.rotateAngleZ -= parParent.rotateAngleZ;
    	// create relationship
    	parParent.addChild(parChild);
    }
}