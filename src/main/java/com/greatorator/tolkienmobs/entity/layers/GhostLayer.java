package com.greatorator.tolkienmobs.entity.layers;

import com.greatorator.tolkienmobs.entity.monster.model.BarrowWightModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.MonsterEntity;

public class GhostLayer<T extends MonsterEntity> extends LayerRenderer<T, BarrowWightModel<T>> {
    private final BarrowWightModel<T> model = new BarrowWightModel<T>(0F, true);

    public GhostLayer(IEntityRenderer<T, BarrowWightModel<T>> p_i50923_1_) {
        super(p_i50923_1_);
    }

    @Override
    public void render(MatrixStack mStack, IRenderTypeBuffer getter, int packedLight, T entity, float limkSwing, float limbSwingAmount, float partialTick, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        this.getParentModel().copyPropertiesTo(this.model);
        this.model.prepareMobModel(entity, limkSwing, limbSwingAmount, partialTick);
        this.model.setupAnim(entity, limkSwing, limbSwingAmount, p_225628_8_, p_225628_9_, p_225628_10_);
        IVertexBuilder buffer = getter.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
        float alpha = 0.5F; //Adjust this to adjust transparency
        this.model.renderToBuffer(mStack, buffer, packedLight, LivingRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, alpha);
    }
}
