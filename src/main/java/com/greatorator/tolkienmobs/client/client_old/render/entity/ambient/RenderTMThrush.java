//package com.greatorator.tolkienmobs.client.render.entity.ambient;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.ambient.ModelThrush;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMThrush;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.MathHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class RenderTMThrush extends RenderLiving<EntityTMThrush>
//{
//    public static final ResourceLocation[] THRUSH_TEXTURES = new ResourceLocation[] {new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png")};
//
//    public RenderTMThrush(RenderManager p_i47375_1_)
//    {
//        super(p_i47375_1_, new ModelThrush(), 0.3F);
//    }
//
//    /**
//     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
//     */
//    protected ResourceLocation getEntityTexture(EntityTMThrush entity)
//    {
//        return THRUSH_TEXTURES[entity.getVariant()];
//    }
//
//    /**
//     * Defines what float the third param in setRotationAngles of ModelBase is
//     */
//    public float handleRotationFloat(EntityTMThrush livingBase, float partialTicks)
//    {
//        return this.getCustomBob(livingBase, partialTicks);
//    }
//
//    private float getCustomBob(EntityTMThrush thrush, float p_192861_2_)
//    {
//        float f = thrush.oFlap + (thrush.flap - thrush.oFlap) * p_192861_2_;
//        float f1 = thrush.oFlapSpeed + (thrush.flapSpeed - thrush.oFlapSpeed) * p_192861_2_;
//        return (MathHelper.sin(f) + 1.0F) * f1;
//    }
//}