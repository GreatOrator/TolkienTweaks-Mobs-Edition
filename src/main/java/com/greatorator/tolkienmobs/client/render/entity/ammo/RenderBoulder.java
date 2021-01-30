//package com.greatorator.tolkienmobs.client.render.entity.ammo;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.RenderItem;
//import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nullable;
//
//@SideOnly(Side.CLIENT)
//public class RenderBoulder extends Render<EntityBoulder> {
//    public static final ResourceLocation BOULDER_TEXTURE = new ResourceLocation(TolkienMobs.MODID + ":textures/items/ammo_boulder.png");
//    public static Factory FACTORY = new Factory();
//    protected final Item item;
//    private final RenderItem itemRenderer;
//    private EntityBoulder entityIn;
//
//    public RenderBoulder(RenderManager renderManager, Item itemIn, RenderItem itemRendererIn) {
//        super(renderManager);
//        this.item = itemIn;
//        this.itemRenderer = itemRendererIn;
//    }
//
//    public void doRender(EntityBoulder entity, double x, double y, double z, float entityYaw, float partialTicks)
//    {
//        GlStateManager.pushMatrix();
//        GlStateManager.translate((float)x, (float)y, (float)z);
//        GlStateManager.enableRescaleNormal();
//        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
//
//        if (this.renderOutlines)
//        {
//            GlStateManager.enableColorMaterial();
//            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
//        }
//
//        this.itemRenderer.renderItem(this.getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);
//
//        if (this.renderOutlines)
//        {
//            GlStateManager.disableOutlineMode();
//            GlStateManager.disableColorMaterial();
//        }
//
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.popMatrix();
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
//    }
//
//    public ItemStack getStackToRender(EntityBoulder entityIn)
//    {
//        this.entityIn = entityIn;
//        return new ItemStack(this.item);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(EntityBoulder entity) {
//        return BOULDER_TEXTURE;
//    }
//
//    public static class Factory implements IRenderFactory<EntityBoulder>{
//        @Override
//        public Render<? super EntityBoulder> createRenderFor(RenderManager manager) {
//            return new RenderBoulder(manager, TTMFeatures.AMMO_BOULDER, Minecraft.getMinecraft().getRenderItem());
//        }
//    }
//}