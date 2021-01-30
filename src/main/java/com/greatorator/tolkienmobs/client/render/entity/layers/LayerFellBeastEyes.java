//package com.greatorator.tolkienmobs.client.render.entity.layers;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.boss.RenderFellBeast;
//import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class LayerFellBeastEyes implements LayerRenderer<EntityTMFellBeast>
//{
//    private static final ResourceLocation TEXTURE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/fellbeast/fellbeast_eyes.png");
//    private final RenderFellBeast dragonRenderer;
//
//    public LayerFellBeastEyes(RenderFellBeast dragonRendererIn)
//    {
//        this.dragonRenderer = dragonRendererIn;
//    }
//
//    public void doRenderLayer(EntityTMFellBeast LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
//    {
//        this.dragonRenderer.bindTexture(TEXTURE);
//        GlStateManager.enableBlend();
//        GlStateManager.disableAlpha();
//        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
//        GlStateManager.disableLighting();
//        GlStateManager.depthFunc(514);
//        int i = 61680;
//        int j = 61680;
//        int k = 0;
//        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
//        GlStateManager.enableLighting();
//        GlStateManager.color(0.0F, 0.0F, 0.0F, 0.0F);
//        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
//        this.dragonRenderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
//        this.dragonRenderer.setLightmap(LivingEntityIn);
//        GlStateManager.disableBlend();
//        GlStateManager.enableAlpha();
//        GlStateManager.depthFunc(515);
//    }
//
//    public boolean shouldCombineTextures()
//    {
//        return false;
//    }
//}