package com.greatorator.tolkienmobs.entity.tile.model;

import com.greatorator.tolkienmobs.entity.tile.LockableDoubleTreasureChestTile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class LockableDoubleTreasureChestModel extends AnimatedGeoModel<LockableDoubleTreasureChestTile> {
    @Override
    public ResourceLocation getModelLocation(LockableDoubleTreasureChestTile object) {
        return new ResourceLocation(MODID, "geo/block/lockable_double_treasure_chest.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LockableDoubleTreasureChestTile object) {
        return new ResourceLocation(MODID, "textures/block/chests/lockable_double_treasure_chest_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LockableDoubleTreasureChestTile animatable) {
        return new ResourceLocation(MODID, "animations/chest.animation.json");
    }
}
