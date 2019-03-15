package com.greatorator.tolkienmobs.client.render.model.ambient;

import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import com.greatorator.tolkienmobs.entity.entityai.AIStates;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/* Thrush - GreatOrator */
@SideOnly(Side.CLIENT)
public class ModelThrush extends ModelTTM {
    public ModelRenderer ThrushBody;
    public ModelRenderer ThrushBodyWingless;
    public ModelRenderer ThrushTail1;
    public ModelRenderer ThrushLegL1;
    public ModelRenderer ThrushLegR1;
    public ModelRenderer ThrushHead;
    public ModelRenderer ThrushWingL1;
    public ModelRenderer ThrushWingR1;
    public ModelRenderer ThrushTail2;
    public ModelRenderer ThrushTail3;
    public ModelRenderer ThrushLegL2;
    public ModelRenderer ThrushLegR2;
    public ModelRenderer ThrushBeakTop;
    public ModelRenderer ThrushBeakBottom;
    public ModelRenderer ThrushWingL2;
    public ModelRenderer ThrushWingR2;

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

    public ModelThrush() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.ThrushTail3 = new ModelRenderer(this, -3, 9);
        this.ThrushTail3.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.ThrushTail3.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(ThrushTail3, 0.0F, -0.2665117767795341F, 0.0F);
        this.ThrushLegL2 = new ModelRenderer(this, 0, 5);
        this.ThrushLegL2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.ThrushLegL2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(ThrushLegL2, -1.3613568165555772F, 0.0F, 0.0F);
        this.ThrushLegL1 = new ModelRenderer(this, 0, 3);
        this.ThrushLegL1.setRotationPoint(1.3F, 1.5F, 0.0F);
        this.ThrushLegL1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(ThrushLegL1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushWingL2 = new ModelRenderer(this, 2, 9);
        this.ThrushWingL2.setRotationPoint(5.0F, -1.0F, -1.5F);
        this.ThrushWingL2.addBox(0.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushHead = new ModelRenderer(this, 12, 0);
        this.ThrushHead.setRotationPoint(0.5F, -0.1F, -3.0F);
        this.ThrushHead.addBox(-1.0F, -1.5F, -2.5F, 2, 2, 3, 0.0F);
        this.setRotateAngle(ThrushHead, 0.5235987755982988F, 0.0F, 0.0F);
        this.ThrushLegR1 = new ModelRenderer(this, 0, 3);
        this.ThrushLegR1.setRotationPoint(-0.3F, 1.5F, 0.0F);
        this.ThrushLegR1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(ThrushLegR1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushWingL1 = new ModelRenderer(this, 0, 9);
        this.ThrushWingL1.setRotationPoint(1.0F, 1.0F, 1.3F);
        this.ThrushWingL1.addBox(1.0F, -1.0F, -3.0F, 4, 0, 3, 0.0F);
        this.ThrushWingR1 = new ModelRenderer(this, 0, 9);
        this.ThrushWingR1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.ThrushWingR1.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushBody = new ModelRenderer(this, 0, 0);
        this.ThrushBody.setRotationPoint(0.0F, 20.3F, 0.5F);
        this.ThrushBody.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(ThrushBody, -0.5235987755982988F, 0.0F, 0.0F);
        this.ThrushTail1 = new ModelRenderer(this, -4, 9);
        this.ThrushTail1.setRotationPoint(0.0F, -0.8F, 4.3F);
        this.ThrushTail1.addBox(-0.5F, -0.5F, -1.5F, 2, 0, 4, 0.0F);
        this.setRotateAngle(ThrushTail1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushBeakTop = new ModelRenderer(this, 0, 0);
        this.ThrushBeakTop.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.ThrushBeakTop.addBox(-0.5F, -0.8F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ThrushBeakTop, 0.08726646259971647F, 0.0F, 0.0F);
        this.ThrushBeakBottom = new ModelRenderer(this, 20, 3);
        this.ThrushBeakBottom.setRotationPoint(0.0F, 0.4F, 0.0F);
        this.ThrushBeakBottom.addBox(-0.5F, -0.9F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ThrushBeakBottom, -0.08726646259971647F, 0.0F, 0.0F);
        this.ThrushTail2 = new ModelRenderer(this, -3, 9);
        this.ThrushTail2.setRotationPoint(1.0F, 0.0F, -1.5F);
        this.ThrushTail2.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(ThrushTail2, 0.0F, 0.2617993877991494F, 0.0F);
        this.ThrushWingR2 = new ModelRenderer(this, 2, 9);
        this.ThrushWingR2.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.ThrushWingR2.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushLegR2 = new ModelRenderer(this, 0, 5);
        this.ThrushLegR2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.ThrushLegR2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(ThrushLegR2, -1.3613568165555772F, 0.0F, 0.0F);
        this.ThrushTail1.addChild(this.ThrushTail3);
        this.ThrushLegL1.addChild(this.ThrushLegL2);
        this.ThrushBody.addChild(this.ThrushLegL1);
        this.ThrushWingL1.addChild(this.ThrushWingL2);
        this.ThrushBody.addChild(this.ThrushHead);
        this.ThrushBody.addChild(this.ThrushLegR1);
        this.ThrushBody.addChild(this.ThrushWingL1);
        this.ThrushBody.addChild(this.ThrushWingR1);
        this.ThrushBody.addChild(this.ThrushTail1);
        this.ThrushHead.addChild(this.ThrushBeakTop);
        this.ThrushBeakTop.addChild(this.ThrushBeakBottom);
        this.ThrushTail1.addChild(this.ThrushTail2);
        this.ThrushWingR1.addChild(this.ThrushWingR2);
        this.ThrushLegR1.addChild(this.ThrushLegR2);

        this.ThrushBodyWingless = new ModelRenderer(this, 0, 0);
        this.ThrushBodyWingless.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.ThrushBodyWingless.setRotationPoint(0.0F, 20.1F, 0.5F);
        this.ThrushBodyWingless.setTextureSize(textureWidth, textureHeight);
        this.ThrushBodyWingless.mirror = true;
        this.setRotateAngle(ThrushBodyWingless, -0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushBodyWingless.addChild(ThrushHead);
        this.ThrushBodyWingless.addChild(ThrushLegL1);
        this.ThrushBodyWingless.addChild(ThrushLegR1);
        this.ThrushBodyWingless.addChild(ThrushTail1);
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
        { ThrushBodyWingless.render(parRenderFloat);
        }
        else
        {
            ThrushBody.render(parRenderFloat);
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
            doAnimate(parEntity, divingCycle);
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
        setRotation(ThrushBody, parCycleArray[cycleIndex][0], 0, 0);
        setRotation(ThrushBodyWingless, parCycleArray[cycleIndex][0], 0, 0);
        setRotation(ThrushHead, parCycleArray[cycleIndex][1], 0, 0);
        // both legs have same angle
        setRotation(ThrushLegL1, parCycleArray[cycleIndex][2], 0, 0);
        setRotation(ThrushLegR1, parCycleArray[cycleIndex][2], 0, 0);
        setRotation(ThrushTail1, parCycleArray[cycleIndex][3], 0, 0);
        // both legs have same (well negative) angle
        setRotation(ThrushWingL1, parCycleArray[cycleIndex][4], parCycleArray[cycleIndex][5], parCycleArray[cycleIndex][6]);
        setRotation(ThrushWingR1, parCycleArray[cycleIndex][4], -parCycleArray[cycleIndex][5], -parCycleArray[cycleIndex][6]);
        setRotation(ThrushWingL2, 0, -21F, -parCycleArray[cycleIndex][7]);
        setRotation(ThrushWingR2, 0, 21F, parCycleArray[cycleIndex][7]);
    }
}