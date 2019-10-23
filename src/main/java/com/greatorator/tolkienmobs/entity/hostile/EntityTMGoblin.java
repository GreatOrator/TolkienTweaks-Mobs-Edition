package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.utils.TTMSpawnEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTMGoblin extends EntityTMHostiles {

    public EntityTMGoblin(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.0F);
        this.setRandomWeapon(true);
        this.setLootTable(LootInit.GOBLIN);
        this.setMobMentality(true, SoundInit.soundAngryGoblin);
        this.setRndMinMax(1,5);
        this.setCombatTask();
    }

    @Override
    public boolean getCanSpawnHere() {
        int willSpawn = TTMSpawnEvent.spawnChance();

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && !this.world.canSeeSky(new BlockPos(this)) && willSpawn <= 10;
    }

    @Override
    public double getAttackDamage() {
        return 3.0D;
    }

    @Override
    public double getArmorStrength() {
        return 2.0D;
    }

    @Override
    public double getHealthLevel() {
        return 20.0D;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleGoblin;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtGoblin;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathGoblin;
    }
}