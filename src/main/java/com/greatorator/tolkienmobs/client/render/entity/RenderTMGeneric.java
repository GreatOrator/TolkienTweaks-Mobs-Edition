//package com.greatorator.tolkienmobs.client.render.entity;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.util.ResourceLocation;
//
///** Borrowed from Twilight Forest */
//public class RenderTMGeneric<T extends EntityLiving> extends RenderLiving<T> {
//    private final ResourceLocation textureLoc;
//
//    public RenderTMGeneric(RenderManager manager, ModelBase model, float shadowSize, String textureName) {
//        super(manager, model, shadowSize);
//
//        if (textureName.startsWith("textures")) {
//            textureLoc = new ResourceLocation(textureName);
//        } else {
//            textureLoc = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/" + textureName);
//        }
//    }
//
//    @Override
//    protected ResourceLocation getEntityTexture(T entity) {
//        return textureLoc;
//    }
//}