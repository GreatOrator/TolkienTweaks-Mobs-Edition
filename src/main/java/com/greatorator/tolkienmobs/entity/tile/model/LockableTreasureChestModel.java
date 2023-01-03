package com.greatorator.tolkienmobs.entity.tile.model;

import com.greatorator.tolkienmobs.entity.tile.LockableTreasureChestTile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class LockableTreasureChestModel extends AnimatedGeoModel<LockableTreasureChestTile> {
    @Override
    public ResourceLocation getModelLocation(LockableTreasureChestTile object) {
        return new ResourceLocation(MODID, "geo/block/lockable_treasure_chest.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LockableTreasureChestTile object) {
        return new ResourceLocation(MODID, "textures/block/chests/lockable_treasure_chest_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LockableTreasureChestTile animatable) {
        return new ResourceLocation(MODID, "animations/chest.animation.json");
    }
}
