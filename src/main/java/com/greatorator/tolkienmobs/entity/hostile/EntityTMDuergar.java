package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTMDuergar extends EntityTMHostiles {

    public EntityTMDuergar(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.5F);
        this.setWeaponType(TTMFeatures.AXE_MORGULIRON);
        this.setLootTable(LootInit.TMDUERGAR);
        this.setMobMentality(true, SoundInit.soundAngryDwarf);
        this.setRndMinMax(1,5);
        this.setCombatTask();
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        boolean monsterSpawn = false;

        int willSpawn = this.spawnChance();
        BlockPos blockpos = new BlockPos(i, j, k);

        if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getLight(blockpos) < 8 && super.getCanSpawnHere() && !this.world.canSeeSky(new BlockPos(this)) && this.posY < 64.0D) {
            if (willSpawn <= 10) {
                monsterSpawn = true;
            }
        }
        return super.getCanSpawnHere() && monsterSpawn;
    }

    @Override
    public double getAttackDamage() {
        return 9.0D;
    }

    @Override
    public double getArmorStrength() {
        return 6.0D;
    }

    @Override
    public double getHealthLevel() {
        return 25.0D;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleDwarf;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtDwarf;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathDwarf;
    }
}