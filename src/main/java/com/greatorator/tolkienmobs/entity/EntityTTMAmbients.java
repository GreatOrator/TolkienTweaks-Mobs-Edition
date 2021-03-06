package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class EntityTTMAmbients extends AnimalEntity {

    protected EntityTTMAmbients(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return this.isInvulnerableTo(source) ? false : super.attackEntityFrom(source, amount);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 16D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.13F;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static int spawnChance()
    {
        int i = TTMRand.getRandomInteger(100, 1);
        return i;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.getPosX());
        int j = MathHelper.floor(this.getBoundingBox().minY);
        int k = MathHelper.floor(this.getPosZ());
        BlockPos blockpos = new BlockPos(i, j, k);

        return this.world.getDifficulty() != Difficulty.PEACEFUL && this.isValidLightLevel();
    }
}
