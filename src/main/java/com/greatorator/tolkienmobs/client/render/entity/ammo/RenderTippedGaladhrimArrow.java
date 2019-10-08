package com.greatorator.tolkienmobs.client.render.entity.ammo;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ammo.EntityTippedGaladhrimArrow;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTippedGaladhrimArrow extends RenderGaladhrimArrow<EntityTippedGaladhrimArrow>
{
    public static final ResourceLocation RES_ARROW = new ResourceLocation(TolkienMobs.MODID + ":textures/items/ammo_galadhrim_arrow.png");
    public static final ResourceLocation RES_TIPPED_ARROW = new ResourceLocation(TolkienMobs.MODID + ":textures/items/ammo_tipped_galadhrim_arrow.png");

    public RenderTippedGaladhrimArrow(RenderManager manager, Item itemIn, RenderItem itemRendererIn)
    {
        super(manager, itemIn, itemRendererIn);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTippedGaladhrimArrow entity)
    {
        return entity.getColor() > 0 ? RES_TIPPED_ARROW : RES_ARROW;
    }
}