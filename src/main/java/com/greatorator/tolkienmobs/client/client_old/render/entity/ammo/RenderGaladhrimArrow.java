//package com.greatorator.tolkienmobs.client.render.entity.ammo;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
//import net.minecraft.client.renderer.entity.RenderArrow;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nullable;
//
//@SideOnly(Side.CLIENT)
//public class RenderGaladhrimArrow<E extends EntityGaladhrimArrow> extends RenderArrow<EntityGaladhrimArrow>
//{
//    public static final ResourceLocation ARROW_TEXTURE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/ammo_galadhrim_arrow.png");
//
//    public RenderGaladhrimArrow(RenderManager renderManagerIn) {
//        super(renderManagerIn);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(EntityGaladhrimArrow entity) {
//        return ARROW_TEXTURE;
//    }
//}