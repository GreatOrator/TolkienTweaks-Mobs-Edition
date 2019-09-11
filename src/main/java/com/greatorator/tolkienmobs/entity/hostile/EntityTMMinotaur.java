package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.Block;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTMMinotaur extends EntityTMHostiles {
    public EntityTMMinotaur(World worldIn) {
        super(worldIn);
        this.setSize(2.3F, 6.9F);
        this.setWeaponType(TTMFeatures.AXE_MORGULIRON);
        this.setLootTable(LootInit.MINOTAUR);
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);

        int willSpawn = this.spawnChance();
        BlockPos blockpos = new BlockPos(i, j, k);

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getLight(blockpos) < 8 && willSpawn <= 10 && super.getCanSpawnHere() && !this.world.canSeeSky(new BlockPos(this)) && this.posY < 64.0D;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleMinotaur;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtMinotaur;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathMinotaur;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepMinotaur, 0.25F, 1.0F);
    }

    @Override
    public double getAttackDamage() {
        return 3.0D;
    }

    @Override
    public double getArmorStrength() {
        return 5.0D;
    }

    @Override
    public double getHealthLevel() {
        return 25.0D;
    }
}