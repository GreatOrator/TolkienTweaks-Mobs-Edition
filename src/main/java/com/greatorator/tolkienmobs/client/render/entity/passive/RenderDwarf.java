package com.greatorator.tolkienmobs.client.render.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.passive.ModelDwarf;
import com.greatorator.tolkienmobs.entity.passive.EntityDwarf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderDwarf extends RenderLiving<EntityDwarf> {
    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
    static {
        for (int i = 0; i < 5; i++) {
            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/dwarf/dwarf" + i + ".png");
        }
    }

    public static final RenderDwarf.Factory FACTORY = new RenderDwarf.Factory();

    public RenderDwarf(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDwarf(), 0.5F);
        this.addLayer(new LayerArmed(this,16.0F, -0.05F, -0.8F,0.0F,0.0F,0.0F,0.08F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDwarf entity) {
        int index = entity.getTextureIndex();
        return mobTexture[index];
    }

    public static class Factory implements IRenderFactory<EntityDwarf> {
        @Override
        public Render<? super EntityDwarf> createRenderFor(RenderManager manager) {
            return new RenderDwarf(manager);
        }
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.1875F, 0.0F);
    }
}