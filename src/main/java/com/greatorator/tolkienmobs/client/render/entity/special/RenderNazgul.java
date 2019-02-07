package com.greatorator.tolkienmobs.client.render.entity.special;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
import com.greatorator.tolkienmobs.client.render.model.boss.ModelWitchKing;
import com.greatorator.tolkienmobs.entity.special.EntityNazgul;
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

import javax.annotation.Nonnull;

public class RenderNazgul extends RenderLiving<EntityNazgul> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/nazgul.png");

    public static final Factory FACTORY = new Factory();


    public RenderNazgul(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelWitchKing(), 1.0F);
        this.addLayer(new LayerArmed(this,8.0F, 0.25F, 0.05F,1.0F, 1.5F, 1.5F, 0.12F));
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityNazgul entity) {
        return mobTexture;
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.18F, 0.0F);
    }

    public static class Factory implements IRenderFactory<EntityNazgul> {

        @Override
        public Render<? super EntityNazgul> createRenderFor(RenderManager manager) {
            return new RenderNazgul(manager);
        }

    }
}