package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelElves;
import com.greatorator.tolkienmobs.entity.passive.EntityElves;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderElves extends RenderLiving<EntityElves> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[17];
    static {
        for (int i = 0; i < 17; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elves/elves" + i + ".png");
        }
    }

    public static final RenderElves.Factory FACTORY = new RenderElves.Factory();

    public RenderElves(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelElves(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityElves entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityElves> {
        @Override
        public Render<? super EntityElves> createRenderFor(RenderManager manager) {
            return new RenderElves(manager);
        }
    }
}
