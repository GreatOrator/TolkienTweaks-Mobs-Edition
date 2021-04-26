package com.greatorator.tolkienmobs.entity.ammo.render;

import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RenderFellBeastFireball<T extends EntityFellBeastFireball> extends EntityRenderer<T> {
    private static final ResourceLocation FELLBEAST_FIREBALL_TEXTURES = new ResourceLocation(MODID,"textures/entity/ammo_fellbeast_fireball.png");

    protected RenderFellBeastFireball(EntityRendererManager renderManager) {
        super(renderManager);
    }


    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return FELLBEAST_FIREBALL_TEXTURES;
    }


    @Override
    protected int getSkyLightLevel(T p_239381_1_, BlockPos p_239381_2_) {
        return super.getSkyLightLevel(p_239381_1_, p_239381_2_);
    }

    @Override
    public boolean shouldRender(T livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        boolean yes= super.shouldRender(livingEntityIn, camera, camX, camY, camZ);

        System.out.println("Should we render? " + yes);
        return  yes;
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        System.out.println("RENDER");
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) - 90.0F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
        int i = 0;
        float f = 0.0F;
        float f1 = 0.5F;
        float f2 = 0.0F;
        float f3 = 0.15625F;
        float f4 = 0.0F;
        float f5 = 0.15625F;
        float f6 = 0.15625F;
        float f7 = 0.3125F;
        float f8 = 0.05625F;

        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(45.0F));
        matrixStackIn.scale(0.05625F, 0.05625F, 0.05625F);
        matrixStackIn.translate(-4.0D, 0.0D, 0.0D);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
        MatrixStack.Entry matrixstack$entry = matrixStackIn.last();
        Matrix4f matrix4f = matrixstack$entry.pose();
        Matrix3f matrix3f = matrixstack$entry.normal();
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, packedLightIn);

        for(int j = 0; j < 4; ++j) {
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, packedLightIn);
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, packedLightIn);
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, packedLightIn);
        }

        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public void drawVertex(Matrix4f matrix, Matrix3f normals, IVertexBuilder vertexBuilder, int offsetX, int offsetY, int offsetZ, float textureX, float textureY, int p_229039_9_, int p_229039_10_, int p_229039_11_, int packedLightIn) {
        vertexBuilder.vertex(matrix, (float)offsetX, (float)offsetY, (float)offsetZ).color(255, 255, 255, 255).uv(textureX, textureY).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(normals, (float)p_229039_9_, (float)p_229039_11_, (float)p_229039_10_).endVertex();
    }


    public static class RenderFactory implements IRenderFactory<Entity> {

        @Override
        public EntityRenderer<? super Entity> createRenderFor(EntityRendererManager manager) {

            final RenderFellBeastFireball fellBeastFireballRenderer = new RenderFellBeastFireball(manager);
            return fellBeastFireballRenderer;
        }
    }
}
