//package com.greatorator.tolkienmobs.client.render.entity.ammo;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.projectile.EntityDragonFireball;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nullable;
//
//@SideOnly(Side.CLIENT)
//public class RenderFellBeastFireball<E extends EntityDragonFireball> extends Render<EntityFellBeastFireball> {
//    public static final ResourceLocation FIREBALL_TEXTURE = new ResourceLocation(TolkienMobs.MODID + ":textures/items/fellbeast_fireball.png");
//
//    public RenderFellBeastFireball(RenderManager renderManagerIn) {
//        super(renderManagerIn);
//    }
//
//    @Nullable
//    protected ResourceLocation getEntityTexture(EntityFellBeastFireball entity) {
//        return FIREBALL_TEXTURE;
//    }
//}