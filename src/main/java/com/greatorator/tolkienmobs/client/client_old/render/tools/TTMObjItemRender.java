//package com.greatorator.tolkienmobs.client.render.tools;
//
//import codechicken.lib.math.MathHelper;
//import codechicken.lib.render.CCModel;
//import codechicken.lib.render.CCRenderState;
//import codechicken.lib.render.OBJParser;
//import codechicken.lib.render.item.IItemRenderer;
//import codechicken.lib.util.TransformUtils;
//import codechicken.lib.vec.Rotation;
//import codechicken.lib.vec.Scale;
//import codechicken.lib.vec.Translation;
//import com.brandon3055.brandonscore.client.ResourceHelperBC;
//import com.greatorator.tolkienmobs.TolkienMobs;
//import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.common.model.IModelState;
//import org.lwjgl.opengl.GL11;
//
//import java.util.Map;
//
//public class TTMObjItemRender implements IItemRenderer {
//    private final String toolPath = TolkienMobs.MODID + ":models/item/tools/";
//    private String toolTexturePath = TolkienMobs.MODID + ":textures/items/tools/obj/";
//    private final String toolRender;
//    private String toolTexture;
//    private CCModel model;
//
//    public TTMObjItemRender(String toolRender, String toolTexture) {
//        this.toolRender = toolRender;
//        this.toolTexture = toolTexture;
//        Map<String, CCModel> map = OBJParser.parseModels(ResourceHelperBC.getResourceRAW(toolPath + this.toolRender));
//        model = CCModel.combine(map.values());
//    }
//
//    @Override
//    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
//        ResourceHelperBC.bindTexture(ResourceHelperBC.getResourceRAW(toolTexturePath + toolTexture));
//
//        CCRenderState ccrs = CCRenderState.instance();
//
//        ccrs.reset();
//        ccrs.startDrawing(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_NORMAL);
//
//        //If your model is created properly with a scale that matches minecraft this should just work.
//        //But that can be tricky to pull off unless the person making the model really knows what they are doing so you will probably have to fiddle with these values.
//        if (transformType == ItemCameraTransforms.TransformType.GUI) {
//            model.render(ccrs, new Rotation(MathHelper.torad * 90, 1, 0, 0).with(new Rotation(MathHelper.torad * -45, 0, 1, 0)).with(new Scale(-0.3)).with(new Translation(0.3, 0.45, 0.5)));
//        }
//        else {
//            model.render(ccrs, new Rotation(MathHelper.torad * 80, 1, 0, 0).with(new Rotation(MathHelper.torad * -90, 0, 1, 0)).with(new Scale(-0.5)).with(new Translation(0.6, 0.30, 0.5)));
//        }
//
//        ccrs.draw();
//    }
//
//    @Override
//    public IModelState getTransforms() {
//        return TransformUtils.DEFAULT_TOOL;
//    }
//
//    @Override
//    public boolean isAmbientOcclusion() {
//        return false;
//    }
//
//    @Override
//    public boolean isGui3d() {
//        return false;
//    }
//}