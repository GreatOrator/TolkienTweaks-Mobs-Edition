package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelMordorOrc;
import com.greatorator.tolkienmobs.entity.monster.EntityMordorOrc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderMordorOrc extends RenderLiving<EntityMordorOrc> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/orc/mordororc" + i + ".png");
        }
    }

    public static final RenderMordorOrc.Factory FACTORY = new RenderMordorOrc.Factory();

    public RenderMordorOrc(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMordorOrc(), 0.5F);
        this.addLayer(new LayerArmed(this, 0.0625f, 0.02F, -0.7F,1F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMordorOrc entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityMordorOrc> {
        @Override
        public Render<? super EntityMordorOrc> createRenderFor(RenderManager manager) {
            return new RenderMordorOrc(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.1875F, 0.0F);
    }
}