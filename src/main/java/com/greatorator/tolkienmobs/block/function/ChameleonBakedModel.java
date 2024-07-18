package com.greatorator.tolkienmobs.block.function;

import com.greatorator.tolkienmobs.block.ChameleonBlock;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.item.keys.KeyBaseItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ChameleonBakedModel implements BakedModel {
    public static ModelProperty<Optional<BlockState>> COPIED_BLOCK = new ModelProperty<>();
    private final BakedModel modelWhenNotCamouflaged;
    private static final Logger LOGGER = LogManager.getLogger();
    private static boolean loggedError = false; // prevent spamming console

    public ChameleonBakedModel(BakedModel unCamouflagedModel)
    {
        modelWhenNotCamouflaged = unCamouflagedModel;
    }

    public ModelData getModelData() {
        ModelData.Builder builder = ModelData.builder();
        builder.with(COPIED_BLOCK, Optional.empty());
        ModelData modelDataMap = builder.build();
        return modelDataMap;
    }

    @Nonnull
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull RandomSource rand, @Nonnull ModelData extraData)
    {
        try {
            return getActualBakedModelFromIModelData(extraData).getQuads(state, side, rand);
        } catch (IllegalArgumentException e) {
            return modelWhenNotCamouflaged.getQuads(state, side, rand);
        }
    }

    @Override
    @Nonnull
    public ModelData getModelData(@Nonnull BlockAndTintGetter world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull ModelData tileData)
    {
        Optional<BlockState> bestAdjacentBlock = ((ChameleonBlock<?>)state.getBlock()).selectBestAdjacentBlock(world, pos);
        ModelData modelDataMap = getModelData();
        modelDataMap.get(COPIED_BLOCK);
        return modelDataMap;
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@Nonnull ModelData data)
    {
        return getActualBakedModelFromIModelData(data).getParticleIcon(data);
    }

    private BakedModel getActualBakedModelFromIModelData(@Nonnull ModelData data) {
        BakedModel retval = modelWhenNotCamouflaged;
        Item item = Minecraft.getInstance().player.getMainHandItem().getItem();

        if (Minecraft.getInstance().player != null && item == TolkienItems.ITEM_DEV_TOOL.get()) {
            return retval;
        } else if (Minecraft.getInstance().player != null && item instanceof KeyBaseItem) {
            return retval;
        } else if (!data.has(COPIED_BLOCK)) {
            if (!loggedError) {
                LOGGER.error("IModelData did not have expected property COPIED_BLOCK");
                loggedError = true;
            }
            return retval;
        } else {
            Optional<BlockState> copiedBlock = data.get(COPIED_BLOCK);
            if (!copiedBlock.isPresent()) return retval;
            Minecraft mc = Minecraft.getInstance();
            BlockRenderDispatcher blockRendererDispatcher = mc.getBlockRenderer();
            retval = blockRendererDispatcher.getBlockModel(copiedBlock.get());
        }
        return retval;
    }

    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        throw new AssertionError("IBakedModel::getQuads should never be called, only IForgeBakedModel::getQuads");
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction direction, RandomSource randomSource) {
        return List.of();
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
    public ItemOverrides getOverrides()
    {
        return modelWhenNotCamouflaged.getOverrides();
    }
}
