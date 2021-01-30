//package com.greatorator.tolkienmobs.client.render.entity.layers;
//
//import com.greatorator.tolkienmobs.block.BlockFlowers;
//import com.greatorator.tolkienmobs.client.render.entity.special.RenderMithrilGolem;
//import com.greatorator.tolkienmobs.client.render.model.special.ModelMithrilGolem;
//import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BlockRendererDispatcher;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.client.renderer.texture.TextureMap;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class LayerMithrilGolemFlower implements LayerRenderer<EntityTMMithrilGolem>
//{
//    private final RenderMithrilGolem mithrilGolemRenderer;
//
//    public LayerMithrilGolemFlower(RenderMithrilGolem mithrilGolemRendererIn)
//    {
//        this.mithrilGolemRenderer = mithrilGolemRendererIn;
//    }
//
//    public void doRenderLayer(EntityTMMithrilGolem LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
//    {
//        if (LivingEntityIn.getHoldRoseTick() != 0)
//        {
//            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
//            GlStateManager.enableRescaleNormal();
//            GlStateManager.pushMatrix();
//            GlStateManager.rotate(5.0F + 180.0F * ((ModelMithrilGolem)this.mithrilGolemRenderer.getMainModel()).bipedRightArm.rotateAngleX / (float)Math.PI, 1.0F, 0.0F, 0.0F);
//            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
//            GlStateManager.translate(-0.9375F, -0.625F, -0.9375F);
//            float f = 0.5F;
//            GlStateManager.scale(0.5F, -0.5F, 0.5F);
//            int i = LivingEntityIn.getBrightnessForRender();
//            int j = i % 65536;
//            int k = i / 65536;
//            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
//            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//            this.mithrilGolemRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
//            blockrendererdispatcher.renderBlockBrightness(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.LILLYOFTHEVALLEY), 1.0F);
//            GlStateManager.popMatrix();
//            GlStateManager.disableRescaleNormal();
//        }
//    }
//
//    public boolean shouldCombineTextures()
//    {
//        return false;
//    }
//}