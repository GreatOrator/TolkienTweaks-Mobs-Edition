package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.merchant.model.ModelTTMDwarf;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMBarrowWight;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMDuergar;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBarrowWight;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.passive.ModelDwarf;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMDuergar;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
public class RenderTTMDuergar extends MobRenderer<EntityTTMDuergar, ModelTTMDwarf<EntityTTMDuergar>> {
    public RenderTTMDuergar(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMDwarf<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMDuergar entity) {
        return entity.getDuergarTypeName();
    }

    protected void scale(EntityTTMDuergar entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmduergar/tmduergar1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmduergar/tmduergar2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmduergar/tmduergar3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmduergar/tmduergar4.png");
//
//    public static final RenderTMDuergar.Factory FACTORY = new RenderTMDuergar.Factory();
//
//    public RenderTMDuergar(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelDwarf(), 0.5F);
//        this.addLayer(new LayerArmed(this, 0.095F, 0.035F, -0.9F, 1.2F));
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMDuergar entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMDuergar> {
//        @Override
//        public Render<? super EntityTMDuergar> createRenderFor(RenderManager manager) {
//            return new RenderTMDuergar(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
}