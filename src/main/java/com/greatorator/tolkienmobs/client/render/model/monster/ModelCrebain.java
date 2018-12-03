package com.greatorator.tolkienmobs.client.render.model.monster;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityBirds;
import com.greatorator.tolkienmobs.entity.monster.EntityCrebain;
import com.greatorator.tolkienmobs.entity.entityai.AIStates;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Crebain - GreatOrator
 */
/**
 * Borrowed from Jabelar https://github.com/jabelar
 */
@SideOnly(Side.CLIENT)
public class ModelCrebain extends ModelTolkienMobs {
    public ModelRenderer CrebainBody;
    public ModelRenderer CrebainBodyWingless;
    public ModelRenderer CrebainTail1;
    public ModelRenderer CrebainLegL1;
    public ModelRenderer CrebainLegR1;
    public ModelRenderer CrebainHead;
    public ModelRenderer CrebainWingL1;
    public ModelRenderer CrebainWingR1;
    public ModelRenderer CrebainTail2;
    public ModelRenderer CrebainTail3;
    public ModelRenderer CrebainLegL2;
    public ModelRenderer CrebainLegR2;
    public ModelRenderer CrebainBeakTop;
    public ModelRenderer CrebainBeakBottom;
    public ModelRenderer CrebainWingL2;
    public ModelRenderer CrebainWingR2;

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

