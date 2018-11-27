package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelCrebain;
import com.greatorator.tolkienmobs.entity.monster.EntityCrebain;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

@SuppressWarnings("rawtypes")
public class RenderCrebain extends RenderLiving<EntityCrebain> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/crebain.png");

    public static final Factory FACTORY = new Factory();

    public RenderCrebain(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelCrebain(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityCrebain entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityCrebain> {

        @Override
        public Render<? super EntityCrebain> createRenderFor(RenderManager manager) {
            return new RenderCrebain(manager);
        }

    }
}
