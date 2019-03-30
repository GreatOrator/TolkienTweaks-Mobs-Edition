package com.greatorator.tolkienmobs.client.render.entity.special;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.special.ModelElementalGolem;
import com.greatorator.tolkienmobs.entity.special.EntityTMElementalGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderElementalGolem extends RenderLiving<EntityTMElementalGolem> {
    private static final ResourceLocation EARTHGOLEM = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elementalgolem/elemental_golem_earth.png");
    private static final ResourceLocation AIRGOLEM = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elementalgolem/elemental_golem_air.png");
    private static final ResourceLocation FIREGOLEM = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elementalgolem/elemental_golem_fire.png");
    private static final ResourceLocation WATERGOLEM = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elementalgolem/elemental_golem_water.png");
    private static final ResourceLocation STONEGOLEM = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elementalgolem/elemental_golem_none.png");

    public static final RenderElementalGolem.Factory FACTORY = new RenderElementalGolem.Factory();
    private ModelElementalGolem modelbase;

    public RenderElementalGolem(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelElementalGolem(), 0.5F);

        this.modelbase = (ModelElementalGolem) this.mainModel;
    }

    @Override
    public void doRender(EntityTMElementalGolem golem, double x, double y, double z, float pitch, float yaw) {
        if(golem.getElementType() >= 1 && golem.getElementType() <= 4 ) {
            ModelElementalGolem.renderGolem = true;
        }
        else{
            ModelElementalGolem.renderGolem = false;
        }
        super.doRender(golem, x, y, z, pitch, yaw);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityTMElementalGolem entity) {
        switch (entity.getElementType()) {
            case 0:
            default:
                return STONEGOLEM;
            case 1:
                return AIRGOLEM;
            case 2:
                return EARTHGOLEM;
            case 3:
                return FIREGOLEM;
            case 4:
                return WATERGOLEM;
            case 5:
                return STONEGOLEM;
            case 6:
                return STONEGOLEM;
        }
    }

    public static class Factory implements IRenderFactory<EntityTMElementalGolem> {
        @Override
        public Render<? super EntityTMElementalGolem> createRenderFor(RenderManager manager) {
            return new RenderElementalGolem(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
}
