package com.greatorator.tolkienmobs.entity.tile.model;

import com.greatorator.tolkienmobs.entity.tile.LockableChestTile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class LockableChestModel extends AnimatedGeoModel<LockableChestTile> {
    @Override
    public ResourceLocation getModelLocation(LockableChestTile object) {
        return new ResourceLocation(MODID, "geo/block/lockable_chest.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LockableChestTile object) {
        return new ResourceLocation(MODID, "textures/block/chests/lockable_chest_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LockableChestTile animatable) {
        return new ResourceLocation(MODID, "animations/chest.animation.json");
    }
}