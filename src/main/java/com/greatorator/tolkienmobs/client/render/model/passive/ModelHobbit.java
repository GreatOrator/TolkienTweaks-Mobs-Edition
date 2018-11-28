package com.greatorator.tolkienmobs.client.render.model.passive;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Hobbit - GreatOrator
 */
public class ModelHobbit extends ModelTolkienMobs {
    public ModelRenderer HobbitArmL;
    public ModelRenderer HobbitLegL;
    public ModelRenderer HobbitBody;
    public ModelRenderer HobbitArmR;
    public ModelRenderer HobbitLegR;
    public ModelRenderer HobbitHead;

    public ModelHobbit() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.HobbitBody = new ModelRenderer(this, 16, 16);
        this.HobbitBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HobbitBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.HobbitArmL = new ModelRenderer(this, 40, 16);
        this.HobbitArmL.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.HobbitArmL.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HobbitLegR = new ModelRenderer(this, 16, 48);
        this.HobbitLegR.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.HobbitLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HobbitLegL = new ModelRenderer(this, 0, 16);
        this.HobbitLegL.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.HobbitLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HobbitArmR = new ModelRenderer(this, 32, 48);
        this.HobbitArmR.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.HobbitArmR.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HobbitHead = new ModelRenderer(this, 0, 0);
        this.HobbitHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HobbitHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaleFactor = 0.75F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

        this.HobbitBody.render(f5);
        this.HobbitArmL.render(f5);
        this.HobbitLegR.render(f5);
        this.HobbitLegL.render(f5);
        this.HobbitArmR.render(f5);
        this.HobbitHead.render(f5);

        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.HobbitLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.HobbitLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.HobbitArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.HobbitArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.HobbitHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.HobbitHead.rotateAngleX = headPitch * 0.017453292F;
    }

}
