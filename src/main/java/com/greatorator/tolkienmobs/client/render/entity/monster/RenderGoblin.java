package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelGoblin;
import com.greatorator.tolkienmobs.entity.monster.EntityGoblin;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderGoblin extends RenderLiving<EntityGoblin> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goblin/goblin" + i + ".png");
        }
    }

    public static final RenderGoblin.Factory FACTORY = new RenderGoblin.Factory();

    public RenderGoblin(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGoblin(), 0.5F);
        this.addLayer(new LayerArmed(this,16.0F, 0.025F, -(0.325F),0.0F,0.0F,0.0F, 0.0625F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGoblin entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityGoblin> {
        @Override
        public Render<? super EntityGoblin> createRenderFor(RenderManager manager) {
            return new RenderGoblin(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
}