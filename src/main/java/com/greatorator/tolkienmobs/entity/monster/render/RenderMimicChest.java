//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelMimicChest;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMMimicChest;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//public class RenderMimicChest extends RenderLiving<EntityTMMimicChest> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mimicchest/mimicchest1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mimicchest/mimicchest2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mimicchest/mimicchest1.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mimicchest/mimicchest2.png");
//
//    public static final Factory FACTORY = new Factory();
//    private ModelMimicChest modelbase;
//
//    public float scale;
//
//    public RenderMimicChest(RenderManager rendermanagerIn, float scale)
//    {
//        super(rendermanagerIn, new ModelMimicChest(), 0.0F);
//        this.scale = scale;
//
//        this.modelbase = (ModelMimicChest) this.mainModel;
//    }
//
//    @Override
//    public void doRender(EntityTMMimicChest mimic, double x, double y, double z, float pitch, float yaw) {
//        if(mimic.getRevengeTarget() == null && !mimic.isAngry()) {
//            ModelMimicChest.renderChest = true;
//        }
//        else{
//            ModelMimicChest.renderChest = false;
//        }
//        super.doRender(mimic, x, y, z, pitch, yaw);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMMimicChest entity) {
//        switch (entity.getMobType()) {
//            case 0:
//            default:
//                return GREEN;
//            case 1:
//                return GREEN;
//            case 2:
//                return BLACK;
//            case 3:
//                return BLUE;
//            case 4:
//                return WHITE;
//            case 5:
//                return WHITE;
//        }
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMMimicChest> {
//        @Override
//        public Render<? super EntityTMMimicChest> createRenderFor(RenderManager manager) {
//            return new RenderMimicChest(manager,0.5F);
//        }
//    }
//}