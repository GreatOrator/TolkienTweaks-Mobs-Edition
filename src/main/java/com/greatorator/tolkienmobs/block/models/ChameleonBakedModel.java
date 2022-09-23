package com.greatorator.tolkienmobs.block.models;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.ChameleonBlock;
import com.greatorator.tolkienmobs.item.tools.KeyBaseItem;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ChameleonBakedModel implements IBakedModel {
    public static ModelProperty<Optional<BlockState>> COPIED_BLOCK = new ModelProperty<>();
    private IBakedModel modelWhenNotCamouflaged;
    private static final Logger LOGGER = LogManager.getLogger();
    private static boolean loggedError = false; // prevent spamming console

    public ChameleonBakedModel(IBakedModel unCamouflagedModel)
    {
        modelWhenNotCamouflaged = unCamouflagedModel;
    }

    public static ModelDataMap getEmptyIModelData() {
        ModelDataMap.Builder builder = new ModelDataMap.Builder();
        builder.withInitial(COPIED_BLOCK, Optional.empty());
        ModelDataMap modelDataMap = builder.build();
        return modelDataMap;
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData)
    {
        try {
            return getActualBakedModelFromIModelData(extraData).getQuads(state, side, rand);
        } catch (IllegalArgumentException e) {
            return modelWhenNotCamouflaged.getQuads(state, side, rand);
        }
    }

    @Override
    @Nonnull
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData)
    {
        Optional<BlockState> bestAdjacentBlock = ChameleonBlock.selectBestAdjacentBlock(world, pos);
        ModelDataMap modelDataMap = getEmptyIModelData();
        modelDataMap.setData(COPIED_BLOCK, bestAdjacentBlock);
        return modelDataMap;
    }

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data)
    {
        return getActualBakedModelFromIModelData(data).getParticleTexture(data);
    }

    private IBakedModel getActualBakedModelFromIModelData(@Nonnull IModelData data) {
        IBakedModel retval = modelWhenNotCamouflaged;  // default

        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.getMainHandItem().getItem() == TTMContent.ITEM_DEV_TOOL.get()) {
            return retval;
        } else if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.getMainHandItem().getItem() instanceof KeyBaseItem){
            return retval;
        } else if (!data.hasProperty(COPIED_BLOCK)) {
            if (!loggedError) {
                LOGGER.error("IModelData did not have expected property COPIED_BLOCK");
                loggedError = true;
            }
            return retval;
        } else {
            Optional<BlockState> copiedBlock = data.getData(COPIED_BLOCK);
            if (!copiedBlock.isPresent()) return retval;
            Minecraft mc = Minecraft.getInstance();
            BlockRendererDispatcher blockRendererDispatcher = mc.getBlockRenderer();
            retval = blockRendererDispatcher.getBlockModel(copiedBlock.get());
        }
        return retval;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        throw new AssertionError("IBakedModel::getQuads should never be called, only IForgeBakedModel::getQuads");
    }

    @Override
    public boolean useAmbientOcclusion() {
        return modelWhenNotCamouflaged.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d()
    {
        return modelWhenNotCamouflaged.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return null;
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return modelWhenNotCamouflaged.getOverrides();
    }
}
