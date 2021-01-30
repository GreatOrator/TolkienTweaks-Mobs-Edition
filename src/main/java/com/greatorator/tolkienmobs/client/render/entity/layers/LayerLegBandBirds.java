//package com.greatorator.tolkienmobs.client.render.entity.layers;
//
//import com.greatorator.tolkienmobs.client.render.entity.RenderTMBirds;
//import com.greatorator.tolkienmobs.entity.EntityTMBirds;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class LayerLegBandBirds implements LayerRenderer<EntityTMBirds> {
//    protected ResourceLocation legBandTexture;
//    protected final RenderTMBirds renderer;
//
//    public LayerLegBandBirds(RenderTMBirds parRenderer, ResourceLocation parTexture)
//    {
//        renderer = parRenderer;
//        legBandTexture = parTexture;
//    }
//
//    @Override
//    public void doRenderLayer(EntityTMBirds LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
//    {
//        if (LivingEntityIn.isTamed() && !LivingEntityIn.isInvisible())
//        {
//            this.renderer.bindTexture(legBandTexture);
//            float[] afloat = LivingEntityIn.getLegBandColor().getColorComponentValues();
//            GlStateManager.color(afloat[0], afloat[1], afloat[2]);
//            this.renderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//        }
//    }
//
//    @Override
//    public boolean shouldCombineTextures()
//    {
//        return true;
//    }
//}