    public ModelCrebain() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.CrebainWingL2 = new ModelRenderer(this, 2, 9);
        this.CrebainWingL2.setRotationPoint(5.0F, -1.0F, -1.5F);
        this.CrebainWingL2.addBox(0.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.CrebainWingR1 = new ModelRenderer(this, 0, 9);
        this.CrebainWingR1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.CrebainWingR1.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.CrebainTail3 = new ModelRenderer(this, -3, 9);
        this.CrebainTail3.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.CrebainTail3.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(CrebainTail3, 0.0F, -0.2665117767795341F, 0.0F);
        this.CrebainBody = new ModelRenderer(this, 0, 0);
        this.CrebainBody.setRotationPoint(0.0F, 20.1F, 0.5F);
        this.CrebainBody.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(CrebainBody, -0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainWingL1 = new ModelRenderer(this, 0, 9);
        this.CrebainWingL1.setRotationPoint(1.0F, 1.0F, 1.3F);
        this.CrebainWingL1.addBox(1.0F, -1.0F, -3.0F, 4, 0, 3, 0.0F);
        this.CrebainBeakBottom = new ModelRenderer(this, 0, 0);
        this.CrebainBeakBottom.setRotationPoint(0.0F, 0.4F, 0.0F);
        this.CrebainBeakBottom.addBox(-0.5F, -0.9F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(CrebainBeakBottom, -0.08726646259971647F, 0.0F, 0.0F);
        this.CrebainWingR2 = new ModelRenderer(this, 2, 9);
        this.CrebainWingR2.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.CrebainWingR2.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.CrebainLegL1 = new ModelRenderer(this, 0, 3);
        this.CrebainLegL1.setRotationPoint(1.3F, 1.5F, 0.0F);
        this.CrebainLegL1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.CrebainLegR2 = new ModelRenderer(this, 0, 5);
        this.CrebainLegR2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.CrebainLegR2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(CrebainLegR2, -1.3613568165555772F, 0.0F, 0.0F);
        this.CrebainLegR1 = new ModelRenderer(this, 0, 3);
        this.CrebainLegR1.setRotationPoint(-0.3F, 1.5F, 0.0F);
        this.CrebainLegR1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.CrebainTail1 = new ModelRenderer(this, -4, 9);
        this.CrebainTail1.setRotationPoint(0.0F, -0.8F, 4.3F);
        this.CrebainTail1.addBox(-0.5F, -0.5F, -1.5F, 2, 0, 4, 0.0F);
        this.setRotateAngle(CrebainTail1, 0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainTail2 = new ModelRenderer(this, -3, 9);
        this.CrebainTail2.setRotationPoint(1.0F, 0.0F, -1.5F);
        this.CrebainTail2.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(CrebainTail2, 0.0F, 0.2617993877991494F, 0.0F);
        this.CrebainHead = new ModelRenderer(this, 12, 0);
        this.CrebainHead.setRotationPoint(0.5F, -0.1F, -3.0F);
        this.CrebainHead.addBox(-1.0F, -1.5F, -2.5F, 2, 2, 3, 0.0F);
        this.setRotateAngle(CrebainHead, 0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainLegL2 = new ModelRenderer(this, 0, 5);
        this.CrebainLegL2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.CrebainLegL2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(CrebainLegL2, -1.3613568165555772F, 0.0F, 0.0F);
        this.CrebainBeakTop = new ModelRenderer(this, 0, 0);
        this.CrebainBeakTop.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.CrebainBeakTop.addBox(-0.5F, -0.8F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(CrebainBeakTop, 0.08726646259971647F, 0.0F, 0.0F);
        this.CrebainWingL1.addChild(this.CrebainWingL2);
        this.CrebainBody.addChild(this.CrebainWingR1);
        this.CrebainTail1.addChild(this.CrebainTail3);
        this.CrebainBody.addChild(this.CrebainWingL1);
        this.CrebainBeakTop.addChild(this.CrebainBeakBottom);
        this.CrebainWingR1.addChild(this.CrebainWingR2);
        this.CrebainBody.addChild(this.CrebainLegL1);
        this.CrebainLegR1.addChild(this.CrebainLegR2);
        this.CrebainBody.addChild(this.CrebainLegR1);
        this.CrebainBody.addChild(this.CrebainTail1);
        this.CrebainTail1.addChild(this.CrebainTail2);
        this.CrebainBody.addChild(this.CrebainHead);
        this.CrebainLegL1.addChild(this.CrebainLegL2);
        this.CrebainHead.addChild(this.CrebainBeakTop);

        this.CrebainBodyWingless = new ModelRenderer(this, 0, 0);
        this.CrebainBodyWingless.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.CrebainBodyWingless.setRotationPoint(0.0F, 20.1F, 0.5F);
        this.CrebainBodyWingless.setTextureSize(textureWidth, textureHeight);
        this.CrebainBodyWingless.mirror = true;
        this.setRotateAngle(CrebainBodyWingless, -0.2617993877991494F, 0.0F, 0.0F);
        this.CrebainBodyWingless.addChild(CrebainHead);
        this.CrebainBodyWingless.addChild(CrebainLegL1);
        this.CrebainBodyWingless.addChild(CrebainLegR1);
        this.CrebainBodyWingless.addChild(CrebainTail1);
    }

    @Override
    public void render(Entity parEntity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        // best to cast to actual expected entity, to allow access to custom fields related to animation
        renderBirds((EntityBirds) parEntity, f5);
    }

    public void renderBirds(EntityBirds parBird, float parRenderFloat)
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
        { CrebainBodyWingless.render(parRenderFloat);
        }
        else
        {
            CrebainBody.render(parRenderFloat);
        }

        // don't forget to pop the matrix for overall scaling
        GL11.glPopMatrix();
    }


    /**
     * Sets the rotation angles.
     *
     * @param parEntity the new rotation angles
     */
    public void setRotationAngles(EntityBirds parEntity)
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

    public void doAnimate(EntityBirds parEntity, float[][] parCycleArray)
    {
        cycleIndex = (int)Math.floor((parEntity.ticksExisted+parEntity.getRandFactor()*2)%parCycleArray.length)/2;
        // will need to set based on entity state
        // bodyAngleX, headAngleX, legsAngleX, tailAngleX, wing1AngleX, wing1AngleZ, wing2AngleZ
        setRotation(CrebainBody, parCycleArray[cycleIndex][0], 0, 0);
        setRotation(CrebainBodyWingless, parCycleArray[cycleIndex][0], 0, 0);
        setRotation(CrebainHead, parCycleArray[cycleIndex][1], 0, 0);
        // both legs have same angle
        setRotation(CrebainLegL1, parCycleArray[cycleIndex][2], 0, 0);
        setRotation(CrebainLegR1, parCycleArray[cycleIndex][2], 0, 0);
        setRotation(CrebainTail1, parCycleArray[cycleIndex][3], 0, 0);
        // both legs have same (well negative) angle
        setRotation(CrebainWingL1, parCycleArray[cycleIndex][4], parCycleArray[cycleIndex][5], parCycleArray[cycleIndex][6]);
        setRotation(CrebainWingR1, parCycleArray[cycleIndex][4], -parCycleArray[cycleIndex][5], -parCycleArray[cycleIndex][6]);
        setRotation(CrebainWingL2, 0, -21F, -parCycleArray[cycleIndex][7]);
        setRotation(CrebainWingR2, 0, 21F, parCycleArray[cycleIndex][7]);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
