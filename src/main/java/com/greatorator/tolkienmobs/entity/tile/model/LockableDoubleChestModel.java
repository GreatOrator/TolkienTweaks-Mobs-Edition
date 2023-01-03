package com.greatorator.tolkienmobs.entity.tile.model;

import com.greatorator.tolkienmobs.entity.tile.LockableDoubleChestTile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class LockableDoubleChestModel extends AnimatedGeoModel<LockableDoubleChestTile> {
    @Override
    public ResourceLocation getModelLocation(LockableDoubleChestTile object) {
        return new ResourceLocation(MODID, "geo/block/lockable_double_chest.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LockableDoubleChestTile object) {
        return new ResourceLocation(MODID, "textures/block/chests/lockable_double_chest_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LockableDoubleChestTile animatable) {
        return new ResourceLocation(MODID, "animations/chest.animation.json");
    }
}
