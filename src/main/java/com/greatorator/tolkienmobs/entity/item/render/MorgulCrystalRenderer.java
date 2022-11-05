package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.entity.item.MorgulCrystalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@OnlyIn(Dist.CLIENT)
public class MorgulCrystalRenderer extends EntityRenderer<MorgulCrystalEntity> {
   private static final ResourceLocation MORGUL_CRYSTAL_LOCATION = new ResourceLocation(MODID + ":textures/entity/morgul_crystal/morgul_crystal.png");
   private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(MORGUL_CRYSTAL_LOCATION);
   private static final float SIN_45 = (float)Math.sin((Math.PI / 4D));
   private final ModelPart cube;
   private final ModelPart glass;
   private final ModelPart base;

   public MorgulCrystalRenderer(EntityRendererProvider.Context context) {
      super(context);
      this.shadowRadius = 0.5F;
      ModelPart modelpart = context.bakeLayer(ModelLayers.END_CRYSTAL);
      this.glass = modelpart.getChild("glass");
      this.cube = modelpart.getChild("cube");
      this.base = modelpart.getChild("base");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      partdefinition.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
      partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
      partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 4.0F, 12.0F), PartPose.ZERO);
      return LayerDefinition.create(meshdefinition, 64, 32);
   }

   public void render(MorgulCrystalEntity crystalEntity, float p_225623_2_, float p_225623_3_, PoseStack stack, MultiBufferSource bufferSource, int p_225623_6_) {
      stack.pushPose();
      float f = getY(crystalEntity, p_225623_3_);
      float f1 = ((float)crystalEntity.time + p_225623_3_) * 3.0F;
      VertexConsumer ivertexbuilder = bufferSource.getBuffer(RENDER_TYPE);
      stack.pushPose();
      stack.scale(2.0F, 2.0F, 2.0F);
      stack.translate(0.0D, -0.5D, 0.0D);
      int i = OverlayTexture.NO_OVERLAY;
      if (crystalEntity.showsBottom()) {
         this.base.render(stack, ivertexbuilder, p_225623_6_, i);
      }

      stack.mulPose(Vector3f.YP.rotationDegrees(f1));
      stack.translate(0.0D, (double)(1.5F + f / 2.0F), 0.0D);
      stack.mulPose(new Quaternion(new Vector3f(SIN_45, 0.0F, SIN_45), 60.0F, true));
      this.glass.render(stack, ivertexbuilder, p_225623_6_, i);
      float f2 = 0.875F;
      stack.scale(0.875F, 0.875F, 0.875F);
      stack.mulPose(new Quaternion(new Vector3f(SIN_45, 0.0F, SIN_45), 60.0F, true));
      stack.mulPose(Vector3f.YP.rotationDegrees(f1));
      this.glass.render(stack, ivertexbuilder, p_225623_6_, i);
      stack.scale(0.875F, 0.875F, 0.875F);
      stack.mulPose(new Quaternion(new Vector3f(SIN_45, 0.0F, SIN_45), 60.0F, true));
      stack.mulPose(Vector3f.YP.rotationDegrees(f1));
      this.cube.render(stack, ivertexbuilder, p_225623_6_, i);
      stack.popPose();
      stack.popPose();
      BlockPos blockpos = crystalEntity.getBeamTarget();
      if (blockpos != null) {
         float f3 = (float)blockpos.getX() + 0.5F;
         float f4 = (float)blockpos.getY() + 0.5F;
         float f5 = (float)blockpos.getZ() + 0.5F;
         float f6 = (float)((double)f3 - crystalEntity.getX());
         float f7 = (float)((double)f4 - crystalEntity.getY());
         float f8 = (float)((double)f5 - crystalEntity.getZ());
         stack.translate((double)f6, (double)f7, (double)f8);
//         FellBeastRender.renderCrystalBeams(-f6, -f7 + f, -f8, p_225623_3_, crystalEntity.time, stack, bufferSource, p_225623_6_);
      }

      super.render(crystalEntity, p_225623_2_, p_225623_3_, stack, bufferSource, p_225623_6_);
   }

   public static float getY(MorgulCrystalEntity p_229051_0_, float p_229051_1_) {
      float f = (float)p_229051_0_.time + p_229051_1_;
      float f1 = Mth.sin(f * 0.2F) / 2.0F + 0.5F;
      f1 = (f1 * f1 + f1) * 0.4F;
      return f1 - 1.4F;
   }

   public ResourceLocation getTextureLocation(MorgulCrystalEntity p_110775_1_) {
      return MORGUL_CRYSTAL_LOCATION;
   }

   public boolean shouldRender(MorgulCrystalEntity p_225626_1_, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
      return super.shouldRender(p_225626_1_, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_) || p_225626_1_.getBeamTarget() != null;
   }
}