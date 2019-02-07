package com.greatorator.tolkienmobs.client.render.entity.layers;

import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerArmed implements LayerRenderer<EntityLivingBase> {

    private final RenderLivingBase<?> weaponRenderer;
    private float floatX;
    private float floatY;
    private float floatZ;
    private float scaleX;
    private float scaleY;
    private float scaleZ;
    private float scaleHand;

    public LayerArmed(RenderLivingBase<?> weaponRendererIn, float floatX, float floatY, float floatZ, float scaleX, float scaleY, float scaleZ, float scaleHand)
    {
        this.weaponRenderer = weaponRendererIn;
        this.floatX = floatX;
        this.floatY = floatY;
        this.floatZ = floatZ;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        this.scaleHand = scaleHand;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty())
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(scaleX, scaleY, scaleZ);

            if (this.weaponRenderer.getMainModel().isChild)
            {
                float f = 0.5F;
                GlStateManager.translate(0.0F, 0.75F, 0.0F);
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
            }

            renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType transform, EnumHandSide handSide)
    {
        if (!stack.isEmpty())
        {
            GlStateManager.pushMatrix();

            if (entity.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            this.translateToHand(handSide);
            GlStateManager.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            boolean flag = handSide == EnumHandSide.LEFT;

            if(floatX == 8.0F){
                GlStateManager.translate((float)(flag ? 1 : -1)/ floatX, floatY, floatZ);
            }else {
                GlStateManager.translate((float)(flag ? -1 : 1) / floatX, floatY, floatZ);
            }

            Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, transform, flag);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToHand(EnumHandSide p_191361_1_) {
        ((ModelTTM)this.weaponRenderer.getMainModel()).postRenderArm(scaleHand, p_191361_1_);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
