package com.greatorator.tolkienmobs.client.render.entity.ambient;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.ambient.ModelCrebain;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMCrebain;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTMCrebain extends RenderLiving<EntityTMCrebain>
{
    public static final ResourceLocation[] CREBAIN_TEXTURES = new ResourceLocation[] {new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png")};

    public RenderTMCrebain(RenderManager p_i47375_1_)
    {
        super(p_i47375_1_, new ModelCrebain(), 0.3F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTMCrebain entity)
    {
        return CREBAIN_TEXTURES[entity.getVariant()];
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    public float handleRotationFloat(EntityTMCrebain livingBase, float partialTicks)
    {
        return this.getCustomBob(livingBase, partialTicks);
    }

    private float getCustomBob(EntityTMCrebain crebain, float p_192861_2_)
    {
        float f = crebain.oFlap + (crebain.flap - crebain.oFlap) * p_192861_2_;
        float f1 = crebain.oFlapSpeed + (crebain.flapSpeed - crebain.oFlapSpeed) * p_192861_2_;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}