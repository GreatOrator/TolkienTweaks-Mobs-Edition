package com.greatorator.tolkienmobs.entity.layers;

import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBarrowWight;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.MonsterEntity;

public class TTMGhostLayer<T extends MonsterEntity> extends LayerRenderer<T, ModelTTMBarrowWight<T>> {
    private final ModelTTMBarrowWight<T> model = new ModelTTMBarrowWight<T>(0F, true);

    public TTMGhostLayer(IEntityRenderer<T, ModelTTMBarrowWight<T>> p_i50923_1_) {
        super(p_i50923_1_);
    }

    public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, T entityIn, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
//        if (!entityIn.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entityIn, p_225628_5_, p_225628_6_, p_225628_7_);
            this.model.setupAnim(entityIn, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
            IVertexBuilder lvt_11_1_ = p_225628_2_.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entityIn)));
            this.model.renderToBuffer(p_225628_1_, lvt_11_1_, p_225628_3_, LivingRenderer.getOverlayCoords(entityIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
//        }
    }
}
