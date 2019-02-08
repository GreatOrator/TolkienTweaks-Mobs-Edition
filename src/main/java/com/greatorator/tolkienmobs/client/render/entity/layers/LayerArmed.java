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
    private float itemXOffset;
    private float itemYOffset;
    private float itemZOffset;
    private float itemScale;

    public LayerArmed(RenderLivingBase<?> weaponRendererIn, float itemXOffset, float itemYOffset, float itemZOffset, float itemScale) {
        this.weaponRenderer = weaponRendererIn;
        this.itemXOffset = itemXOffset;
        this.itemYOffset = itemYOffset;
        this.itemZOffset = itemZOffset;
        this.itemScale = itemScale;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            GlStateManager.pushMatrix();

            if (this.weaponRenderer.getMainModel().isChild) {
                float f = 0.5F;
                GlStateManager.translate(0.0F, 0.75F, 0.0F);
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
            }

            renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType transform, EnumHandSide handSide) {
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();

            if (entity.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            this.translateToHand(handSide);
            GlStateManager.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

            //This is a good way to figure out the item and scale offset runtime.
            //Once you find values that work put them where they belong (in this case RenderTroll) and comment/delete this test code.
//            if (entity instanceof EntityTroll) {
//                itemXOffset = 0.25F;
//                itemYOffset = 0.0F;
//                itemZOffset = -1.2F;
//                itemScale = 1.5F;
//            }

//            if (entity instanceof EntityGoblin) {
//                itemXOffset = 0.0825f;
//                itemYOffset = 0.025F;
//                itemZOffset = -0.325F;
//                itemScale = 1F;
//            }

//            16.0F, 0.025F, -0.325F, 1F

            boolean flag = handSide == EnumHandSide.LEFT;
            GlStateManager.translate((float) (flag ? -1 : 1) * itemXOffset, itemYOffset, itemZOffset);
            GlStateManager.scale(itemScale, itemScale, itemScale);

            Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, transform, flag);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToHand(EnumHandSide p_191361_1_) {
        ((ModelTTM) this.weaponRenderer.getMainModel()).postRenderArm(0.0625F, p_191361_1_);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
