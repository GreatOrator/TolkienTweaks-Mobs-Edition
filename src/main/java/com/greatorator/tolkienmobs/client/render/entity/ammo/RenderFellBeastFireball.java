package com.greatorator.tolkienmobs.client.render.entity.ammo;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderFellBeastFireball extends Render<EntityFellBeastFireball> {
    public static Factory FACTORY = new Factory();
    protected final Item item;
    private final RenderItem itemRenderer;
    private EntityFellBeastFireball entityIn;

    public RenderFellBeastFireball(RenderManager renderManager, Item itemIn, RenderItem itemRendererIn) {
        super(renderManager);
        this.item = itemIn;
        this.itemRenderer = itemRendererIn;
    }

    public void doRender(EntityFellBeastFireball entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(new ResourceLocation(TolkienMobs.MODID + ":textures/items/fellbeast_fireball.png"));

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.itemRenderer.renderItem(this.getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public ItemStack getStackToRender(EntityFellBeastFireball entityIn)
    {
        this.entityIn = entityIn;
        return new ItemStack(this.item);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFellBeastFireball entity) {
        return new ResourceLocation(TolkienMobs.MODID + ":textures/items/fellbeast_fireball.png");
    }

    public static class Factory implements IRenderFactory<EntityFellBeastFireball>{
        @Override
        public Render<? super EntityFellBeastFireball> createRenderFor(RenderManager manager) {
            return new RenderFellBeastFireball(manager, TTMFeatures.FELLBEAST_FIREBALL, Minecraft.getMinecraft().getRenderItem());
        }
    }
}