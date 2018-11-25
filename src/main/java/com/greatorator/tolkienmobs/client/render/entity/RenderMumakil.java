package com.greatorator.tolkienmobs.client.render.entity;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.ModelMumakil;

import com.greatorator.tolkienmobs.entity.monster.EntityMumakil;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

/** Borrowed from Jabelar https://github.com/jabelar */
@SuppressWarnings("rawtypes")
public class RenderMumakil extends RenderLiving<EntityMumakil>
{
    private static final ResourceLocation[] mobTexture = new ResourceLocation[4];
    static {
        for (int i = 0; i < 4; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mumakil/mumakil" + i + ".png");
        }
    }

	public static final RenderMumakil.Factory FACTORY = new RenderMumakil.Factory();

	public RenderMumakil(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelMumakil(), 2.0F);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMumakil entity) {
		int index = entity.getTextureIndex();
		return mobTexture[index];
	}

	public static class Factory implements IRenderFactory<EntityMumakil> {

		@Override
		public Render<? super EntityMumakil> createRenderFor(RenderManager manager) {
			return new RenderMumakil(manager);
		}

	}
}