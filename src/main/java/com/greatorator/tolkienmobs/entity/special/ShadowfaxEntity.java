package com.greatorator.tolkienmobs.entity.special;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShadowfaxEntity extends HorseEntity {
    public ShadowfaxEntity(EntityType<? extends HorseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)1.0F)
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.JUMP_STRENGTH, 2.5D);
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }
}
